package it.gds.point.beans;

import java.io.Serializable;
import java.util.Date;

public class NCarrellodett implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long id_cliente;
	private String codice_gds;
	private String descrizione;
	private Double prezzo;
	private Double quantita;
	private Double totale;
	private Boolean p_a_peso;
	private Boolean p_totem;
	private Boolean p_bar;
	private Long id_totem_order;
	private Date datetime_insert;
	private Boolean old;
	private Integer order;
	private String unita_misura;
	private Double peso;
	private String reparto;

	public NCarrellodett() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getCodice_gds() {
		return codice_gds;
	}

	public void setCodice_gds(String codice_gds) {
		this.codice_gds = codice_gds;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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

	public Boolean getP_a_peso() {
		return p_a_peso;
	}

	public void setP_a_peso(Boolean p_a_peso) {
		this.p_a_peso = p_a_peso;
	}

	public Boolean getP_totem() {
		return p_totem;
	}

	public void setP_totem(Boolean p_totem) {
		this.p_totem = p_totem;
	}

	public Boolean getP_bar() {
		return p_bar;
	}

	public void setP_bar(Boolean p_bar) {
		this.p_bar = p_bar;
	}

	public Long getId_totem_order() {
		return id_totem_order;
	}

	public void setId_totem_order(Long id_totem_order) {
		this.id_totem_order = id_totem_order;
	}

	public Date getDatetime_insert() {
		return datetime_insert;
	}

	public void setDatetime_insert(Date datetime_insert) {
		this.datetime_insert = datetime_insert;
	}

	public Boolean getOld() {
		return old;
	}

	public void setOld(Boolean old) {
		this.old = old;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getUnita_misura() {
		return unita_misura;
	}

	public void setUnita_misura(String unita_misura) {
		this.unita_misura = unita_misura;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getReparto() {
		return reparto;
	}

	public void setReparto(String reparto) {
		this.reparto = reparto;
	}

}
