package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Usuario;
import com.LES.EcommerceOnPaper.repository.UsuarioRepository;

@Service
public class UsuarioService {

	final UsuarioRepository repository;
	
	public UsuarioService(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Usuario save(Usuario model) {
		return repository.save(model);
	}
	
	public void delete(Usuario model) {
		repository.delete(model);
	}
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Optional<Usuario> findById(Long id) {
		return repository.findById(id);
	}
}
