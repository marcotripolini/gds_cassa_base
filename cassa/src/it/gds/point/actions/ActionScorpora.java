package it.gds.point.actions;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Cliente;
import it.gds.point.beans.CodiceSconto;
import it.gds.point.beans.NCarrellodett;
import it.gds.point.beans.OrdineNegozio;
import it.gds.point.beans.Prodotto;
import it.gds.point.interfaces.HQLQueries;
import it.gds.point.persistence.HibernateUtil;

public class ActionScorpora extends ActionSupport implements HQLQueries {
	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private String codice_gds;
	private String prezzo;
	private NCarrellodett cd;

	public String execute() {
		try {
			OrdineNegozio o = (OrdineNegozio)session.getAttribute("BAR_O");
			Cliente cli = (Cliente)session.getAttribute("CLIENTE");
			Set <NCarrellodett> carrello1 = o.getCarrello();
			Set <NCarrellodett> carrello2 = new HashSet<NCarrellodett>();

			// reset
			o.setCarrello(carrello2);
			o.setImporto((double) 0);

			Session sx = HibernateUtil.getSessionFactory().openSession();
			Iterator it = carrello1.iterator();

			while (it.hasNext()) {
				NCarrellodett obj = (NCarrellodett) it.next();

				HibernateUtil.delete(obj);
				
				String codice_gds = obj.getCodice_gds();
				Prodotto p = null;
				Double quanti = obj.getQuantita();
				p = (Prodotto)sx.createQuery(HQL_PRODBYCOD).setParameter("codice_gds", codice_gds).uniqueResult();
				for (int i=0; i<quanti; i++) {
					NCarrellodett cd = new NCarrellodett();
					cd.setCodice_gds(p.getCodice_gds());
					cd.setDescrizione(obj.getDescrizione());
					cd.setId_cliente(cli.getId());
					cd.setP_a_peso(false);
					cd.setP_totem(false);
					cd.setP_bar(true);
					cd.setOld(false);
					cd.setQuantita((double)1);
					CodiceSconto sconto = (CodiceSconto) session.getAttribute("SCONTO");

					if (sconto != null) {
						List<String> codici  = Arrays.asList(sconto.getCategorie().split(","));
						if (codici.contains(p.getCategoria_gds())) {
							System.out.println("si");
							cd.setPrezzo((p.getP_alpubblico_0())
									- ((p.getP_alpubblico_0() ) * (sconto.getSconto_perc() / 100)));
							cd.setDescrizione(cd.getDescrizione() + " [sc. 20]");
						} else {
							if (cli.getTessera().getTessera().equals("NOCARD")
									|| cli.getTessera().getTessera().contains("TAV")) {
								cd.setPrezzo(p.getP_alpubblico_0());
							} else {
								switch (cli.getTessera().getLivello_listino()) {
								case "0":
									cd.setPrezzo(p.getP_alpubblico_1());
									break;
								case "1":
									cd.setPrezzo(p.getP_alpubblico_2());
									break;
								case "2":
									cd.setPrezzo(p.getP_alpubblico_3());
									break;
								case "3":
									cd.setPrezzo(p.getP_alpubblico_4());
									break;
								default:
									cd.setPrezzo(p.getP_alpubblico_4());
									break;
								}
							}
						}
					} else {
						if (cli.getTessera().getTessera().equals("NOCARD")) {
							cd.setPrezzo(p.getP_alpubblico_0());
						} else {
							if (p.getCategoria_gds().equals("BISM00109")) {
								cd.setPrezzo(p.getP_alpubblico_0());
							} else {
								switch (cli.getTessera().getLivello_listino()) {
								case "0":
									cd.setPrezzo(p.getP_alpubblico_1());
									break;
								case "1":
									cd.setPrezzo(p.getP_alpubblico_2());
									break;
								case "2":
									cd.setPrezzo(p.getP_alpubblico_3());
									break;
								case "3":
									cd.setPrezzo(p.getP_alpubblico_4());
									break;
								default:
									cd.setPrezzo(p.getP_alpubblico_4());
									break;
								}
							}

						}
					}
					
					cd.setTotale(cd.getPrezzo());
					cd.setId_totem_order(o.getId());
					cd.setOrder(o.getCarrello().size() + 1);
					cd.setDatetime_insert(new Date());
					cd.setUnita_misura(p.getUnita_misura());
					cd.setPeso((double)0);
					cd.setReparto("1");
					
					HibernateUtil.saveOrUpdate(cd);
					
					o.getCarrello().add(cd);
					o.setImporto(o.getImporto() + cd.getTotale());
				}
			}
			
			HibernateUtil.saveOrUpdate(o);
			session.setAttribute("BAR_O", o);
			sx.close();
			return SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	public String getCodice_gds() {
		return codice_gds;
	}

	public void setCodice_gds(String codice_gds) {
		this.codice_gds = codice_gds;
	}

	public String getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}

}
