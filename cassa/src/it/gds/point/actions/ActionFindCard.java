package it.gds.point.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Cliente;
import it.gds.point.interfaces.HQLQueries;
import it.gds.point.persistence.HibernateUtil;

public class ActionFindCard extends ActionSupport implements HQLQueries {
	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private List<Cliente> clis = new ArrayList<Cliente>();
	private String name;

	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			Session sx = HibernateUtil.getSessionFactory().openSession();
			if (name != null && !name.equals("")) {
				clis = (List<Cliente>) sx.createQuery("from Cliente where cognome like '%" + name + "%'").list();
			}
			sx.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Cliente> getClis() {
		return clis;
	}

	public void setClis(List<Cliente> clis) {
		this.clis = clis;
	}

}
