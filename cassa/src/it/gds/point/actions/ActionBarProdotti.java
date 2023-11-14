package it.gds.point.actions;

import java.util.List;

import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Prodotto;
import it.gds.point.interfaces.HQLQueries;
import it.gds.point.persistence.HibernateUtil;

public class ActionBarProdotti extends ActionSupport implements HQLQueries {
	private static final long serialVersionUID = 1L;
	private String categoria_gds;
	private List<Prodotto> pl;

	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			Session sx = HibernateUtil.getSessionFactory().openSession();
			pl = (List<Prodotto>)sx.createQuery("FROM Prodotto WHERE visibile = true and categoria_gds = '" + getCategoria_gds() + "' ORDER BY titolo").list();
			sx.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getCategoria_gds() {
		return categoria_gds;
	}

	public void setCategoria_gds(String categoria_gds) {
		this.categoria_gds = categoria_gds;
	}

	public List<Prodotto> getPl() {
		return pl;
	}
}
