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
	private String status;
	
	@Column(name = "ite_quantidade")
	private int quantidade;
	
	@Column(name = "ite_preco")
	private double preco;
		
	@Column(name = "ite_nomeProduto")
	private String nomeProduto;
	
	@Column(name = "ite_idProduto")
	private long idProduto;
	
	public Item() {}
	
	public Item(String status, int quantidade, double preco, String nomeProduto, long idProduto) {
		super();
		this.status = status;
		this.quantidade = quantidade;
		this.preco = preco;
		this.nomeProduto = nomeProduto;
		this.idProduto = idProduto;
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}
}
