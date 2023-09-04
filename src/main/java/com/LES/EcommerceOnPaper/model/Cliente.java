package com.LES.EcommerceOnPaper.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
	private long cpf;

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

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	private Set<Cupom> cupons;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Carrinho carrinho;

	public Cliente() {}
	
	public Cliente(String email, String senha, String tipo, String status, String nome, long cpf, String genero, Date dataNascimento, String score,
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

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
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
	
	
	public String validarDadosObrigatorios() {
		StringBuilder msg = new StringBuilder();
		if(nome==null || nome.isEmpty()) {
			msg.append("O nome é Obrigatorio; ");
		}
		if(genero==null || genero.isEmpty()) {
			msg.append("O genero é Obrigatorio; ");
		}
		
		if(cpf<1) {
			msg.append("O CPF é Obrigatorio; ");
		}
		if(dataNascimento==null) {
			msg.append("A Data de Nascimento é Obrigatorio; ");
		}
		for(Cartao cart : cartoes) {
			msg.append(cart.validarDadosObrigatorios());
		}
		for(Endereco end : enderecos) {
			msg.append(end.validarDadosObrigatorios());
		}
		msg.append(validarEnderecoEntrega());
		msg.append(validarEnderecoCobrança());
		return msg.toString();
		
	}
	public Boolean validarCPF(long cpf) {
		
		return true;
	}
	
	public String validarEnderecoEntrega() {
		for(Endereco end : enderecos) {
			if(end.getEntrega()) {
				return "";
			}
		}
		return "É obrigatori ter pelo menos um Endereço de Entrega";
	}
	
	public String validarEnderecoCobrança() {
		for(Endereco end : enderecos) {
			if(end.getCobranca()) {
				return "";
			}
		}
		return "É obrigatori ter pelo menos um Endereço de Cobrança";
	}
}
