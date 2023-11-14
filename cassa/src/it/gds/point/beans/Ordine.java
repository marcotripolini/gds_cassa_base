package it.gds.point.beans;

import java.io.Serializable;
import java.util.Date;

public class Ordine implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long id_carrello;
	private Date data;
	private Long id_cliente;
	private String tessera;
	private Double importo;
	private Double spesesped;
	private Double totale;
	private Boolean pagato;
	private Date pagato_il;
	private String pagato_transaz;
	private Boolean gestito;
	private Date gestito_il;
	private String d_indirizzo;
	private String d_localita;
	private String d_provincia;
	private String d_cap;
	private String d_note;
	private String cod_referente_dest;
	private Boolean consegna_fascia_a;
	private Boolean consegna_fascia_b;
	private Boolean consegna_fascia_c;
	private Boolean contrassegno;
	private Long id_spesesped;
	private Long negozio;

	public Ordine() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_carrello() {
		return id_carrello;
	}

	public void setId_carrello(Long id_carrello) {
		this.id_carrello = id_carrello;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
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

	public Double getSpesesped() {
		return spesesped;
	}

	public void setSpesesped(Double spesesped) {
		this.spesesped = spesesped;
	}

	public Double getTotale() {
		return totale;
	}

	public void setTotale(Double totale) {
		this.totale = totale;
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

	public Boolean getGestito() {
		return gestito;
	}

	public void setGestito(Boolean gestito) {
		this.gestito = gestito;
	}

	public Date getGestito_il() {
		return gestito_il;
	}

	public void setGestito_il(Date gestito_il) {
		this.gestito_il = gestito_il;
	}

	public String getD_indirizzo() {
		return d_indirizzo;
	}

	public void setD_indirizzo(String d_indirizzo) {
		this.d_indirizzo = d_indirizzo;
	}

	public String getD_localita() {
		return d_localita;
	}

	public void setD_localita(String d_localita) {
		this.d_localita = d_localita;
	}

	public String getD_provincia() {
		return d_provincia;
	}

	public void setD_provincia(String d_provincia) {
		this.d_provincia = d_provincia;
	}

	public String getD_cap() {
		return d_cap;
	}

	public void setD_cap(String d_cap) {
		this.d_cap = d_cap;
	}

	public String getD_note() {
		return d_note;
	}

	public void setD_note(String d_note) {
		this.d_note = d_note;
	}

	public String getCod_referente_dest() {
		return cod_referente_dest;
	}

	public void setCod_referente_dest(String cod_referente_dest) {
		this.cod_referente_dest = cod_referente_dest;
	}

	public Boolean getConsegna_fascia_a() {
		return consegna_fascia_a;
	}

	public void setConsegna_fascia_a(Boolean consegna_fascia_a) {
		this.consegna_fascia_a = consegna_fascia_a;
	}

	public Boolean getConsegna_fascia_b() {
		return consegna_fascia_b;
	}

	public void setConsegna_fascia_b(Boolean consegna_fascia_b) {
		this.consegna_fascia_b = consegna_fascia_b;
	}

	public Boolean getConsegna_fascia_c() {
		return consegna_fascia_c;
	}

	public void setConsegna_fascia_c(Boolean consegna_fascia_c) {
		this.consegna_fascia_c = consegna_fascia_c;
	}

	public Boolean getContrassegno() {
		return contrassegno;
	}

	public void setContrassegno(Boolean contrassegno) {
		this.contrassegno = contrassegno;
	}

	public Long getId_spesesped() {
		return id_spesesped;
	}

	public void setId_spesesped(Long id_spesesped) {
		this.id_spesesped = id_spesesped;
	}

	public Long getNegozio() {
		return negozio;
	}

	public void setNegozio(Long negozio) {
		this.negozio = negozio;
	}

}
