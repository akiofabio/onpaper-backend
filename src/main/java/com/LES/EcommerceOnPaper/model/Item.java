package com.LES.EcommerceOnPaper.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@OneToMany(cascade = {CascadeType.ALL})
	private Set<StatusItem> status;
	
	@Column(name = "ite_quantidade")
	private int quantidade;
	
	@Column(name = "ite_quantidade_trocada")
	private int quantidadeTrocada;
	
	@Column(name = "ite_quantidade_trocar")
	private int quantidadeTrocar;
	
	@Column(name = "ite_preco")
	private double preco;
		
	@Column(name = "ite_nome_produto")
	private String nomeProduto;
	
	@Column(name = "ite_image_produto")
	private String imagemProduto;
	
	@Column(name = "ite_id_produto")
	private long idProduto;
	
	@Column(name = "ite_disponibilidade")
	private String disponibilidade;
	
	@ManyToOne
	private Produto produto;

	
	
	public Item() {}

	public Item(Set<StatusItem> status, int quantidade, double preco, String nomeProduto, String imagemProduto, long idProduto) {
		super();
		this.status = status;
		this.quantidade = quantidade;
		this.preco = preco;
		this.nomeProduto = nomeProduto;
		this.imagemProduto = imagemProduto;
		this.idProduto = idProduto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<StatusItem> getStatus() {
		return status;
	}

	public void setStatus(Set<StatusItem> status) {
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

	public String getImagemProduto() {
		return imagemProduto;
	}

	public void setImagemProduto(String imagemProduto) {
		this.imagemProduto = imagemProduto;
	}

	public long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}

	public String getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(String disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
	public int getQuantidadeTrocada() {
		return quantidadeTrocada;
	}

	public void setQuantidadeTrocada(int quantidadeTrocada) {
		this.quantidadeTrocada = quantidadeTrocada;
	}

	public int getQuantidadeTrocar() {
		return quantidadeTrocar;
	}

	public void setQuantidadeTrocar(int quantidadeTrocar) {
		this.quantidadeTrocar = quantidadeTrocar;
	}

	public StatusItem getUltimoStatus() {
		StatusItem ultimoStatus = null;
		if(status !=null && !status.isEmpty()) {
			ultimoStatus = status.iterator().next();
			for(StatusItem st : status) {
				if(st.getData().isAfter(ultimoStatus.getData())) {
					ultimoStatus = st;
				}
			}
		}
		return ultimoStatus;
	}
	
}
