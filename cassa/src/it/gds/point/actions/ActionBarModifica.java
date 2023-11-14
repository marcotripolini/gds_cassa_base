package it.gds.point.actions;
import java.util.Date;
import java.util.Iterator;

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

public class ActionBarModifica extends ActionSupport implements HQLQueries {

	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private String id;
	private String valore;
	private NCarrellodett cd;

	public String execute() {
		try {
			Double val = Double.parseDouble(valore.replace(",", "."));
			Session sx = HibernateUtil.getSessionFactory().openSession();
			NCarrellodett obj = (NCarrellodett) sx.get(NCarrellodett.class, Long.parseLong(id));
			obj.setPrezzo(val);
			obj.setTotale(val * obj.getQuantita());
			OrdineNegozio o = (OrdineNegozio)session.getAttribute("BAR_O");
			Cliente cli = (Cliente)session.getAttribute("CLIENTE");
			Iterator i = o.getCarrello().iterator();
			while (i.hasNext()) {
				NCarrellodett object = (NCarrellodett) i.next();
				if (object.getId() == (Long.parseLong(id))) {
					//o.getCarrello().remove(object);
					o.setImporto(o.getImporto() - object.getTotale());
					object.setPrezzo(val);
					object.setTotale(val * object.getQuantita());
					o.setImporto(o.getImporto() + object.getTotale());
					session.setAttribute("BAR_O", o);
					HibernateUtil.saveOrUpdate(o);
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValore() {
		return valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}
}