package it.gds.point.beans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

public class ClienteInCorso implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String tessera;
	private String nome;
	private String cognome;
	private Set<NCarrellodett> cd;
	private Double tt = (double) 0;
	private Double nt = (double) 0;

	public ClienteInCorso() {
		super();
	}


	@SuppressWarnings("rawtypes")
	public Double getTt() {
		try {
			Iterator i = cd.iterator();
			while(i.hasNext()) {
				NCarrellodett el = (NCarrellodett)i.next();
				if(el.getP_totem() == true)
					tt = tt + el.getTotale();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return tt;
	}
	@SuppressWarnings("rawtypes")
	public Double getNt() {
		try {
			Iterator i = cd.iterator();
			while(i.hasNext()) {
				NCarrellodett el = (NCarrellodett)i.next();
				if(el.getP_totem() == false)
					nt = nt + el.getTotale();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return nt;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTessera() {
		return tessera;
	}


	public void setTessera(String tessera) {
		this.tessera = tessera;
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


	public Set<NCarrellodett> getCd() {
		return cd;
	}


	public void setCd(Set<NCarrellodett> cd) {
		this.cd = cd;
	}
}
