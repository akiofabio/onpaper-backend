package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Produto;
import com.LES.EcommerceOnPaper.repository.ProdutoRepository;

@Service
public class ProdutoService {

	final ProdutoRepository repository;
	
	public ProdutoService(ProdutoRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Produto save(Produto model) {
		return repository.save(model);
	}
	
	public void delete(Produto model) {
		repository.delete(model);
	}
	
	public List<Produto> findAll() {
		return repository.findAll();
	}
	
	public Optional<Produto> findById(Long id) {
		return repository.findById(id);
	}
}
