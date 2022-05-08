package com.LES.EcommerceOnPaper.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ite_id")
	private long id;
	
	@Column(name = "ite_status")
	String status;
	
	@Column(name = "ite_quantidade")
	int quantidade;
	
	@Column(name = "ite_preco")
	double preco;
		
	@OneToOne
	Produto produto;
	
	public Item() {}
	
	public Item(String status, int quantidade, double preco, Produto produto) {
		super();
		this.status = status;
		this.quantidade = quantidade;
		this.preco = preco;
		this.produto = produto;
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

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
}
