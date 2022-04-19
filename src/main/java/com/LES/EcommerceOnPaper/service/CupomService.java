package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Cupom;
import com.LES.EcommerceOnPaper.repository.CupomRepository;

@Service
public class CupomService {

	final CupomRepository repository;
	
	public CupomService(CupomRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Cupom save(Cupom model) {
		return repository.save(model);
	}
	
	public void delete(Cupom model) {
		repository.delete(model);
	}
	
	public List<Cupom> findAll() {
		return repository.findAll();
	}
	
	public Optional<Cupom> findById(Long id) {
		return repository.findById(id);
	}
}
