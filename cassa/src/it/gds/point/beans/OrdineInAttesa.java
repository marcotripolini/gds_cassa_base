package it.gds.point.beans;

public class OrdineInAttesa {
	private long ordine;
	private long id_cliente;
	private String nome;
	private String cognome;
	private String tessera;
	private Double importo;
	private String tavolo;
	private Integer quantiarticoli;

	public long getOrdine() {
		return ordine;
	}

	public void setOrdine(long ordine) {
		this.ordine = ordine;
	}

	public long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(long id_cliente) {
		this.id_cliente = id_cliente;
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

	public Integer getQuantiarticoli() {
		return quantiarticoli;
	}

	public void setQuantiarticoli(Integer quantiarticoli) {
		this.quantiarticoli = quantiarticoli;
	}
}
