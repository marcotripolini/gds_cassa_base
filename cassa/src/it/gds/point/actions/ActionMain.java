package it.gds.point.actions;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Cliente;

public class ActionMain extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private Cliente cli;

	public String execute() {
		try {
			cli = (Cliente) session.getAttribute("CLIENTE");
			if (cli == null) {
				return ERROR;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String execute_scontrino () {
		try {
			cli = (Cliente) session.getAttribute("CLIENTE");
			if (cli == null) {
				return ERROR;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
