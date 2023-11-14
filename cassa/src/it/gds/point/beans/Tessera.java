package it.gds.point.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Tessera implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String tessera;
	private Date assign_date;
	private Date last_use_date;
	private Long score;
	private Double valore;
	private String codice_alternativo;
	private String livello_listino;
	private String livello_listino_successivo;
	private Double tot_fatturato;
	private Double differenza_x_listino_succ;
	private String periodo_fatturato;
	private String livello_tessera;
	private Set<TesseraAbbonamento> abbonamenti;

	public Tessera() {
		super();
	}

	public String getTessera() {
		return tessera;
	}

	public void setTessera(String tessera) {
		this.tessera = tessera;
	}

	public Date getAssign_date() {
		return assign_date;
	}

	public void setAssign_date(Date assign_date) {
		this.assign_date = assign_date;
	}

	public Date getLast_use_date() {
		return last_use_date;
	}

	public void setLast_use_date(Date last_use_date) {
		this.last_use_date = last_use_date;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Double getValore() {
		return valore;
	}

	public void setValore(Double valore) {
		this.valore = valore;
	}

	public String getCodice_alternativo() {
		return codice_alternativo;
	}

	public void setCodice_alternativo(String codice_alternativo) {
		this.codice_alternativo = codice_alternativo;
	}

	public String getLivello_listino() {
		return livello_listino;
	}

	public void setLivello_listino(String livello_listino) {
		this.livello_listino = livello_listino;
	}

	public String getLivello_listino_successivo() {
		return livello_listino_successivo;
	}

	public void setLivello_listino_successivo(String livello_listino_successivo) {
		this.livello_listino_successivo = livello_listino_successivo;
	}

	public Double getTot_fatturato() {
		return tot_fatturato;
	}

	public void setTot_fatturato(Double tot_fatturato) {
		this.tot_fatturato = tot_fatturato;
	}

	public Double getDifferenza_x_listino_succ() {
		return differenza_x_listino_succ;
	}

	public void setDifferenza_x_listino_succ(Double differenza_x_listino_succ) {
		this.differenza_x_listino_succ = differenza_x_listino_succ;
	}

	public String getPeriodo_fatturato() {
		return periodo_fatturato;
	}

	public void setPeriodo_fatturato(String periodo_fatturato) {
		this.periodo_fatturato = periodo_fatturato;
	}

	public String getLivello_tessera() {
		return livello_tessera;
	}

	public void setLivello_tessera(String livello_tessera) {
		this.livello_tessera = livello_tessera;
	}

	public Set<TesseraAbbonamento> getAbbonamenti() {
		return abbonamenti;
	}

	public void setAbbonamenti(Set<TesseraAbbonamento> abbonamenti) {
		this.abbonamenti = abbonamenti;
	}

}
