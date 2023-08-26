package com.LES.EcommerceOnPaper.model;

import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usu_id")
	private long id;
	
	@Column(name = "usu_email")
	private String email;
	
	@Column(name = "usu_nome")
	private String senha;
	
	@Column(name = "usu_tipo")
	private String tipo;

	public Usuario() {}
	
	public Usuario(String email, String senha, String tipo) {
		super();
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String validarSenha() {
		StringBuilder msg = new StringBuilder();
		Pattern p;
		if(senha.length()<8) {
			msg.append("A Senha deve ter pelo menos 8 digitos");
		}
		
		p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		if(!p.matcher(senha).find()) {
			msg.append("A Senha deve conter pelo menos um caracters especial; ");
		}
		
		p = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
		if(!p.matcher(senha).find()) {
			msg.append("A Senha deve conter pelo menos um mumero; ");
		}
		
		p = Pattern.compile("[a-z]");
		if(!p.matcher(senha).find()) {
			msg.append("A Senha deve conter pelo menos uma letra minuscula; ");
		}
		
		p = Pattern.compile("[A-Z]");
		if(!p.matcher(senha).find()) {
			msg.append("A Senha deve conter pelo menos uma letra maiuscula; ");
		}
		return msg.toString();
	}
}
