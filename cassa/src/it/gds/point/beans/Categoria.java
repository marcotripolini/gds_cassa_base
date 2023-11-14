package it.gds.point.beans;

import java.io.Serializable;

public class Categoria implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String categoria_gds;
	private String categoria_1;
	private String categoria_2;
	private String categoria_3;
	private String categoria_4;
	private Integer attiva_sn;
	private Integer tipo;
	private String categoria_menu;
	
	public Categoria() {
		super();
	}

	public String getCategoria_gds() {
		return categoria_gds;
	}

	public void setCategoria_gds(String categoria_gds) {
		this.categoria_gds = categoria_gds;
	}

	public String getCategoria_1() {
		return categoria_1;
	}

	public void setCategoria_1(String categoria_1) {
		this.categoria_1 = categoria_1;
	}

	public String getCategoria_2() {
		return categoria_2;
	}

	public void setCategoria_2(String categoria_2) {
		this.categoria_2 = categoria_2;
	}

	public String getCategoria_3() {
		return categoria_3;
	}

	public void setCategoria_3(String categoria_3) {
		this.categoria_3 = categoria_3;
	}

	public String getCategoria_4() {
		return categoria_4;
	}

	public void setCategoria_4(String categoria_4) {
		this.categoria_4 = categoria_4;
	}

	public Integer getAttiva_sn() {
		return attiva_sn;
	}

	public void setAttiva_sn(Integer attiva_sn) {
		this.attiva_sn = attiva_sn;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getCategoria_menu() {
		return categoria_menu;
	}

	public void setCategoria_menu(String categoria_menu) {
		this.categoria_menu = categoria_menu;
	}
	
}
