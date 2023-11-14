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

public class ActionBarRimuovi extends ActionSupport implements HQLQueries {

	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private String id;
	private NCarrellodett cd;

	public String execute() {
		try {
			if (id != null && !id.equals("")) {
				Session sx = HibernateUtil.getSessionFactory().openSession();
				sx.getTransaction().setTimeout(10);
				sx.getTransaction().begin();
				sx.createQuery("delete from NCarrellodett where id='" + id + "'").executeUpdate();
				sx.getTransaction().commit();

				OrdineNegozio o = (OrdineNegozio) session.getAttribute("BAR_O");
				Cliente cli = (Cliente) session.getAttribute("CLIENTE");
				Iterator i = o.getCarrello().iterator();
				while (i.hasNext()) {
					NCarrellodett object = (NCarrellodett) i.next();
					if (object.getId() == (Long.parseLong(id))) {
						o.getCarrello().remove(object);
						o.setImporto(o.getImporto() - object.getTotale());
						session.setAttribute("BAR_O", o);
						HibernateUtil.saveOrUpdate(o);
						break;
					}
				}
			}
		} catch (Exception e) {
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
}
