package com.LES.EcommerceOnPaper.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pedidos")
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ped_id")
	private long id;
	
	@javax.persistence.OrderBy(value = "id")
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	private Set<StatusPedido> status;
	
	@OneToOne
	private StatusPedido ultimoStatus;
	
	@javax.persistence.OrderBy(value = "id")
	@OneToMany(cascade = {CascadeType.MERGE})
	private Set<Item> itens;
	
	@javax.persistence.OrderBy(value = "id")
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<MeioDePagamento> meioDePagamentos;
	
	@Column(name = "ped_id_endereco")
	private long idEndereco;
	
	@Column(name = "ped_endereco")
	private String endereco;
	
	@Column(name = "ped_cep")
	private String cep;
	
	@Column(name = "ped_frete")
	private float frete;
	
	@ManyToOne
	private Endereco enderecoObj;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cli_id")
	@JsonIgnore
	private Cliente cliente;
	
	@Column(name = "ped_motivo_devolucao")
	private String motivoDevolucao;
	
	public Pedido() {}

	public Pedido(Set<StatusPedido> pedidoStatus, Set<Item> itens, Set<MeioDePagamento> meioDePagamentos, long idEndereco,
			String endereco, String cep, float frete) {
		super();
		this.status = pedidoStatus;
		this.itens = itens;
		this.meioDePagamentos = meioDePagamentos;
		this.idEndereco = idEndereco;
		this.endereco = endereco;
		this.cep = cep;
		this.frete = frete;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<StatusPedido> getStatus() {
		return status;
	}

	public void setStatus(Set<StatusPedido> status) {
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

	public long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public float getFrete() {
		return frete;
	}

	public void setFrete(float frete) {
		this.frete = frete;
	}
	
	public Endereco getEnderecoObj() {
		return enderecoObj;
	}

	public void setEnderecoObj(Endereco enderecoObj) {
		this.enderecoObj = enderecoObj;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void setUltimoStatus(StatusPedido ultimoStatus) {
		this.ultimoStatus = ultimoStatus;
	}

	public StatusPedido getUltimoStatus() {
		return ultimoStatus;
	}
	
	public double getTotal() {
		double total = 0;
		total = frete;
		for(Item item : itens) {
			total+= item.getQuantidade() * item.getPreco();
		}
		return total;
	}
}
