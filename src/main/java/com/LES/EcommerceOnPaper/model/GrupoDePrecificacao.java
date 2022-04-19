package com.LES.EcommerceOnPaper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grupo_de_precificacao")
public class GrupoDePrecificacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gpr_id")
	private long id;
	
	@Column(name = "gpr_nome")
	private String nome;
	
	@Column(name = "gpr_descricao")
	private String descricao;
	
	@Column(name = "gpr_margem_lucro_min")
	private double margemLucroMin;

	public GrupoDePrecificacao() {}
	
	public GrupoDePrecificacao(String nome, String descricao, double margemLucroMin) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.margemLucroMin = margemLucroMin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getMargemLucroMin() {
		return margemLucroMin;
	}

	public void setMargemLucroMin(double margemLucroMin) {
		this.margemLucroMin = margemLucroMin;
	}
}
