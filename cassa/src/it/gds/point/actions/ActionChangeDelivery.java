package it.gds.point.actions;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Cliente;
import it.gds.point.beans.OrdineTotem;
import it.gds.point.persistence.HibernateUtil;

public class ActionChangeDelivery extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private Cliente cli;
	private OrdineTotem totem_o;
	private String d_nominativo;
	private String d_indirizzo;
	private String d_cap;
	private String d_localita;
	private String d_provincia;
	
	public String execute() {
		try {
			cli = (Cliente)session.getAttribute("CLIENTE");
			cli.setD_nominativo(getD_nominativo());
			cli.setD_indirizzo(getD_indirizzo());
			cli.setD_cap(getD_cap());
			cli.setD_localita(getD_localita());
			cli.setD_provincia(getD_provincia());
			HibernateUtil.saveOrUpdate(cli);
			session.setAttribute("CLIENTE", cli);
			totem_o = (OrdineTotem)session.getAttribute("TOTEM_O");
			if(totem_o != null) {
				if(totem_o.getChange_delivery()) {
					totem_o.setChange_delivery(false);
					HibernateUtil.saveOrUpdate(totem_o);
					session.setAttribute("TOTEM_O", totem_o);
				}
			}
			response.getWriter().print(SUCCESS);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	public String getD_nominativo() {
		return d_nominativo;
	}

	public void setD_nominativo(String d_nominativo) {
		this.d_nominativo = d_nominativo;
	}

	public String getD_indirizzo() {
		return d_indirizzo;
	}

	public void setD_indirizzo(String d_indirizzo) {
		this.d_indirizzo = d_indirizzo;
	}

	public String getD_cap() {
		return d_cap;
	}

	public void setD_cap(String d_cap) {
		this.d_cap = d_cap;
	}

	public String getD_localita() {
		return d_localita;
	}

	public void setD_localita(String d_localita) {
		this.d_localita = d_localita;
	}

	public String getD_provincia() {
		return d_provincia;
	}

	public void setD_provincia(String d_provincia) {
		this.d_provincia = d_provincia;
	}
	
	

}
