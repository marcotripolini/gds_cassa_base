package it.gds.point.actions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Cliente;
import it.gds.point.beans.OrdineNegozio;
import it.gds.point.beans.OrdineTotem;
import it.gds.point.interfaces.HQLQueries;
import it.gds.point.persistence.HibernateUtil;
import it.gds.point.utils.Config;

public class ActionLogin extends ActionSupport implements HQLQueries {
	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private Cliente cli;
	private OrdineTotem totem_o;
	private OrdineNegozio bar_o;
	private List<OrdineNegozio> l_bar_o;

	private String tessera;
	private String password;
	private String order;
	private String table;

	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			session.removeAttribute("SCONTO");
			Session sx = HibernateUtil.getSessionFactory().openSession();
			if (table != null) {
				List<Cliente> clis = sx.createQuery (HQL_LOGIN).setParameter("tessera", "NOCARD").list();
				if (clis.size()  > 0) {
					cli = (Cliente) clis.get(0);
				}

				//	cli = (Cliente)sx.createQuery(HQL_LOGIN).setParameter("tessera", "NOCARD").uniqueResult();

				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				try {
					conn = DriverManager.getConnection (Config.getJDBCCONNECTION(), Config.getJDBCUSER(), Config.getJDBPASSWORD());
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select ordine from status_tavoli where tavolo = '" + table.trim() + "' order by ordine");
					int i = 0;
					String str= "";
					while (rs.next()) {
						str += "'" + rs.getString("ordine") + "'" + ", ";
						i++;
					}
					str = str.substring(0,str.length() -2 );
					if (i > 1) {
						l_bar_o =(List<OrdineNegozio>)sx.createQuery(HQL_BARORDER_ID_LIST.replace("__ORDERS__", str)).list();
						bar_o = l_bar_o.get(0);
						for (int j=1; j< l_bar_o.size(); j++ ) {
							OrdineNegozio bar_o_b = l_bar_o.get(j);
							bar_o.setImporto(bar_o.getImporto() + bar_o_b.getImporto());
							bar_o.getCarrello().addAll(bar_o_b.getCarrello());

							sx.getTransaction().setTimeout(10);
							sx.getTransaction().begin();
							sx.saveOrUpdate(bar_o);
							sx.createQuery("delete from OrdineNegozio where id='" + bar_o_b.getId() + "'")
									.executeUpdate();
							sx.getTransaction().commit();
						}

						clis = sx.createQuery (HQL_LOGIN_ID).setParameter("id", bar_o.getId_cliente()).list();
						if (clis.size()  > 0) {
							cli = (Cliente) clis.get(0);
						}
					} else {
						bar_o = (OrdineNegozio)sx.createQuery(HQL_BARORDER_ID).setParameter("id_ordine", Long.parseLong(str.replace("'",""))).uniqueResult();
						clis = sx.createQuery (HQL_LOGIN_ID).setParameter("id", bar_o.getId_cliente()).list();

						if (clis.size()  > 0) {
							cli = (Cliente) clis.get(0);
						}
					}
					session.setAttribute("CLIENTE", cli);
					session.setAttribute("TOTEM_O", totem_o);
					session.setAttribute("BAR_O", bar_o);
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
			} else {
				List<Cliente> clis = sx.createQuery (HQL_LOGIN).setParameter("tessera",  getTessera()).list();
				if (clis.size()  > 0) {
					cli = (Cliente) clis.get(0);
				}
//				cli = (Cliente)sx.createQuery(HQL_LOGIN).setParameter("tessera", getTessera()).uniqueResult();
				if(cli != null) {
					totem_o = (OrdineTotem)sx.createQuery(HQL_TOTEMORDER).setParameter("id_cliente", cli.getId()).uniqueResult();
					if (order != null) {
						bar_o = (OrdineNegozio)sx.createQuery(HQL_BARORDER_ID).setParameter("id_ordine", Long.parseLong(order)).uniqueResult();
					} else {
						bar_o = null;
					}
					// old version
					// bar_o = (OrdineNegozio)sx.createQuery(HQL_BARORDER).setParameter("id_cliente", cli.getId()).uniqueResult();
					if (bar_o == null) {
						bar_o = new OrdineNegozio();
						bar_o.setId_cliente(cli.getId());
						bar_o.setImporto((double)0);
						bar_o.setExecuted(true);
						bar_o.setType("B");
						bar_o.setPayed(false);
						bar_o.setContrassegno(false);
						HibernateUtil.saveOrUpdate(bar_o);
					}
					session.setAttribute("CLIENTE", cli);
					session.setAttribute("TOTEM_O", totem_o);
					session.setAttribute("BAR_O", bar_o);
				} else {
					sx.close();
					return ERROR;
				}
			}

			sx.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getTessera() {
		return tessera;
	}
	public void setTessera(String tessera) {
		this.tessera = tessera;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
}
