package com.LES.EcommerceOnPaper.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inativacao")
public class Inativacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ina_id")
	private long id;
	
	@Column(name = "ina_motivo")
	private String motivo;
	
	@Column(name = "ina_data")
	private Date data;
	
	@Column(name = "ina_grupo")
	private String grupo;

	public Inativacao(String motivo, Date data, String grupo) {
		super();
		this.motivo = motivo;
		this.data = data;
		this.grupo = grupo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
}
