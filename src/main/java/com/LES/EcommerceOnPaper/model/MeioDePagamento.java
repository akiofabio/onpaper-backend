package com.LES.EcommerceOnPaper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "meios_pagamentos")
public class MeioDePagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mei_id")
	private long id;
	
	@Column(name = "mei_tipo")
	private String tipo;
	
	@Column(name = "mei_detalhes")
	private String detalhes;
	
	@Column(name = "mei_valor")
	private double valor;
	
	@Column(name = "mei_idTipo")
	private long idTipo;
	
	public MeioDePagamento() {}
	
	public MeioDePagamento(String tipo, String detalhes, double valor) {
		this.tipo = tipo;
		this.detalhes = detalhes;
		this.valor = valor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(long idTipo) {
		this.idTipo = idTipo;
	}
	
	
}
