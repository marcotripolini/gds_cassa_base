package it.gds.point.actions;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import it.gds.point.beans.Cliente;
import it.gds.point.beans.Tessera;
import it.gds.point.persistence.HibernateUtil;
import it.gds.point.utils.Config;

public class ActionSaveUser extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response = ServletActionContext.getResponse();
	private String nome;
	private String cognome;
	private String email;
	private String tessera;
	private String cap;
	private String localita;
	private String codice_alternativo;
	private Cliente cli;
	private Tessera t;

	public String execute() {
		try {
			cli = new Cliente();
			cli.setNome(getNome());
			cli.setCognome(getCognome());
			cli.setEmail(getEmail());
			cli.setPassword(getTessera());
			cli.setCap(cap.trim());
			cli.setLocalita(localita.trim());
			cli.setCod_referente("SEDE");
			cli.setAttivo(true);
			cli.setPrivacy(false);
			cli.setPrivacy_a(false);
			cli.setPrivacy_b(false);
			cli.setAbilita_contrassegno(true);
			t = new Tessera();
			t.setTessera(getTessera());
			t.setScore((long)0);
			t.setValore((double)0);
			t.setCodice_alternativo(getCodice_alternativo());
			t.setLivello_listino(Config.getNEWUSERLEVEL());
			t.setLivello_listino_successivo(Config.getNEWUSERNEXTLEVEL());
			t.setTot_fatturato((double)0);
			t.setDifferenza_x_listino_succ((double)250);
			t.setLivello_tessera(Config.getNEWUSERLEVEL());

			HibernateUtil.saveOrUpdate(t);

			cli.setTessera(t);

			HibernateUtil.saveOrUpdate(cli);

			response.getWriter().print(SUCCESS);

		} catch(Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTessera() {
		return tessera;
	}

	public void setTessera(String tessera) {
		this.tessera = tessera;
	}

	public String getCodice_alternativo() {
		return codice_alternativo;
	}

	public void setCodice_alternativo(String codice_alternativo) {
		this.codice_alternativo = codice_alternativo;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}
}
