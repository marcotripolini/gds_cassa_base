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


public class ActionScanTessera extends ActionSupport implements HQLQueries {

	private static final long serialVersionUID = 1L;
	private HttpServletResponse response = ServletActionContext.getResponse();
	private String barcode;

	public String execute() {
		// Connection conn = ((SessionImpl)HibernateUtil.getSessionFactory().openSession()).connection();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection (Config.getJDBCCONNECTION(), Config.getJDBCUSER(), Config.getJDBPASSWORD());
			conn.setAutoCommit(true);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM tessere_da_assegnare WHERE "
									+ "(tessera = '" + getBarcode() + "' OR codice_alternativo = '" + getBarcode() + "') "
									+ "AND used = b'0' "
									+ "AND codice_referente = 'SEDE' "
									+ "AND tessera NOT LIKE 'P%';");
			if(rs.next()) {
				String t = rs.getString("tessera");
				String cod = rs.getString("codice_alternativo");
				stmt.execute("UPDATE tessere_da_assegnare SET used = b'1' WHERE tessera = '" + t + "';");
				response.getWriter().print(t + "|" + cod);
			} else {
				response.getWriter().print(ERROR);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(conn != null)
					conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return NONE;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}
