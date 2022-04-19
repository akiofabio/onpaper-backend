package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Categoria;
import com.LES.EcommerceOnPaper.repository.CategoriaRepository;

@Service
public class CategoriaService {

	final CategoriaRepository repository;
	
	public CategoriaService(CategoriaRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Categoria save(Categoria model) {
		return repository.save(model);
	}
	
	public void delete(Categoria model) {
		repository.delete(model);
	}
	
	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	public Optional<Categoria> findById(Long id) {
		return repository.findById(id);
	}
}
