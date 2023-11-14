package it.gds.point.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class OrdineNegozioPrint implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long id_cliente;
	private Double importo;
	private Boolean executed;
	private String type;
	private Boolean payed;
	private Boolean contrassegno;
	private Boolean stampato;

	private Set<NCarrellodett> carrello = new HashSet<NCarrellodett>(0);

	public OrdineNegozioPrint() {
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

	public Double getImporto() {
		return importo;
	}

	public void setImporto(Double importo) {
		this.importo = importo;
	}

	public Boolean getExecuted() {
		return executed;
	}

	public void setExecuted(Boolean executed) {
		this.executed = executed;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getPayed() {
		return payed;
	}

	public void setPayed(Boolean payed) {
		this.payed = payed;
	}

	public Boolean getContrassegno() {
		return contrassegno;
	}

	public void setContrassegno(Boolean contrassegno) {
		this.contrassegno = contrassegno;
	}

	public Set<NCarrellodett> getCarrello() {
		return carrello;
	}

	public void setCarrello(Set<NCarrellodett> carrello) {
		this.carrello = carrello;
	}

	public Boolean getStampato() {
		return stampato;
	}

	public void setStampato(Boolean stampato) {
		this.stampato = stampato;
	}

}
