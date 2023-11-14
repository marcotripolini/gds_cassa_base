package it.gds.point.actions;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Cliente;
import it.gds.point.beans.NCarrellodett;
import it.gds.point.beans.OrdineNegozio;
import it.gds.point.beans.Prodotto;
import it.gds.point.interfaces.HQLQueries;
import it.gds.point.persistence.HibernateUtil;

public class ActionNoCard extends ActionSupport implements HQLQueries {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private Cliente cli;
	private OrdineNegozio o;
	private String items;

	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			cli = (Cliente)session.getAttribute("CLIENTE");
			if (cli == null) {

				Session sx = HibernateUtil.getSessionFactory().openSession();
				List<Cliente> clis = sx.createQuery (HQL_LOGIN).setParameter("tessera", "NOCARD").list();

				if (clis.size()  > 0) {
					cli = clis.get(0);
				}

				o = new OrdineNegozio();
				o.setId_cliente(cli.getId());
				o.setImporto((double)0);
				o.setExecuted(true);
				o.setType("B");
				o.setPayed(false);
				o.setContrassegno(false);
				HibernateUtil.saveOrUpdate(o);
				sx.close();

				session.setAttribute("CLIENTE", cli);
				session.setAttribute("BAR_O", o);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String executeWithItems() {
		try {
			session.removeAttribute("CLIENTE");
			cli = (Cliente)session.getAttribute("CLIENTE");
			if (cli == null) {

				String[] result = items.split(",");

				Session sx = HibernateUtil.getSessionFactory().openSession();
				List<Cliente> clis = sx.createQuery (HQL_LOGIN).setParameter("tessera", "NOCARD").list();

				if (clis.size()  > 0) {
					cli = clis.get(0);
				}

				o = new OrdineNegozio();
				o.setId_cliente(cli.getId());
				o.setImporto((double)0);
				o.setExecuted(true);
				o.setType("B");
				o.setPayed(false);
				o.setContrassegno(false);

				sx.getTransaction().setTimeout(10);
				sx.getTransaction().begin();
				sx.saveOrUpdate(o);
				sx.getTransaction().commit();

//				HibernateUtil.saveOrUpdate(o);
//				System.out.println(o.getId());

				for (String str : result){
					sx.getTransaction().setTimeout(10);
					sx.getTransaction().begin();
//					System.out.println(str);
//					p = (Prodotto)sx.createQuery(HQL_PRODBYCOD).setParameter("codice_gds", getCodice_gds()).uniqueResult();

				    String sql = "update n_carrellodett set id_totem_order = " + o.getId() + " where id=" + str;
				    Query query = sx.createSQLQuery(sql);
				    query.executeUpdate();
					sx.getTransaction().commit();
				}


				sx.refresh(o);

				Iterator it = o.getCarrello().iterator();

				while (it.hasNext()) {
					NCarrellodett c = (NCarrellodett) it.next();
					Prodotto p = (Prodotto)sx.createQuery(HQL_PRODBYCOD).setParameter("codice_gds", c.getCodice_gds()).uniqueResult();
					c.setPrezzo(p.getP_alpubblico_0());
					c.setTotale(p.getP_alpubblico_0());

					o.setImporto(o.getImporto() + c.getTotale());
				}

				sx.getTransaction().setTimeout(10);
				sx.getTransaction().begin();
				sx.saveOrUpdate(o);
				sx.getTransaction().commit();

				sx.close();

				session.setAttribute("CLIENTE", cli);
				session.setAttribute("BAR_O", o);

			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

}
