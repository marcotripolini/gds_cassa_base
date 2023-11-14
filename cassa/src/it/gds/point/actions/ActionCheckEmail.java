package it.gds.point.actions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.impl.SessionImpl;

import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.interfaces.HQLQueries;
import it.gds.point.persistence.HibernateUtil;
import it.gds.point.utils.Config;

public class ActionCheckEmail extends ActionSupport implements HQLQueries {

	private static final long serialVersionUID = 1L;
	private HttpServletResponse response = ServletActionContext.getResponse();
	private String email;

	public String execute() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection (Config.getJDBCCONNECTION(), Config.getJDBCUSER(), Config.getJDBPASSWORD());
			conn.setAutoCommit(true);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT email FROM clienti WHERE email= '" + email + "'");
			if (rs.next()) {
				response.getWriter().print(ERROR);
			} else {
				response.getWriter().print("OK");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return NONE;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
