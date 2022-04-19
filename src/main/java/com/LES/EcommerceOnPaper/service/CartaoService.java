package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Cartao;
import com.LES.EcommerceOnPaper.repository.CartaoRepository;

@Service
public class CartaoService {

	final CartaoRepository repository;
	
	public CartaoService(CartaoRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Cartao save(Cartao model) {
		return repository.save(model);
	}
	
	public void delete(Cartao model) {
		repository.delete(model);
	}
	
	public List<Cartao> findAll() {
		return repository.findAll();
	}
	
	public Optional<Cartao> findById(Long id) {
		return repository.findById(id);
	}
}
