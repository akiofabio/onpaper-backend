package com.LES.EcommerceOnPaper.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ped_id")
	private long id;
	
	@Column(name = "ped_status")
	private String status;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Item> itens;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<MeioDePagamento> meioDePagamentos;
	
	@Column(name = "ped_endereco")
	private String endereco;

	public Pedido() {}
	
	public Pedido(String status, Set<Item> itens, Set<MeioDePagamento> meioDePagamentos, String endereco) {
		this.status = status;
		this.itens = itens;
		this.meioDePagamentos = meioDePagamentos;
		this.endereco = endereco;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Item> getItens() {
		return itens;
	}

	public void setItens(Set<Item> itens) {
		this.itens = itens;
	}

	public Set<MeioDePagamento> getMeioDePagamentos() {
		return meioDePagamentos;
	}

	public void setMeioDePagamentos(Set<MeioDePagamento> meioDePagamentos) {
		this.meioDePagamentos = meioDePagamentos;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
