package it.gds.point.actions;

import com.opensymphony.xwork2.ActionSupport;

public class ActionAdmin extends ActionSupport {
	// *********************************************************
	private static final long serialVersionUID = 1L;
	private String pwd;

	public String execute() {
		try {
			if (!getPwd().equals("5555")) {
				return ERROR;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
