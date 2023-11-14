package it.gds.point.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Prodotto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String codice_gds;
	private String ean;
	private Long id_fornitore;
	private Long id_produttore;
	private String titolo;
	private Double ivaperc;
	private Double imponibile;
	private Double ricarico;
	private Double acquisto_imponibile;
	private Double p_alpubblico_0;
	private Double p_alpubblico_1;
	private Double p_alpubblico_2;
	private Double p_alpubblico_3;
	private Double p_alpubblico_4;
	private Double p_alpubblico_5;
	private Double p_alpubblico_6;
	private Double p_alpubblico_7;
	private Double p_alpubblico_8;
	private Double p_alpubblico_9;
	private String unita_misura;
	private String quantita;
	private Boolean visibile;
	private String categoria_gds;
	private String chiavi_ricerca;
	private Integer priorita_ricerca;
	private String vita_media;
	private String nome_immagine;
	private Date data_modifica;
	private String dettaglio;
	private String offerta;
	private Integer superofferta;
	private Long qmvend;
	private Boolean visualizza_prezzo_unitario;
	private String car_immagini;
	private Date valido_dal;
	private Date valido_fino_al;
	private Double campo2;
	private Marchio marchio;
	private Double giacenza_magazzino;
	private Double scarico_magazzino;
	private String codice_prodotto_collegato_magazzino;
	private Integer azione;
	private String codice_gds_azione;
	private Integer valore;
	private Set<DiBa> distinta_base;

	public Prodotto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice_gds() {
		return codice_gds;
	}

	public void setCodice_gds(String codice_gds) {
		this.codice_gds = codice_gds;
	}

	public String getEan() {
		return ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public Long getId_fornitore() {
		return id_fornitore;
	}

	public void setId_fornitore(Long id_fornitore) {
		this.id_fornitore = id_fornitore;
	}

	public Long getId_produttore() {
		return id_produttore;
	}

	public void setId_produttore(Long id_produttore) {
		this.id_produttore = id_produttore;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Double getIvaperc() {
		return ivaperc;
	}

	public void setIvaperc(Double ivaperc) {
		this.ivaperc = ivaperc;
	}

	public Double getImponibile() {
		return imponibile;
	}

	public void setImponibile(Double imponibile) {
		this.imponibile = imponibile;
	}

	public Double getRicarico() {
		return ricarico;
	}

	public void setRicarico(Double ricarico) {
		this.ricarico = ricarico;
	}

	public Double getAcquisto_imponibile() {
		return acquisto_imponibile;
	}

	public void setAcquisto_imponibile(Double acquisto_imponibile) {
		this.acquisto_imponibile = acquisto_imponibile;
	}

	public Double getP_alpubblico_0() {
		return p_alpubblico_0;
	}

	public void setP_alpubblico_0(Double p_alpubblico_0) {
		this.p_alpubblico_0 = p_alpubblico_0;
	}

	public Double getP_alpubblico_1() {
		return p_alpubblico_1;
	}

	public void setP_alpubblico_1(Double p_alpubblico_1) {
		this.p_alpubblico_1 = p_alpubblico_1;
	}

	public Double getP_alpubblico_2() {
		return p_alpubblico_2;
	}

	public void setP_alpubblico_2(Double p_alpubblico_2) {
		this.p_alpubblico_2 = p_alpubblico_2;
	}

	public Double getP_alpubblico_3() {
		return p_alpubblico_3;
	}

	public void setP_alpubblico_3(Double p_alpubblico_3) {
		this.p_alpubblico_3 = p_alpubblico_3;
	}

	public Double getP_alpubblico_4() {
		return p_alpubblico_4;
	}

	public void setP_alpubblico_4(Double p_alpubblico_4) {
		this.p_alpubblico_4 = p_alpubblico_4;
	}

	public Double getP_alpubblico_5() {
		return p_alpubblico_5;
	}

	public void setP_alpubblico_5(Double p_alpubblico_5) {
		this.p_alpubblico_5 = p_alpubblico_5;
	}

	public Double getP_alpubblico_6() {
		return p_alpubblico_6;
	}

	public void setP_alpubblico_6(Double p_alpubblico_6) {
		this.p_alpubblico_6 = p_alpubblico_6;
	}

	public Double getP_alpubblico_7() {
		return p_alpubblico_7;
	}

	public void setP_alpubblico_7(Double p_alpubblico_7) {
		this.p_alpubblico_7 = p_alpubblico_7;
	}

	public Double getP_alpubblico_8() {
		return p_alpubblico_8;
	}

	public void setP_alpubblico_8(Double p_alpubblico_8) {
		this.p_alpubblico_8 = p_alpubblico_8;
	}

	public Double getP_alpubblico_9() {
		return p_alpubblico_9;
	}

	public void setP_alpubblico_9(Double p_alpubblico_9) {
		this.p_alpubblico_9 = p_alpubblico_9;
	}

	public String getUnita_misura() {
		return unita_misura;
	}

	public void setUnita_misura(String unita_misura) {
		this.unita_misura = unita_misura;
	}

	public String getQuantita() {
		return quantita;
	}

	public void setQuantita(String quantita) {
		this.quantita = quantita;
	}

	public Boolean getVisibile() {
		return visibile;
	}

	public void setVisibile(Boolean visibile) {
		this.visibile = visibile;
	}

	public String getCategoria_gds() {
		return categoria_gds;
	}

	public void setCategoria_gds(String categoria_gds) {
		this.categoria_gds = categoria_gds;
	}

	public String getChiavi_ricerca() {
		return chiavi_ricerca;
	}

	public void setChiavi_ricerca(String chiavi_ricerca) {
		this.chiavi_ricerca = chiavi_ricerca;
	}

	public Integer getPriorita_ricerca() {
		return priorita_ricerca;
	}

	public void setPriorita_ricerca(Integer priorita_ricerca) {
		this.priorita_ricerca = priorita_ricerca;
	}

	public String getVita_media() {
		return vita_media;
	}

	public void setVita_media(String vita_media) {
		this.vita_media = vita_media;
	}

	public String getNome_immagine() {
		return nome_immagine;
	}

	public void setNome_immagine(String nome_immagine) {
		this.nome_immagine = nome_immagine;
	}

	public Date getData_modifica() {
		return data_modifica;
	}

	public void setData_modifica(Date data_modifica) {
		this.data_modifica = data_modifica;
	}

	public String getDettaglio() {
		return dettaglio;
	}

	public void setDettaglio(String dettaglio) {
		this.dettaglio = dettaglio;
	}

	public String getOfferta() {
		return offerta;
	}

	public void setOfferta(String offerta) {
		this.offerta = offerta;
	}

	public Integer getSuperofferta() {
		return superofferta;
	}

	public void setSuperofferta(Integer superofferta) {
		this.superofferta = superofferta;
	}

	public Long getQmvend() {
		return qmvend;
	}

	public void setQmvend(Long qmvend) {
		this.qmvend = qmvend;
	}

	public Boolean getVisualizza_prezzo_unitario() {
		return visualizza_prezzo_unitario;
	}

	public void setVisualizza_prezzo_unitario(Boolean visualizza_prezzo_unitario) {
		this.visualizza_prezzo_unitario = visualizza_prezzo_unitario;
	}

	public String getCar_immagini() {
		return car_immagini;
	}

	public void setCar_immagini(String car_immagini) {
		this.car_immagini = car_immagini;
	}

	public Date getValido_dal() {
		return valido_dal;
	}

	public void setValido_dal(Date valido_dal) {
		this.valido_dal = valido_dal;
	}

	public Date getValido_fino_al() {
		return valido_fino_al;
	}

	public void setValido_fino_al(Date valido_fino_al) {
		this.valido_fino_al = valido_fino_al;
	}

	public Double getCampo2() {
		return campo2;
	}

	public void setCampo2(Double campo2) {
		this.campo2 = campo2;
	}

	public Marchio getMarchio() {
		return marchio;
	}

	public void setMarchio(Marchio marchio) {
		this.marchio = marchio;
	}

	public Double getGiacenza_magazzino() {
		return giacenza_magazzino;
	}

	public void setGiacenza_magazzino(Double giacenza_magazzino) {
		this.giacenza_magazzino = giacenza_magazzino;
	}

	public Double getScarico_magazzino() {
		return scarico_magazzino;
	}

	public void setScarico_magazzino(Double scarico_magazzino) {
		this.scarico_magazzino = scarico_magazzino;
	}

	public String getCodice_prodotto_collegato_magazzino() {
		return codice_prodotto_collegato_magazzino;
	}

	public void setCodice_prodotto_collegato_magazzino(String codice_prodotto_collegato_magazzino) {
		this.codice_prodotto_collegato_magazzino = codice_prodotto_collegato_magazzino;
	}

	public Integer getAzione() {
		return azione;
	}

	public void setAzione(Integer azione) {
		this.azione = azione;
	}

	public String getCodice_gds_azione() {
		return codice_gds_azione;
	}

	public void setCodice_gds_azione(String codice_gds_azione) {
		this.codice_gds_azione = codice_gds_azione;
	}

	public Integer getValore() {
		return valore;
	}

	public void setValore(Integer valore) {
		this.valore = valore;
	}

	public Set<DiBa> getDistinta_base() {
		return distinta_base;
	}

	public void setDistinta_base(Set<DiBa> distinta_base) {
		this.distinta_base = distinta_base;
	}
}
