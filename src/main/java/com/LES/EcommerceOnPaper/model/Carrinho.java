package com.LES.EcommerceOnPaper.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name = "carrinhos")
public class Carrinho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "crr_id")
	private long id;
	
	@Column(name = "crr_endereco")
	private String endereco;
	
	@Column(name = "crr_id_endereco")
	private long idEndereco;
	
	@Column(name = "crr_cep")
	private String cep;
	
	@Column(name = "crr_ultimo_adicionado")
	private Date ultimoAdicionado;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@javax.persistence.OrderBy(value = "id")
	private Set<Item> itens;
	
	public Carrinho() {}

	public Carrinho(String endereco, long idEndereco, String cep, Date ultimoAdicionado, Set<Item> itens) {
		super();
		this.endereco = endereco;
		this.idEndereco = idEndereco;
		this.cep = cep;
		this.ultimoAdicionado = ultimoAdicionado;
		this.itens = itens;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Date getUltimoAdicionado() {
		return ultimoAdicionado;
	}

	public void setUltimoAdicionado(Date ultimoAdicionado) {
		this.ultimoAdicionado = ultimoAdicionado;
	}

	public Set<Item> getItens() {
		return itens;
	}

	public void setItens(Set<Item> itens) {
		this.itens = itens;
	}
}
