package it.gds.point.actions;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Cliente;
import it.gds.point.beans.NCarrellodett;
import it.gds.point.beans.OrdineNegozio;
import it.gds.point.beans.Prodotto;
import it.gds.point.interfaces.HQLQueries;
import it.gds.point.persistence.HibernateUtil;

public class ActionCard extends ActionSupport implements HQLQueries {

	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private Cliente cli;
	private String tessera;

	public String execute() {
		try {
			Session sx = HibernateUtil.getSessionFactory().openSession();

        	if (tessera != null ) {
				List<Cliente> clis = sx.createQuery(HQL_LOGIN).setParameter("tessera", getTessera()).list();
				if (clis.size()  > 0) {
					cli = (Cliente) clis.get(0);
				}

				if (cli != null) {
					session.setAttribute("CLIENTE", cli);
					OrdineNegozio o = (OrdineNegozio) session.getAttribute("BAR_O");
					Set<NCarrellodett> carrello = o.getCarrello();
					Iterator<NCarrellodett> it = carrello.iterator();
					o.setImporto ((double) 0);
					while (it.hasNext()) {
						NCarrellodett e = (NCarrellodett) it.next();
						Prodotto p = (Prodotto) sx.createQuery(HQL_PRODBYCOD).setParameter("codice_gds", e.getCodice_gds().trim()).uniqueResult();
						if (p != null) {
							if (p.getCategoria_gds().equals("BISM00109")) {
								e.setPrezzo(p.getP_alpubblico_0());
							} else {
								switch (cli.getTessera().getLivello_listino()) {
								case "0":
									e.setPrezzo(p.getP_alpubblico_1());
									break;
								case "1":
									e.setPrezzo(p.getP_alpubblico_2());
									break;
								case "2":
									e.setPrezzo(p.getP_alpubblico_3());
									break;
								case "3":
									e.setPrezzo(p.getP_alpubblico_4());
									break;
								default:
									e.setPrezzo(p.getP_alpubblico_4());
									break;
								}
							}
							e.setTotale(e.getPrezzo());
							o.setImporto(o.getImporto() + e.getTotale());
						}
					}
					session.setAttribute("CLIENTE", cli);
					session.setAttribute("BAR_O", o);
					return SUCCESS ;
				}
        	}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getTessera() {
		return tessera;
	}

	public void setTessera(String tessera) {
		this.tessera = tessera;
	}
}
