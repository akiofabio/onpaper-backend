package com.LES.EcommerceOnPaper.model;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_id")
	private long id;
	
	@Column(name = "pro_status")
	private String status;
	
	@Column(name = "pro_name")
	private String nome;
	
	@Column(name = "pro_descricao")
	private String descricao;
	
	@Column(name = "pro_codigo_de_barra")
	private String codigoDeBarra;
	
	@ManyToOne
    @JoinColumn(name = "fk_gdp_pro")
	private GrupoDePrecificacao grupoDePrecificacao;
	
	@Column(name = "pro_preco")
	private double preco;
	
	@Column(name = "pro_custo")
	private double custo;
	
	@Column(name = "pro_quantidade")
	private int quantidade;
	
	@Column(name = "pro_imagens")
	private String imagens;
	
	@OneToMany
	private Set<Inativacao> inativacoes;
	
	@OneToMany
	private Set<Ativacao> ativacoes;
	
	@Column(name = "pro_altura")
	private double altura;
	
	@Column(name = "pro_largura")
	private double largura;
	
	@Column(name = "pro_comprimento")
	private double comprimento;
	
	@Column(name = "pro_peso")
	private double peso;
	
	@OneToOne
	private Fabricante fabricante;
	
	@ManyToMany
	private Set<Categoria> categorias;
	
	@Column(name = "pro_destaque")
	private boolean destaque;
	
	@Column(name = "pro_quantidade_bloqueada")
	private int quantidadeBloqueada;
	
	public Produto() {}

	public Produto(String status, String nome, String descricao, String codigoDeBarra,
			GrupoDePrecificacao grupoDePrecificacao, double preco, double custo, int quantidade, String imagens,
			Set<Inativacao> inativacoes, Set<Ativacao> ativacoes, double altura, double largura, double comprimento,
			double peso, Fabricante fabricante, Set<Categoria> categorias, boolean destaque, int quantidadeBloqueada) {
		super();
		this.status = status;
		this.nome = nome;
		this.descricao = descricao;
		this.codigoDeBarra = codigoDeBarra;
		this.grupoDePrecificacao = grupoDePrecificacao;
		this.preco = preco;
		this.custo = custo;
		this.quantidade = quantidade;
		this.imagens = imagens;
		this.inativacoes = inativacoes;
		this.ativacoes = ativacoes;
		this.altura = altura;
		this.largura = largura;
		this.comprimento = comprimento;
		this.peso = peso;
		this.fabricante = fabricante;
		this.categorias = categorias;
		this.destaque = destaque;
		this.quantidadeBloqueada = quantidadeBloqueada;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigoDeBarra() {
		return codigoDeBarra;
	}

	public void setCodigoDeBarra(String codigoDeBarra) {
		this.codigoDeBarra = codigoDeBarra;
	}

	public GrupoDePrecificacao getGrupoDePrecificacao() {
		return grupoDePrecificacao;
	}

	public void setGrupoDePrecificacao(GrupoDePrecificacao grupoDePrecificacao) {
		this.grupoDePrecificacao = grupoDePrecificacao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getImagens() {
		return imagens;
	}

	public void setImagens(String imagens) {
		this.imagens = imagens;
	}

	public Set<Inativacao> getInativacoes() {
		return inativacoes;
	}

	public void setInativacoes(Set<Inativacao> inativacoes) {
		this.inativacoes = inativacoes;
	}

	public Set<Ativacao> getAtivacoes() {
		return ativacoes;
	}

	public void setAtivacoes(Set<Ativacao> ativacoes) {
		this.ativacoes = ativacoes;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getLargura() {
		return largura;
	}

	public void setLargura(double largura) {
		this.largura = largura;
	}

	public double getComprimento() {
		return comprimento;
	}

	public void setComprimento(double comprimento) {
		this.comprimento = comprimento;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<Categoria> categorias) {
		this.categorias = categorias;
	}

	public boolean isDestaque() {
		return destaque;
	}

	public void setDestaque(boolean destaque) {
		this.destaque = destaque;
	}

	public int getQuantidadeBloqueada() {
		return quantidadeBloqueada;
	}

	public void setQuantidadeBloqueada(int quantidadeBloqueada) {
		this.quantidadeBloqueada = quantidadeBloqueada;
	}

	
}
