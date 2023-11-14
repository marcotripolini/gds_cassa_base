package it.gds.point.beans;

import java.io.Serializable;

public class CodiceSconto implements Serializable  {
	private static final long serialVersionUID = 1L;
	String codice_sconto;
	String categorie;
	boolean usa_tessera;
	Double sconto_perc;

	public String getCodice_sconto() {
		return codice_sconto;
	}

	public void setCodice_sconto(String codice_sconto) {
		this.codice_sconto = codice_sconto;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public boolean isUsa_tessera() {
		return usa_tessera;
	}

	public void setUsa_tessera(boolean usa_tessera) {
		this.usa_tessera = usa_tessera;
	}

	public Double getSconto_perc() {
		return sconto_perc;
	}

	public void setSconto_perc(Double sconto_perc) {
		this.sconto_perc = sconto_perc;
	}
}
