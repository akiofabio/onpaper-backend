package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.MeioDePagamento;
import com.LES.EcommerceOnPaper.repository.MeioDePagamentoRepository;

@Service
public class MeioDePagamentoService {

	final MeioDePagamentoRepository repository;
	
	public MeioDePagamentoService(MeioDePagamentoRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public MeioDePagamento save(MeioDePagamento model) {
		return repository.save(model);
	}
	
	public void delete(MeioDePagamento model) {
		repository.delete(model);
	}
	
	public List<MeioDePagamento> findAll() {
		return repository.findAll();
	}
	
	public Optional<MeioDePagamento> findById(Long id) {
		return repository.findById(id);
	}
}
