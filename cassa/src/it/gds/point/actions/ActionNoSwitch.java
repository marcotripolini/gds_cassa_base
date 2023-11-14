package it.gds.point.actions;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ActionNoSwitch extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	
	public String execute() {
		session.setAttribute("NOSWITCH", true);
		return NONE;
	}

}
