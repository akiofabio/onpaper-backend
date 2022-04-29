package com.LES.EcommerceOnPaper.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cartoes")
public class Cartao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private long id;
	
	@Column(name = "car_nome")
	private String nome;
	
	@Column(name = "car_numero")
	private String numero;
	
	@Column(name = "car_codigo_seguranca")
	private String codigoSeguranca;
	
	@Column(name = "car_validade")
	private Date validade;
	
	@Column(name = "car_preferencial")
	private boolean preferencial;
	
	@Column(name = "car_bandeira")
	private String bandeira;
	
	public Cartao() {}
	
	public Cartao(String nome, String numero, String codigoSeguranca, Date validade, boolean preferencial,
			String bandeira) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.codigoSeguranca = codigoSeguranca;
		this.validade = validade;
		this.preferencial = preferencial;
		this.bandeira = bandeira;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCodigoSeguranca() {
		return codigoSeguranca;
	}

	public void setCodigoSeguranca(String codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public boolean isPreferencial() {
		return preferencial;
	}

	public void setPreferencial(boolean preferencial) {
		this.preferencial = preferencial;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}
	
}
