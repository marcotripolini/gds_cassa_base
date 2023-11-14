package it.gds.point.actions;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Carrellodett;
import it.gds.point.beans.Cliente;
import it.gds.point.beans.DiBa;
import it.gds.point.beans.NCarrellodett;
import it.gds.point.beans.Ordine;
import it.gds.point.beans.OrdineDettagli;
import it.gds.point.beans.OrdineNegozio;
import it.gds.point.beans.OrdineTotem;
import it.gds.point.beans.Prodotto;
import it.gds.point.beans.TesseraAbbonamento;
import it.gds.point.interfaces.HQLQueries;
import it.gds.point.persistence.HibernateUtil;
import it.gds.point.utils.Config;
import it.gds.point.utils.MyPrint;

public class ActionProcess extends ActionSupport implements HQLQueries {
	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private Cliente cli;
	private Ordine o = null;
	private OrdineDettagli od1, od2;
	private OrdineTotem totem_o;
	private OrdineNegozio bar_o;
	SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	DecimalFormat formatter = new DecimalFormat("###0.00");
	private boolean isDebug = false;

	@SuppressWarnings("rawtypes")
	public synchronized String execute() {
		try {
			String serviceprinter = Config.getSERVICEPRINTER();
			session.removeAttribute("SCONTO");
			cli = (Cliente) session.getAttribute("CLIENTE");
			Long negozio = Long.decode(Config.getNEGOZIO());
			Double ticketrest = (double) 0;

			try {
				ticketrest = Double.parseDouble((session.getAttribute("TICKETREST")).toString().replace(",","."));
			} catch (Exception e) {
				ticketrest = (double)0;
			}

			if (cli != null) {
				String printdata = "NONE";
				String fiscale = "NONE";
//				Properties prop = (Properties)ServletActionContext.getRequest().getServletContext().getAttribute("PROPERTIES");
//				BufferedWriter writer = new BufferedWriter(
//						new OutputStreamWriter(new FileOutputStream(prop.getProperty("scontrino_path") + "scontrino.inp"), "utf-8"));
				boolean created = false;
				Double totcart = (double) 0;
				Iterator i;

				// ****************************************************************************************
				// ordine del bar
				// ****************************************************************************************

				bar_o = (OrdineNegozio) session.getAttribute("BAR_O");
				if (bar_o != null) {

					i = bar_o.getCarrello().iterator();
					while (i.hasNext()) {
						NCarrellodett el = (NCarrellodett) i.next();
						if (created == false) {
							o = new Ordine();
							o.setId_cliente(cli.getId());
							o.setTessera(cli.getTessera().getTessera());
							o.setD_indirizzo("VENDUTO IN GDSPOINT NOVARA");
							o.setD_localita(null);
							o.setD_cap(null);
							o.setD_provincia(null);
							o.setD_note(null);
							o.setConsegna_fascia_a(false);
							o.setConsegna_fascia_b(false);
							o.setConsegna_fascia_c(false);
							o.setData(new Date());
							o.setCod_referente_dest(cli.getCod_referente());
							o.setImporto((double)0);
							o.setSpesesped((double)0);
							o.setTotale((double)0);
							o.setNegozio((long)negozio);

							HibernateUtil.saveOrUpdate(o);
							created = true;
						}

						Carrellodett d = new Carrellodett();
						d.setCodice_gds(el.getCodice_gds());
						d.setDescrizione(el.getDescrizione());
						d.setId_carrello(null);
						d.setId_sito((long)1);

						char[] prezzo = (el.getPrezzo().toString()).toCharArray();
//						for(int k = prezzo.length - 1; k >= 0; k--) {
//							if(prezzo[k] != '0') {
//								if(prezzo[k] == '5') {
//									prezzo[k] = '6';
//								}
//								break;
//							}
//						}
						char[] totale = (el.getTotale().toString()).toCharArray();
//						for(int k = totale.length - 1; k >= 0; k--) {
//							if(totale[k] != '0') {
//								if(totale[k] == '5') {
//									totale[k] = '6';
//								}
//								break;
//							}
//						}

						d.setPrezzo(el.getPrezzo());
						d.setQuantita(el.getQuantita().doubleValue());
						d.setTotale(el.getTotale());
						d.setUnita_misura(el.getUnita_misura());
						d.setReparto(el.getReparto());
						d.setId_ordine(o.getId());

						scaricoMagazzinoDiba(d.getCodice_gds(), d.getQuantita());
						azione (d.getCodice_gds(), d.getQuantita());

						if (isDebug == true) {
							continue ;
						}

						HibernateUtil.saveOrUpdate(d);
						Long id = d.getId();
						Session sx = HibernateUtil.getSessionFactory().openSession();
						d = (Carrellodett)sx.createQuery("FROM Carrellodett WHERE id = :id").setParameter("id", id).uniqueResult();
						if (sx.isOpen()) {
							sx.close();
						}
						totcart = totcart + d.getTotale();
					}
					bar_o.setPayed(true);
					HibernateUtil.saveOrUpdate(bar_o);
					Set<TesseraAbbonamento> abbonamenti = cli.getTessera().getAbbonamenti();
					Iterator it = abbonamenti.iterator();
					while (it.hasNext()) {
						TesseraAbbonamento abbonamento = (TesseraAbbonamento) it.next();
						HibernateUtil.saveOrUpdate(abbonamento);
					}
				}
				if (created) {
					o.setImporto(totcart);
					o.setSpesesped((double)0);
					o.setTotale(totcart);
					o.setPagato(true);
					o.setPagato_il(new Date());
					o.setPagato_transaz("GDSPOINT_VIGEVANO");
					o.setContrassegno(false);

					HibernateUtil.saveOrUpdate(o);

					Session sx = HibernateUtil.getSessionFactory().openSession();
					od1 = (OrdineDettagli) sx.createQuery(HQL_ORDEDETT).setParameter("id_ordine", o.getId())
							.uniqueResult();

					if (sx.isOpen()) {
						sx.close();
					}
					// sx.close();

					if (!od1.getDettagli().isEmpty()) {
						// 	fiscale = prop.getProperty("print_1_ip") + "\n" + "=K\n" + "=C1\n" + "<</?s\n";
						fiscale = Config.getPOSPRINTER() + "\n" + "=K\n" + "=C1\n" + "<</?s\n";
						Double totscontrino = (double)0;

						Iterator p1 = od1.getDettagli().iterator();
						while (p1.hasNext()) {
							Carrellodett el = (Carrellodett) p1.next();
							if (el.getTotale() > 0) {
								fiscale += "=R" 
										+ el.getReparto() + "/$" 
										+ formatter.format(((Double) el.getTotale())).replaceAll(",", "") + "/("
										+ el.getQuantita() + "x " + el.getDescrizione() +  ")\n";
								totscontrino += el.getTotale();
							}
						}

						String ptype = (String)session.getAttribute("PTYPE");

						if (ptype.equals("CO")) {
							fiscale += "=S\n=T1\n";
						} else if (ptype.equals("CC")) {
							fiscale += "=S\n=T4\n";
						} else if (ptype.equals("TK")) {
							if (ticketrest == 0) {
								fiscale += "=S\n=T2\n";
							} else {
								totscontrino = totscontrino - ticketrest;
								if (totscontrino > 0) {
									fiscale += "=S\n=T1" + "/$" + formatter.format(((Double) totscontrino)).replaceAll(",", "") + "\n";
								}
								fiscale += "=S\n=T2" + "/$" + formatter.format(((Double) ticketrest)).replaceAll(",", "") + "\n";
							}
						}
					}
				}

				//	**********************************************************************************

				totem_o = (OrdineTotem) session.getAttribute("TOTEM_O");
				if (totem_o != null) {

					created = false;
					totcart = (double) 0;
					i = totem_o.getCarrello().iterator();
					while (i.hasNext()) {
						NCarrellodett el = (NCarrellodett) i.next();

						if (el.getP_totem()) {

							if (created == false) {

								o = new Ordine();
								o.setId_cliente(cli.getId());
								o.setTessera(cli.getTessera().getTessera());

								o.setD_indirizzo(cli.getD_nominativo() + " - " + cli.getD_indirizzo());
								o.setD_localita(cli.getD_localita());
								o.setD_cap(cli.getD_cap());
								o.setD_provincia(cli.getD_provincia());
								o.setD_note(cli.getD_note());

								if (totem_o.getTime_d() == true) {
									o.setConsegna_fascia_a(true);
									o.setConsegna_fascia_b(true);
									o.setConsegna_fascia_c(true);
								} else {
									o.setConsegna_fascia_a(totem_o.getTime_a());
									o.setConsegna_fascia_b(totem_o.getTime_b());
									o.setConsegna_fascia_c(totem_o.getTime_c());
								}

								o.setData(new Date());
								o.setCod_referente_dest(cli.getCod_referente());
								o.setImporto((double)0);
								o.setSpesesped((double)0);
								o.setTotale((double)0);
								HibernateUtil.saveOrUpdate(o);

								created = true;

							}

							Carrellodett d = new Carrellodett();

							d.setCodice_gds(el.getCodice_gds());
							d.setDescrizione(el.getDescrizione());
							d.setId_carrello(null);
							d.setId_sito((long)1);


							d.setPrezzo(el.getPrezzo());
							d.setQuantita(el.getQuantita().doubleValue());
							d.setTotale(el.getTotale());

							d.setUnita_misura(el.getUnita_misura());

							d.setId_ordine(o.getId());

							HibernateUtil.saveOrUpdate(d);

							Long id = d.getId();
							Session sx = HibernateUtil.getSessionFactory().openSession();
							d = (Carrellodett)sx.createQuery("FROM Carrellodett WHERE id = :id").setParameter("id", id).uniqueResult();

							if (sx.isOpen()) {
								sx.close();
							}
							// sx.close();

							totcart = totcart + d.getTotale();
						}
					}

					if (created) {
						totem_o.setPayed(true);
						HibernateUtil.saveOrUpdate(totem_o);

						if (!totem_o.getContrassegno()) {
							o.setPagato(true);
							o.setPagato_il(new Date());
							o.setPagato_transaz("GDSPOINT_NOVARA");
							o.setContrassegno(false);
						} else {
							o.setPagato(false);
							o.setContrassegno(true);
						}

						o.setImporto(totcart);
						o.setSpesesped((double)0);
						o.setTotale(totcart);

						HibernateUtil.saveOrUpdate(o);

						Session sx = HibernateUtil.getSessionFactory().openSession();
						od2 = (OrdineDettagli) sx.createQuery(HQL_ORDEDETT).setParameter("id_ordine", o.getId()).uniqueResult();
						if (sx.isOpen()) {
							sx.close();
						}
						// sx.close();

						if (!od2.getDettagli().isEmpty()) {
							printdata = "GDS POINT NOVARA\nNon fiscale\n\n" + formatter2.format(new Date()) + "\n\n";
							printdata += "ORDINE N. " + od2.getId() + "\n\n";
							Iterator p1 = od2.getDettagli().iterator();
							while (p1.hasNext()) {
								Carrellodett el = (Carrellodett) p1.next();
								printdata += "- " + el.getDescrizione() + "\n";
								String imp = "";
								String tot = "";

								imp = "Euro " + formatter.format(el.getPrezzo()) + " x"
										+ ((Double) el.getQuantita()).intValue();
								tot = "Euro " + formatter.format(el.getTotale());

								printdata += imp;
								for (int k = 0; k < 32 - imp.length() - tot.length(); k++)
									printdata += " ";
								printdata += tot + "\n\n";
							}
							String otot = "Euro " + formatter.format(od2.getTotale());
							printdata += "TOTALE";
							for (int k = 0; k < 26 - otot.length(); k++)
								printdata += " ";
							printdata += otot + "\n\n\n";

							if (!od2.getContrassegno()) {
								printdata += "PAGATO IN NEGOZIO\n\n";
								String tot_completo;
								if (od1 != null) {
									tot_completo = "Euro " + formatter.format(od1.getTotale() + od2.getTotale());
								} else {
									tot_completo = "Euro " + formatter.format(od2.getTotale());
								}

								printdata += "TOTALE";
								for (int k = 0; k < 26 - tot_completo.length(); k++)
									printdata += " ";
								printdata += tot_completo + "\n\n\n";
							} else {
								printdata += "DA PAGARE ALLA CONSEGNA\n\n";
							}

							printdata += "Consegnare a\n";
							printdata += o.getD_indirizzo() + "\n";
							printdata += o.getD_cap() + "\n";
							printdata += o.getD_localita() + " " + o.getD_provincia() + "\n\n";
							if (o.getD_note() != null)
								printdata += o.getD_note() + "\n\n";

							printdata += "Orario di consegna:\n";
							if (o.getConsegna_fascia_a())
								printdata += "dalle 9.00 alle 12.00\n";
							if (o.getConsegna_fascia_b())
								printdata += "dalle 14.00 alle 17.00\n";
							if (o.getConsegna_fascia_c())
								printdata += "dalle 18.00 alle 21.00\n";

							printdata += "----\n\n\n \n\n\n";
						}
					}
				}

				session.invalidate();
				session = ServletActionContext.getRequest().getSession();

				// apertura canale per emissione scontrino. *******************************************************************
				// ************************************************************************************************************

				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(new FileOutputStream(Config.getSCONTRINOPATH() + "scontrino.inp"), "utf-8"));
				MyPrint p = new MyPrint(serviceprinter.trim());
				// ************************************************************************************************************

				if(!printdata.equals("NONE")) {
					p.setData(printdata, null);
					p.replaceSpecialChars();
					p.print();
				}

				if(!fiscale.equals("NONE")) {
					writer.write(stringClean(fiscale));
					writer.close();
					Process pp = Runtime.getRuntime().exec("cmd /c start \"\" \"" + Config.getSCONTRINOPATH() + "m.lnk\"");
					pp.waitFor();
				}
			} else {
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Cliente getCli() {
		return cli;
	}

	public OrdineDettagli getOd1() {
		return od1;
	}

	public OrdineDettagli getOd2() {
		return od2;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	private void scaricoMagazzinoDiba(String codice_gds, Double quantita) {
		if (codice_gds.equals("DUMMY_BAR")) {
			return;
		}

		Session sx = HibernateUtil.getSessionFactory().openSession();
		try {
//			Prodotto p = (Prodotto)sx.createQuery("FROM Prodotto WHERE codice_gds = :codice_gds").setParameter("codice_gds", codice_gds).uniqueResult();
			List<DiBa> distinta_base = (List<DiBa>) sx.createQuery("FROM DiBa WHERE codice_gds = :codice_gds")
					.setParameter("codice_gds", codice_gds).list();
			if (distinta_base != null && distinta_base.size() > 0) {
				Iterator it = distinta_base.iterator();
				while (it.hasNext()) {
					DiBa diba = (DiBa) it.next();
					Prodotto pm = (Prodotto) sx.get(Prodotto.class, diba.getCodice_gds_base());
					// Prodotto pm = (Prodotto)sx.createQuery("FROM Prodotto WHERE codice_gds =
					// :codice_gds").setParameter("codice_gds",
					// diba.getCodice_gds_base()).uniqueResult();
					pm.setGiacenza_magazzino(pm.getGiacenza_magazzino() - (diba.getQuantita() * quantita));
					sx.getTransaction().setTimeout(60);
					sx.getTransaction().begin();
					sx.saveOrUpdate(pm);
					sx.getTransaction().commit();
				}
			} else {
				Prodotto pm = (Prodotto) sx.get(Prodotto.class, codice_gds);
				// Prodotto pm = (Prodotto)sx.createQuery("FROM Prodotto WHERE codice_gds =
				// :codice_gds").setParameter("codice_gds",
				// diba.getCodice_gds_base()).uniqueResult();
				if (pm != null) {
					pm.setGiacenza_magazzino(pm.getGiacenza_magazzino() - (quantita));
					sx.getTransaction().setTimeout(60);
					sx.getTransaction().begin();
					sx.saveOrUpdate(pm);
					sx.getTransaction().commit();
				}
			}
		} catch (RuntimeException e) {
			sx.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (sx.isOpen()) {
				sx.close();
			}
		}
	}

	@SuppressWarnings("unused")
	private void scaricoMagazzino (String codice_gds, Double quantita ) {
		Session sx = HibernateUtil.getSessionFactory().openSession();
		try {
			Prodotto p = (Prodotto)sx.createQuery("FROM Prodotto WHERE codice_gds = :codice_gds").setParameter("codice_gds", codice_gds).uniqueResult();
			if (p != null) {
				if (p.getUnita_misura().equals ("cf")) {
					p.setGiacenza_magazzino(p.getGiacenza_magazzino() - quantita);
					sx.getTransaction().setTimeout(60);
					sx.getTransaction().begin();
					sx.saveOrUpdate(p);
					sx.getTransaction().commit();
				} else if (p.getUnita_misura().equals ("g")) {
					if (p.getCodice_prodotto_collegato_magazzino() != null && p.getScarico_magazzino() != null ) {
						Prodotto pm = (Prodotto)sx.createQuery("FROM Prodotto WHERE codice_gds = :codice_gds").setParameter("codice_gds", p.getCodice_prodotto_collegato_magazzino()).uniqueResult();
						if (pm != null) {
							pm.setGiacenza_magazzino(pm.getGiacenza_magazzino() - (quantita * p.getScarico_magazzino()));
							sx.getTransaction().setTimeout(60);
							sx.getTransaction().begin();
							sx.saveOrUpdate(pm);
							sx.getTransaction().commit();
						} else {
							p.setGiacenza_magazzino(p.getGiacenza_magazzino() - quantita);
							sx.getTransaction().setTimeout(60);
							sx.getTransaction().begin();
							sx.saveOrUpdate(p);
							sx.getTransaction().commit();
						}
					} else {
						p.setGiacenza_magazzino(p.getGiacenza_magazzino() - quantita);
						sx.getTransaction().setTimeout(60);
						sx.getTransaction().begin();
						sx.saveOrUpdate(p);
						sx.getTransaction().commit();
					}
				} else if (p.getUnita_misura().equals ("ml")) {
					if (p.getCodice_prodotto_collegato_magazzino() != null && p.getScarico_magazzino() != null ) {
						Prodotto pm = (Prodotto)sx.createQuery("FROM Prodotto WHERE codice_gds = :codice_gds").setParameter("codice_gds", p.getCodice_prodotto_collegato_magazzino()).uniqueResult();
						pm.setGiacenza_magazzino(pm.getGiacenza_magazzino() - (quantita * p.getScarico_magazzino()));
						sx.getTransaction().setTimeout(60);
						sx.getTransaction().begin();
						sx.saveOrUpdate(pm);
						sx.getTransaction().commit();
					} else {
						p.setGiacenza_magazzino(p.getGiacenza_magazzino() - quantita);
						sx.getTransaction().setTimeout(60);
						sx.getTransaction().begin();
						sx.saveOrUpdate(p);
						sx.getTransaction().commit();
					}
				}
			}
		} catch (RuntimeException e) {
			sx.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (sx.isOpen()) {
				sx.close();
			}
		}
	}

	private void azione (String codice_gds, Double quantita) {
		Session sx = HibernateUtil.getSessionFactory().openSession();
		try {
			Prodotto p = (Prodotto)sx.createQuery("FROM Prodotto WHERE codice_gds = :codice_gds").setParameter("codice_gds", codice_gds).uniqueResult();
				if (p != null && p.getAzione() > 0) {
					if (p.getAzione() > 0) {
						TesseraAbbonamento abbo = (TesseraAbbonamento) sx.createQuery(
								"FROM TesseraAbbonamento WHERE codice_abbonamento = :codice_abbonamento and tessera = :tessera")
								.setParameter("codice_abbonamento", codice_gds)
								.setParameter("tessera", cli.getTessera().getTessera()).uniqueResult();
						if (abbo != null ) {
							// ricarico l'abbonamento
							abbo.setContatore(abbo.getContatore() + p.getValore() * quantita.intValue());
							Set <TesseraAbbonamento> set = cli.getTessera().getAbbonamenti();
							Iterator it = set.iterator();
							while (it.hasNext()) {
								TesseraAbbonamento objabbo = (TesseraAbbonamento) it.next();
								if (objabbo.getCodice_gds().equals(abbo.getCodice_gds())) {
									objabbo.setContatore((Integer)abbo.getContatore());
									break;
								}
							}
							HibernateUtil.saveOrUpdate(abbo);
						} else {
							abbo = new TesseraAbbonamento();
							abbo.setCodice_gds(p.getCodice_gds_azione());
							abbo.setCodice_abbonamento(p.getCodice_gds());
							abbo.setContatore(p.getValore() * quantita.intValue());
							abbo.setDescrizione(p.getTitolo());
							abbo.setLoading_date(new Date());
							abbo.setTessera(cli.getTessera().getTessera());
							HibernateUtil.saveOrUpdate(abbo);
						}
					}
				}
		} catch (RuntimeException e) {
			sx.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (sx.isOpen()) {
				sx.close();
			}
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
}
