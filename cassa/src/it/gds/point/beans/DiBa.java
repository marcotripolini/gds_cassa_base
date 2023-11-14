package it.gds.point.beans;

public class DiBa {
	private Long id;
	private String codice_gds;
	private String codice_gds_base;
	private Double quantita;

	public String getCodice_gds() {
		return codice_gds;
	}

	public void setCodice_gds(String codice_gds) {
		this.codice_gds = codice_gds;
	}

	public String getCodice_gds_base() {
		return codice_gds_base;
	}

	public void setCodice_gds_base(String codice_gds_base) {
		this.codice_gds_base = codice_gds_base;
	}

	public Double getQuantita() {
		return quantita;
	}

	public void setQuantita(Double quantita) {
		this.quantita = quantita;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
