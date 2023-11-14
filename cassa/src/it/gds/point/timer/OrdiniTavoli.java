package it.gds.point.timer;

import java.io.File;
import java.net.*;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.SourceDataLine;

import org.hibernate.Session;
import it.gds.point.beans.NCarrellodett;
import it.gds.point.beans.OrdineNegozio;
import it.gds.point.beans.OrdineNegozioPrint;
import it.gds.point.beans.Tavolo;
import it.gds.point.interfaces.HQLQueries;
import it.gds.point.persistence.HibernateUtil;
import it.gds.point.utils.Config;
import it.gds.point.utils.MyPrint;

public class OrdiniTavoli extends TimerTask implements HQLQueries {
	private ServletContext sc;

	public OrdiniTavoli(ServletContext sc) {
		this.sc = sc;
	}

	@SuppressWarnings("deprecation")
	@Override
	public synchronized void run() {
		boolean isdebug = Config.isDebug();
		try {
			String dir = sc.getRealPath("/");
			System.out.println(dir);

			if (isdebug == false) {
				System.out.println("----> Avvio controllo comande: " + (new Date()).toLocaleString());

				Session sx = HibernateUtil.getSessionFactory().openSession();
				@SuppressWarnings("unchecked")
				List<Tavolo> tt = sx
						.createQuery("from Tavolo where ordine is not null and (stampato is null or stampato = false)")
						.list();
				if (tt.size() > 0) {
					MyPrint p = new MyPrint(Config.getSERVICEPRINTER());
					String printdata = "";
					SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm");
					Iterator<Tavolo> it = tt.iterator();

					while (it.hasNext()) {
						try {
							Tavolo tavolo = (Tavolo) it.next();
							Long id_ordine = tavolo.getOrdine();
							OrdineNegozioPrint bar_o = (OrdineNegozioPrint) sx.createQuery(HQL_BARORDER_ID_PRINT)
									.setParameter("id_ordine", id_ordine).uniqueResult();
							Iterator itcart = null;
							if (bar_o != null) {
								itcart = bar_o.getCarrello().iterator();
							}

							printdata += "ORD. TAVOLO: " + tavolo.getTavolo() + "\n\n";
							printdata += "ORD. NUMERO: " + tavolo.getOrdine() + "\n\n";
							printdata += "ORE: " + formatter2.format(new Date()) + "\n\n";
							while (itcart.hasNext()) {
								NCarrellodett dett = (NCarrellodett) itcart.next();
								printdata += this.stringClean(dett.getDescrizione()) + "\n";
								printdata += "Quant: " + dett.getQuantita() + "\n";
							}

							printdata += "\n";
							printdata += "GESTITO:_______________________\n\n";
							printdata += "<----------------------------->\n\n";
							printdata += "\n\n";

							OrdineNegozio on = (OrdineNegozio) sx.get(OrdineNegozio.class, id_ordine);
							sx.evict(on);
							System.out.println(printdata);
							on.setStampato(true);
							HibernateUtil.saveOrUpdate(on);
						} catch (Exception e) {
							e.printStackTrace();
						}

						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					if (!printdata.equals("")) {
						p.setData(printdata, null);
						p.replaceSpecialChars();
						p.print();

						play(dir);

//						byte[] buf = new byte[1];
//
//						AudioFormat af = new AudioFormat((float) 44100, 8, 1, true, false);
//						SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
//						sdl.open();
//						sdl.start();
//						for (int i = 0; i < 2000 * (float) 44100 / 1000; i++) {
//							double angle = i / ((float) 44100 / 440) * 3.5 * Math.PI;
//							buf[0] = (byte) (Math.sin(angle) * 100);
//							sdl.write(buf, 0, 1);
//						}
//						sdl.drain();
//						sdl.stop();

					}
					printdata = "";
				}
				sx.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean exists(String URLName) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			// note : you may also need
			// HttpURLConnection.setInstanceFollowRedirects(false)
			HttpURLConnection conn = (HttpURLConnection) new URL(URLName).openConnection();
			conn.setRequestMethod("HEAD");
			return (conn.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private String stringClean (String text) {
		 text = text.replaceAll("è", "e'");
		 text = text.replaceAll("é", "e'");
		 text = text.replaceAll("à", "a'");
		 text = text.replaceAll("ì", "i'");
		 text = text.replaceAll("ò", "o'");
		 text = text.replaceAll("ù", "u'");
		 return text;
	 }

	public void sound(String dir) {
		String sound = dir + "" + "BELLS.WAV";
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(sound));
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        // If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY);
	        // If you want to stop the sound, then use clip.stop();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}

	public void play (String dir) {
		String sound = dir + "" + "BELLS.WAV";
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(sound));
            Clip test = AudioSystem.getClip();

            test.open(ais);
            test.start();

            while (!test.isRunning())
                Thread.sleep(10);
            while (test.isRunning())
                Thread.sleep(10);

            test.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
	}
}
