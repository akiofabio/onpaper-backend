package com.LES.EcommerceOnPaper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enderecos")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "end_id")
	private long id;
	
	@Column(name = "end_nome")
	private String nome;
	
	@Column(name = "end_cep")
	private String cep;
	
	@Column(name = "end_pais")
	private String pais;
	
	@Column(name = "end_estado")
	private String estado;

	@Column(name = "end_cidade")
	private String cidade;

	@Column(name = "end_bairro")
	private String bairro;

	@Column(name = "end_tipo")
	private String tipo;
	
	@Column(name = "end_tipoLogradouro")
	private String tipoLogradouro;

	@Column(name = "end_logradouro")
	private String logradouro;

	@Column(name = "end_numero")
	private String numero;

	@Column(name = "end_observacao")
	private String observacao;

	@Column(name = "end_cobranca")
	private Boolean cobranca;
	
	@Column(name = "end_entrega")
	private Boolean entrega;
	
	public Endereco() {}
	
	public Endereco(String nome, String cep, String pais, String estado, String cidade, String bairro, String tipo,
			String tipoLogradouro, String logradouro, String numero, String observacao, Boolean cobranca,
			Boolean entrega) {
		super();
		this.nome = nome;
		this.cep = cep;
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.tipo = tipo;
		this.tipoLogradouro = tipoLogradouro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.observacao = observacao;
		this.cobranca = cobranca;
		this.entrega = entrega;
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Boolean getCobranca() {
		return cobranca;
	}

	public void setCobranca(Boolean cobranca) {
		this.cobranca = cobranca;
	}

	public Boolean getEntrega() {
		return entrega;
	}

	public void setEntrega(Boolean entrega) {
		this.entrega = entrega;
	}
	
	public String validarDadosObrigatorios() {
		StringBuilder msg = new StringBuilder();
		if(nome==null || nome.isEmpty()) {
			msg.append("O Nome do Ensdereco é Obrigatorio; ");
		}
		if(cep==null || cep.isEmpty()) {
			msg.append("O CEP é Obrigatorio; ");
		}
		
		if(pais==null || pais.isEmpty()) {
			msg.append("O País é Obrigatorio; ");
		}
		
		if(estado==null || estado.isEmpty()) {
			msg.append("O Estado é Obrigatorio; ");
		}
		
		if(cidade==null || cidade.isEmpty()) {
			msg.append("A Cidade é Obrigatoria; ");
		}
		
		if(bairro==null || bairro.isEmpty()) {
			msg.append("O Bairro é Obrigatorio; ");
		}
		
		if(tipo==null || tipo.isEmpty()) {
			msg.append("O País é Obrigatorio; ");
		}
		
		if(tipoLogradouro==null || tipoLogradouro.isEmpty()) {
			msg.append("O Tipo de Logradouro é Obrigatorio; ");
		}
		
		if(logradouro==null || logradouro.isEmpty()) {
			msg.append("O Logradouro é Obrigatorio; ");
		}
		
		if(numero==null || numero.isEmpty()) {
			msg.append("O Numero é Obrigatorio; ");
		}
		
		if(entrega==null ) {
			msg.append("A Entrega é Obrigatoria; ");
		}
		if(cobranca==null ) {
			msg.append("A Cobranca é Obrigatoria; ");
		}
		return msg.toString();
	}
	
}
