package it.gds.point.beans;

import java.io.Serializable;

public class Tavolo {
	private String id;
	private Long ordine;
	private Long id_cliente;
	private Double importo;
	private String tavolo;
	private String nome;
	private String cognome;
	private String tessera;
	private String nome_tavolo;
	private boolean libero;
	private Boolean stampato;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getOrdine() {
		return ordine;
	}

	public void setOrdine(Long ordine) {
		this.ordine = ordine;
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Double getImporto() {
		return importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}

	public String getTavolo() {
		return tavolo;
	}

	public void setTavolo(String tavolo) {
		this.tavolo = tavolo;
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

	public String getTessera() {
		return tessera;
	}

	public void setTessera(String tessera) {
		this.tessera = tessera;
	}

	public boolean isLibero() {
		if (this.id_cliente == null) {
			return false;
		} else {
			return true;
		}
	}

	public void setLibero(boolean libero) {
		this.libero = libero;
	}

	public String getNome_tavolo() {
		return nome_tavolo;
	}

	public void setNome_tavolo(String nome_tavolo) {
		this.nome_tavolo = nome_tavolo;
	}

	public Boolean isStampato() {
		return stampato;
	}

	public void setStampato(Boolean stampato) {
		this.stampato = stampato;
	}

}
