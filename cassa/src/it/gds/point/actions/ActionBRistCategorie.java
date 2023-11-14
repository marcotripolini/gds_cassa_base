package it.gds.point.actions;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Categoria;
import it.gds.point.interfaces.HQLQueries;
import it.gds.point.persistence.HibernateUtil;

public class ActionBRistCategorie extends ActionSupport implements HQLQueries {
	private static final long serialVersionUID = 1L;
	private List<Categoria> cl;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private String categoria_base = "BISMT002%";
	private String categorie_menu = " ('X','XC') ";

	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			Session sx = HibernateUtil.getSessionFactory().openSession();
			cl = (List<Categoria>) sx.createQuery(
					"FROM Categoria WHERE categoria_gds LIKE '"
					+ categoria_base
					+ "' AND categoria_2 IS NOT NULL and categoria_menu in "
					+ categorie_menu
					+ " ORDER BY categoria_2")
					.list();
			sx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<Categoria> getCl() {
		return cl;
	}
}
