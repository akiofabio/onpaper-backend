package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.LES.EcommerceOnPaper.model.Carrinho;
import com.LES.EcommerceOnPaper.repository.CarrinhoRepository;

@Service
public class CarrinhoService {
	final CarrinhoRepository repository;
	
	public CarrinhoService(CarrinhoRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Carrinho save(Carrinho model) {
		return repository.save(model);
	}
	
	public void delete(Carrinho model) {
		repository.delete(model);
	}
	
	public List<Carrinho> findAll() {
		return repository.findAllByOrderById();
	}
	
	public Optional<Carrinho> findById(Long id) {
		return repository.findById(id);
	}
}
