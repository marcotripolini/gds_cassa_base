package it.gds.point.actions;

import java.util.List;
import org.hibernate.Session;
import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.ClienteInCorso;
import it.gds.point.beans.OrdineInAttesa;
import it.gds.point.interfaces.HQLQueries;
import it.gds.point.persistence.HibernateUtil;

public class ActionSessioniAperte extends ActionSupport implements HQLQueries {
	private static final long serialVersionUID = 1L;
	private List<ClienteInCorso> ccd;
	private List<OrdineInAttesa> ona;

	@SuppressWarnings("unchecked")
	public String execute() {
		try {
			Session sx = HibernateUtil.getSessionFactory().openSession();
			ona = (List<OrdineInAttesa>) sx.createQuery("from OrdineInAttesa").list();
			sx.close();
		} catch(Exception e) {
			e.printStackTrace();
		}

//		try {
//			Connection conn = ((SessionImpl) HibernateUtil.getSessionFactory().openSession()).connection();
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery(
//				"SELECT id_cliente FROM point_ordini WHERE payed = b'0' ORDER BY id_cliente");
//			while(rs.next()) {
//				idc += rs.getLong("id_cliente") + ",";
//			}
//			conn.close();
//			if(!idc.equals("")) {
//				Session sx = HibernateUtil.getSessionFactory().openSession();
//				ccd = (List<ClienteInCorso>)sx.createQuery(
//					HQL_CLIDETT.replace(":idc", idc.substring(0, idc.length() - 1))).list();
//				sx.close();
//			} else {
//				ccd = null;
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		return SUCCESS;
	}

	public List<ClienteInCorso> getCcd() {
		return ccd;
	}

	public List<OrdineInAttesa> getOna() {
		return ona;
	}

	public void setOna(List<OrdineInAttesa> ona) {
		this.ona = ona;
	}

}
