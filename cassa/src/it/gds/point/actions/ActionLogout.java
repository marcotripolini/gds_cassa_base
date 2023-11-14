package it.gds.point.actions;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


public class ActionLogout extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() {
		try {
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.invalidate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
