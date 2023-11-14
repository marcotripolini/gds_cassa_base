package it.gds.point.actions;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.OrdineTotem;
import it.gds.point.persistence.HibernateUtil;

public class ActionPayment extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private OrdineTotem totem_o;
	private String payment;
	
	public String execute() {
		try {
			totem_o = (OrdineTotem)session.getAttribute("TOTEM_O");
				if(totem_o != null) {
					if(getPayment().equals("C")) {
						totem_o.setContrassegno(false);
					} else {
						totem_o.setContrassegno(true);
					}
					HibernateUtil.saveOrUpdate(totem_o);
					session.setAttribute("TOTEM_O", totem_o);
				}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}



}
