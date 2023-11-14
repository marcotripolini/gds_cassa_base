package it.gds.point.actions;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Cliente;

public class ActionComplete extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private Cliente cli;

	public String execute() {
		try {
			cli = (Cliente)session.getAttribute("CLIENTE");
			session.removeAttribute("SCONTO");
			if(cli != null) {
				String ptype = (String)session.getAttribute("PTYPE");
				if(ptype == null) {
					session.setAttribute("PTYPE", "CO");
				}
			} else {
				return ERROR;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
