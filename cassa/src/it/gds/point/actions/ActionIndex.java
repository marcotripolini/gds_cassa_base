package it.gds.point.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Cliente;
import it.gds.point.beans.Tavolo;
import it.gds.point.beans.TavoloStatus;
import it.gds.point.persistence.HibernateUtil;

public class ActionIndex extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private Cliente cli;
	private List<Tavolo> tt;
	private List<TavoloStatus> ttt;

	class Tavoli {
		private String tavolo;
		private boolean libero;

		public String getTavolo() {
			return tavolo;
		}

		public void setTavolo(String tavolo) {
			this.tavolo = tavolo;
		}

		public boolean isLibero() {
			return libero;
		}

		public void setLibero(boolean libero) {
			this.libero = libero;
		}

		public Tavoli() {

		}
	}

	List<Tavoli> tavoli = new ArrayList<Tavoli>(0);

	public List<Tavoli> getTavoli() {
		return tavoli;
	}

	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			cli = (Cliente) session.getAttribute("CLIENTE");
			if (cli == null) {
				Session sx = HibernateUtil.getSessionFactory().openSession();
//				tt = sx.createQuery("from Tavolo").list();
				ttt = sx.createQuery("from TavoloStatus").list();
				sx.close();

				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<Tavolo> getTt() {
		return tt;
	}

	public void setTt(List<Tavolo> tt) {
		this.tt = tt;
	}

	public List<TavoloStatus> getTtt() {
		return ttt;
	}

	public void setTtt(List<TavoloStatus> ttt) {
		this.ttt = ttt;
	}
}
