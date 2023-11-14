package it.gds.point.actions;

import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Cliente;
import it.gds.point.beans.NCarrellodett;
import it.gds.point.beans.OrdineNegozio;
import it.gds.point.beans.OrdineTotem;
import it.gds.point.beans.Prodotto;
import it.gds.point.interfaces.HQLQueries;
import it.gds.point.persistence.HibernateUtil;


public class ActionSwitch extends ActionSupport implements HQLQueries {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private String tessera;
	private Cliente cli;
	private OrdineNegozio bar_o;
	private OrdineTotem totem_o;
	
	public String execute() {
		try {
			Session sx = HibernateUtil.getSessionFactory().openSession();
			cli = (Cliente)sx.createQuery(HQL_LOGIN).setParameter("tessera", getTessera()).uniqueResult();
			if(cli != null) {
				
				// ordine negozio
				
				bar_o = (OrdineNegozio)session.getAttribute("BAR_O");
				if(bar_o != null) {
					Double importo = (double)0;
					bar_o.setId_cliente(cli.getId());
					Iterator<NCarrellodett> i = bar_o.getCarrello().iterator();
					while(i.hasNext()) {
						NCarrellodett d = (NCarrellodett)i.next();
						Prodotto p = (Prodotto)sx.createQuery(HQL_PRODBYCOD).setParameter("codice_gds", d.getCodice_gds()).uniqueResult();
						if(p != null) {
							if(d.getP_a_peso()) {
								switch(cli.getTessera().getLivello_listino()) {
									case "0":
										d.setPrezzo(p.getP_alpubblico_1() * d.getPeso());
										break;
									case "1":
										d.setPrezzo(p.getP_alpubblico_2() * d.getPeso());
										break;
									case "2":
										d.setPrezzo(p.getP_alpubblico_3() * d.getPeso());
										break;
									case "3":
										d.setPrezzo(p.getP_alpubblico_4() * d.getPeso());
										break;
									default:
										d.setPrezzo(p.getP_alpubblico_4() * d.getPeso());
										break;
								}
								d.setTotale(d.getPrezzo());
							} else {
								switch(cli.getTessera().getLivello_listino()) {
									case "0":
										d.setPrezzo(p.getP_alpubblico_1());
										break;
									case "1":
										d.setPrezzo(p.getP_alpubblico_2());
										break;
									case "2":
										d.setPrezzo(p.getP_alpubblico_3());
										break;
									case "3":
										d.setPrezzo(p.getP_alpubblico_4());
										break;
									default:
										d.setPrezzo(p.getP_alpubblico_4());
										break;
								}
								d.setTotale(d.getPrezzo() * d.getQuantita());
							}
							
							d.setId_cliente(cli.getId());
							importo = importo + d.getPrezzo();
							
						}
					}
					
					bar_o.setImporto(importo);
					HibernateUtil.saveOrUpdate(bar_o);
					session.setAttribute("BAR_O", bar_o);
				}
				
				// ordine totem
				
				totem_o = (OrdineTotem)sx.createQuery(HQL_TOTEMORDER).setParameter("id_cliente", cli.getId()).uniqueResult();
				session.setAttribute("TOTEM_O", totem_o);
				
				session.setAttribute("CLIENTE", cli);
				response.getWriter().print(SUCCESS);
			} else {
				response.getWriter().print(ERROR);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	public String getTessera() {
		return tessera;
	}

	public void setTessera(String tessera) {
		this.tessera = tessera;
	}

}
