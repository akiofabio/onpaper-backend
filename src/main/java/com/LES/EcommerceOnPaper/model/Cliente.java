package com.LES.EcommerceOnPaper.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="cli_id")
public class Cliente extends Usuario{
	
	@Column(name = "cli_status")
	private String status;
	
	@Column(name = "cli_name")
	private String nome;
	
	@Column(name = "cli_cpf")
	private String cpf;

	@Column(name = "cli_genero")
	private String genero;

	@Column(name = "cli_data_nascimento")
	private Date dataNascimento;
	
	@Column(name = "cli_score")
	private String score;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Telefone> telefones;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private Set<Endereco> enderecos;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Cartao> cartoes;

	@OneToMany
	private Set<Pedido> pedidos;

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Cupom> cupons;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Carrinho carrinho;

	public Cliente() {}
	
	public Cliente(String email, String senha, String tipo, String status, String nome, String cpf, String genero, Date dataNascimento, String score,
			Set<Telefone> telefones, Set<Endereco> enderecos, Set<Cartao> cartoes, Set<Pedido> pedidos, Set<Cupom> cupons, Carrinho carrinho) {
		super(email, senha, tipo);
		this.status = status;
		this.nome = nome;
		this.cpf = cpf;
		this.genero = genero;
		this.dataNascimento = dataNascimento;
		this.score = score;
		this.telefones = telefones;
		this.enderecos = enderecos;
		this.cartoes = cartoes;
		this.pedidos = pedidos;
		this.cupons = cupons;
		this.carrinho = carrinho;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<Cartao> getCartoes() {
		return cartoes;
	}

	public void setCartoes(Set<Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Set<Cupom> getCupons() {
		return cupons;
	}

	public void setCupons(Set<Cupom> cupons) {
		this.cupons = cupons;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
}
