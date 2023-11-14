package it.gds.point.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class OrdineTotem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long id_cliente;
	private Double importo;
	private Boolean change_delivery;
	private Boolean time_a;
	private Boolean time_b;
	private Boolean time_c;
	private Boolean time_d;
	private Boolean executed;
	private String type;
	private Boolean payed;
	private Boolean contrassegno;
	private Set<NCarrellodett> carrello = new HashSet<NCarrellodett>(0);

	public OrdineTotem() {
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

	public Boolean getChange_delivery() {
		return change_delivery;
	}

	public void setChange_delivery(Boolean change_delivery) {
		this.change_delivery = change_delivery;
	}

	public Boolean getTime_a() {
		return time_a;
	}

	public void setTime_a(Boolean time_a) {
		this.time_a = time_a;
	}

	public Boolean getTime_b() {
		return time_b;
	}

	public void setTime_b(Boolean time_b) {
		this.time_b = time_b;
	}

	public Boolean getTime_c() {
		return time_c;
	}

	public void setTime_c(Boolean time_c) {
		this.time_c = time_c;
	}

	public Boolean getTime_d() {
		return time_d;
	}

	public void setTime_d(Boolean time_d) {
		this.time_d = time_d;
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
	
}
