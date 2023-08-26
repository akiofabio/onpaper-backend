package com.LES.EcommerceOnPaper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bandeiras")
public class Bandeira {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ban_id")
	private long id;
	
	@Column(name = "ban_nome")
	private String nome;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Bandeira() {}

	public Bandeira( String nome) {
		super();
		this.nome = nome;
	}
}
