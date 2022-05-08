package com.LES.EcommerceOnPaper.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	private String idEndereco;
	
	@Column(name = "crr_ultimo_adicionado")
	private Date ultimoAdicionado;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@OrderBy
	private Set<Item> itens;
	
	public Carrinho() {}
	
	public Carrinho(Endereco endereco, Date ultimoAdicionado, Set<Item> itens) {
		super();
		this.endereco = endereco;
		this.ultimoAdicionado = ultimoAdicionado;
		this.itens = itens;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
