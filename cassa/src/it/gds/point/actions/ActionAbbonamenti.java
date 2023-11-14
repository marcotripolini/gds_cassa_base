package it.gds.point.actions;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Cliente;
import it.gds.point.beans.NCarrellodett;
import it.gds.point.beans.OrdineNegozio;
import it.gds.point.beans.TesseraAbbonamento;
import it.gds.point.persistence.HibernateUtil;

public class ActionAbbonamenti extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private HttpSession session = ServletActionContext.getRequest().getSession();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private Cliente cli;
	private String id_abbonamento;
	private String codice_gds;
	private String id_cliente;

	public String executeScala_1() {
		try {
			cli = (Cliente) session.getAttribute("CLIENTE");

			if (cli == null) {
				return ERROR;
			}

			if (id_abbonamento == null || id_abbonamento.equals("")) {
				return ERROR;
			}

			if (codice_gds == null || codice_gds.equals("")) {
				return ERROR;
			}

			OrdineNegozio ordine = (OrdineNegozio)session.getAttribute("BAR_O");
			Set<NCarrellodett> carrello = ordine.getCarrello();
			Iterator<NCarrellodett> it = carrello.iterator();

			while (it.hasNext()) {
				NCarrellodett nCarrellodett = (NCarrellodett) it.next();
				if (nCarrellodett.getCodice_gds().equals(codice_gds)) {
					if (nCarrellodett.getQuantita() > 0) {
						nCarrellodett.setQuantita(nCarrellodett.getQuantita() - 1);
						nCarrellodett.setTotale(nCarrellodett.getQuantita() * nCarrellodett.getPrezzo());
						ordine.setImporto(ordine.getImporto() - nCarrellodett.getPrezzo());
						// **************************************************************
						// scarico abbonamento
						// **************************************************************
						Set abbonamenti = cli.getTessera().getAbbonamenti();
						Iterator it1 = abbonamenti.iterator();
						while (it1.hasNext()) {
							TesseraAbbonamento abbonamento = (TesseraAbbonamento) it1.next();
							if (abbonamento.getId() == Integer.decode(id_abbonamento)) {
								abbonamento.setContatore(abbonamento.getContatore() - 1);
							}
						}
						if (nCarrellodett.getQuantita() == 0) {
							carrello.remove(nCarrellodett);
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Cliente getCli() {
		try {
			cli = (Cliente) session.getAttribute("CLIENTE");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return cli;
	}

	public void setCli(Cliente cli) {
		this.cli = cli;
	}

	public String getId_abbonamento() {
		return id_abbonamento;
	}

	public void setId_abbonamento(String id_abbonamento) {
		this.id_abbonamento = id_abbonamento;
	}

	public String getCodice_gds() {
		return codice_gds;
	}

	public void setCodice_gds(String codice_gds) {
		this.codice_gds = codice_gds;
	}

	public String getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
}
