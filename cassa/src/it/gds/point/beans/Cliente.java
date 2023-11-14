package it.gds.point.beans;

import java.io.Serializable;

public class Cliente implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String email;
	private String cod_referente;
	private String codfiscale;
	private String nome;
	private String password;
	private String cognome;
	private String indirizzo;
	private String localita;
	private String provincia;
	private String cap;
	private String telefono_a;
	private String telefono_c;
	private Boolean attivo;
	private Boolean privacy;
	private Boolean privacy_a;
	private Boolean privacy_b;
	private String d_nominativo;
	private String d_indirizzo;
	private String d_localita;
	private String d_provincia;
	private String d_cap;
	private String d_note;
	private String codice_controllo;
	private Boolean abilita_contrassegno;
	private Tessera tessera;

	public Cliente() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCod_referente() {
		return cod_referente;
	}

	public void setCod_referente(String cod_referente) {
		this.cod_referente = cod_referente;
	}

	public String getCodfiscale() {
		return codfiscale;
	}

	public void setCodfiscale(String codfiscale) {
		this.codfiscale = codfiscale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getTelefono_a() {
		return telefono_a;
	}

	public void setTelefono_a(String telefono_a) {
		this.telefono_a = telefono_a;
	}

	public String getTelefono_c() {
		return telefono_c;
	}

	public void setTelefono_c(String telefono_c) {
		this.telefono_c = telefono_c;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	public Boolean getPrivacy() {
		return privacy;
	}

	public void setPrivacy(Boolean privacy) {
		this.privacy = privacy;
	}

	public Boolean getPrivacy_a() {
		return privacy_a;
	}

	public void setPrivacy_a(Boolean privacy_a) {
		this.privacy_a = privacy_a;
	}

	public Boolean getPrivacy_b() {
		return privacy_b;
	}

	public void setPrivacy_b(Boolean privacy_b) {
		this.privacy_b = privacy_b;
	}

	public String getD_nominativo() {
		return d_nominativo;
	}

	public void setD_nominativo(String d_nominativo) {
		this.d_nominativo = d_nominativo;
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

	public String getCodice_controllo() {
		return codice_controllo;
	}

	public void setCodice_controllo(String codice_controllo) {
		this.codice_controllo = codice_controllo;
	}

	public Boolean getAbilita_contrassegno() {
		return abilita_contrassegno;
	}

	public void setAbilita_contrassegno(Boolean abilita_contrassegno) {
		this.abilita_contrassegno = abilita_contrassegno;
	}

	public Tessera getTessera() {
		return tessera;
	}

	public void setTessera(Tessera tessera) {
		this.tessera = tessera;
	}

}
