package it.gds.point.actions;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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

public class ActionScan extends ActionSupport implements HQLQueries {
	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private String barcode;
	private Prodotto p;
	private CodiceSconto s;
	List<String> codici = null;

	public String execute() {
		try {
			// 			how to right pad String in Java with any character
			//	        String rightpadded = StringUtils.rightPad(barcode, 13, "0");
			//	        System.out.println("Right padded String in Java with #: " + rightpadded);

			Session sx = HibernateUtil.getSessionFactory().openSession();
			if (isNumeric(getBarcode())) {
		        if (getBarcode().length() == 8 ) {
					p = (Prodotto) sx.createQuery(HQL_PRODBYEAN).setParameter("ean", getBarcode().trim()).uniqueResult();
		        }
			}

			if (p == null) {
				if (isNumeric(getBarcode())) {
			        if (getBarcode().length() == 13 ) {
						p = (Prodotto) sx.createQuery(HQL_PRODBYEAN).setParameter("ean", getBarcode().trim()).uniqueResult();
			        }
				}
			}

			Integer peso = null;
			Double peso_kg = null;
			if ( p == null ) {
//				Integer peso = null;
//				Double peso_kg = null;
				if (isNumeric(getBarcode())) {
					if (getBarcode().length() == 13) {
						peso = Integer.parseInt(getBarcode().substring(7, 12));
						peso_kg = (double) peso / 1000;
					}
				}
				p = (Prodotto) sx.createQuery(HQL_PRODBYEAN).setParameter("ean", getBarcode().trim()).uniqueResult();
			}

//			if (p == null) {
//				p = (Prodotto) sx.createQuery(HQL_PRODBYEAN).setParameter("ean", getBarcode().substring(0, 7))
//						.uniqueResult();
//			}

			if (p != null) {
				OrdineNegozio o = (OrdineNegozio) session.getAttribute("BAR_O");
				Cliente cli = (Cliente) session.getAttribute("CLIENTE");
				NCarrellodett cd = new NCarrellodett();
				cd.setCodice_gds(p.getCodice_gds());

				if (p.getMarchio().getId() != 276) {
					if (peso != null) {
						cd.setDescrizione(p.getMarchio().getMarchio() + " - " + p.getTitolo() + " [" + peso + " "
								+ p.getUnita_misura() + "]");
					} else {
						cd.setDescrizione(p.getMarchio().getMarchio() + " - " + p.getTitolo() );
					}
				} else {
					if (peso != null) {
						cd.setDescrizione(p.getTitolo() + " [" + peso + " " + p.getUnita_misura() + "]");
					} else {
						cd.setDescrizione(p.getTitolo() + "");
					}
				}


				cd.setId_cliente(cli.getId());
				if (peso != null ) {
					cd.setP_a_peso(true);
				} else {
					cd.setP_a_peso(false);
				}
				cd.setP_totem(false);
				cd.setP_bar(true);
				cd.setOld(false);
				cd.setQuantita((double) 1);
				CodiceSconto sconto = (CodiceSconto) session.getAttribute("SCONTO");
				if (sconto != null) {
					codici = Arrays.asList(sconto.getCategorie().split(","));
					if (codici.contains(p.getCategoria_gds())) {
						System.out.println("si");
						cd.setPrezzo((p.getP_alpubblico_0() * peso_kg)
								- ((p.getP_alpubblico_0() * peso_kg) * (sconto.getSconto_perc() / 100)));
						cd.setDescrizione(cd.getDescrizione() + " [sc. 20]");
					} else {
						if (peso == null) {
							if (cli.getTessera().getTessera().equals("NOCARD")  || cli.getTessera().getTessera().contains("TAV")) {
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
						} else {
							if (cli.getTessera().getTessera().equals("NOCARD")
									|| cli.getTessera().getTessera().contains("TAV")) {
								cd.setPrezzo(p.getP_alpubblico_0() * peso_kg);
							} else {
								switch (cli.getTessera().getLivello_listino()) {
								case "0":
									cd.setPrezzo(p.getP_alpubblico_1() * peso_kg);
									break;
								case "1":
									cd.setPrezzo(p.getP_alpubblico_2() * peso_kg);
									break;
								case "2":
									cd.setPrezzo(p.getP_alpubblico_3() * peso_kg);
									break;
								case "3":
									cd.setPrezzo(p.getP_alpubblico_4() * peso_kg);
									break;
								default:
									cd.setPrezzo(p.getP_alpubblico_4() * peso_kg);
									break;
								}
							}
						}
					}
				} else {
					if (peso == null) {
						if (cli.getTessera().getTessera().equals("NOCARD")  || cli.getTessera().getTessera().contains("TAV")) {
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
					} else {
						if (cli.getTessera().getTessera().equals("NOCARD") || cli.getTessera().getTessera().contains("TAV")) {
							cd.setPrezzo(p.getP_alpubblico_0() * peso_kg);
						} else {
							switch (cli.getTessera().getLivello_listino()) {
							case "0":
								cd.setPrezzo(p.getP_alpubblico_1() * peso_kg);
								break;
							case "1":
								cd.setPrezzo(p.getP_alpubblico_2() * peso_kg);
								break;
							case "2":
								cd.setPrezzo(p.getP_alpubblico_3() * peso_kg);
								break;
							case "3":
								cd.setPrezzo(p.getP_alpubblico_4() * peso_kg);
								break;
							default:
								cd.setPrezzo(p.getP_alpubblico_4() * peso_kg);
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
				cd.setPeso(peso_kg);
				cd.setReparto(getBarcode().substring(2, 3));

				o.getCarrello().add(cd);
				o.setImporto(o.getImporto() + cd.getTotale());

				HibernateUtil.saveOrUpdate(o);
				session.setAttribute("BAR_O", o);

				ServletActionContext.getResponse().getWriter().print(SUCCESS);
			} else {
				s = (CodiceSconto) sx.createQuery("from CodiceSconto where codice_sconto = '" + barcode + "'")
						.uniqueResult();
				sx.evict(s);
				if (s != null) {
					session.setAttribute("SCONTO", s);
					// codici = Arrays.asList(s.getCategorie().split(","));
					ServletActionContext.getResponse().getWriter().print(SUCCESS);
				}
			}
			sx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
