package it.gds.point.actions;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ActionPaymentType extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private String ptype;
	private String ticketrest;

	public String execute() {
		session.setAttribute("PTYPE", getPtype());
		session.setAttribute("TICKETREST", getTicketrest());
		return NONE;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getTicketrest() {
		return ticketrest;
	}

	public void setTicketrest(String ticketrest) {
		this.ticketrest = ticketrest;
	}

}
