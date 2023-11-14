package it.gds.point.actions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Cliente;
import it.gds.point.utils.Config;

public class ActionTables extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private Cliente cli;
	private String table;
	private String order_id;

	public String execute() {
		try {
			if (table != null && order_id != null) {
				if (!table.equals("") && !order_id.equals("")) {
					String sql = "update point_ordini set table_loc ='" + table + "' where id = '" + order_id + "'";
					Connection conn = null;
					Statement stmt = null;
					try {
						conn = DriverManager.getConnection(Config.getJDBCCONNECTION(), Config.getJDBCUSER(),
								Config.getJDBPASSWORD());
						conn.setAutoCommit(true);
						stmt = conn.createStatement();
						stmt.executeUpdate(sql);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							if (stmt != null)
								stmt.close();
							if (conn != null)
								conn.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

}
