package it.gds.point.beans;

import java.util.Date;

public class TesseraAbbonamento {
	Integer id;
	String tessera;
	String codice_gds;
	Integer contatore;
	String descrizione;
	Date loading_date;
	String codice_abbonamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTessera() {
		return tessera;
	}

	public void setTessera(String tessera) {
		this.tessera = tessera;
	}

	public String getCodice_gds() {
		return codice_gds;
	}

	public void setCodice_gds(String codice_gds) {
		this.codice_gds = codice_gds;
	}

	public Integer getContatore() {
		return contatore;
	}

	public void setContatore(Integer contatore) {
		this.contatore = contatore;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getLoading_date() {
		return loading_date;
	}

	public void setLoading_date(Date loading_date) {
		this.loading_date = loading_date;
	}

	public String getCodice_abbonamento() {
		return codice_abbonamento;
	}

	public void setCodice_abbonamento(String codice_abbonamento) {
		this.codice_abbonamento = codice_abbonamento;
	}

}
