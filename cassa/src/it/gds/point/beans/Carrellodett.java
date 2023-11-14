package it.gds.point.beans;

import java.io.Serializable;
import java.util.Date;

public class Carrellodett implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long id_sito;
	private Long id_carrello;
	private Long id_vetrina;
	private String codice_gds;
	private Double prezzo;
	private Double quantita;
	private Double totale;
	private String descrizione;
	private Long id_ordine;
	private Boolean pagato;
	private Date pagato_il;
	private String pagato_transaz;
	private String unita_misura;
	private String reparto;
	
	public Carrellodett() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_sito() {
		return id_sito;
	}

	public void setId_sito(Long id_sito) {
		this.id_sito = id_sito;
	}

	public Long getId_carrello() {
		return id_carrello;
	}

	public void setId_carrello(Long id_carrello) {
		this.id_carrello = id_carrello;
	}

	public Long getId_vetrina() {
		return id_vetrina;
	}

	public void setId_vetrina(Long id_vetrina) {
		this.id_vetrina = id_vetrina;
	}

	public String getCodice_gds() {
		return codice_gds;
	}

	public void setCodice_gds(String codice_gds) {
		this.codice_gds = codice_gds;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public Double getQuantita() {
		return quantita;
	}

	public void setQuantita(Double quantita) {
		this.quantita = quantita;
	}

	public Double getTotale() {
		return totale;
	}

	public void setTotale(Double totale) {
		this.totale = totale;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Long getId_ordine() {
		return id_ordine;
	}

	public void setId_ordine(Long id_ordine) {
		this.id_ordine = id_ordine;
	}

	public Boolean getPagato() {
		return pagato;
	}

	public void setPagato(Boolean pagato) {
		this.pagato = pagato;
	}

	public Date getPagato_il() {
		return pagato_il;
	}

	public void setPagato_il(Date pagato_il) {
		this.pagato_il = pagato_il;
	}

	public String getPagato_transaz() {
		return pagato_transaz;
	}

	public void setPagato_transaz(String pagato_transaz) {
		this.pagato_transaz = pagato_transaz;
	}

	public String getUnita_misura() {
		return unita_misura;
	}

	public void setUnita_misura(String unita_misura) {
		this.unita_misura = unita_misura;
	}

	public String getReparto() {
		return reparto;
	}

	public void setReparto(String reparto) {
		this.reparto = reparto;
	}

}
