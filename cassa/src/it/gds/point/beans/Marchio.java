package it.gds.point.beans;

import java.io.Serializable;

public class Marchio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String marchio;
	private String immagine;
	private Boolean visualizza;
	private String url;
	private String descrizione;
	
	public Marchio() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarchio() {
		return marchio;
	}

	public void setMarchio(String marchio) {
		this.marchio = marchio;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public Boolean getVisualizza() {
		return visualizza;
	}

	public void setVisualizza(Boolean visualizza) {
		this.visualizza = visualizza;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
}