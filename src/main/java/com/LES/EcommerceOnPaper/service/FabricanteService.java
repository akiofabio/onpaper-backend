package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Fabricante;
import com.LES.EcommerceOnPaper.repository.FabricanteRepository;

@Service
public class FabricanteService {

	final FabricanteRepository repository;
	
	public FabricanteService(FabricanteRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Fabricante save(Fabricante model) {
		return repository.save(model);
	}
	
	public void delete(Fabricante model) {
		repository.delete(model);
	}
	
	public List<Fabricante> findAll() {
		return repository.findAll();
	}
	
	public Optional<Fabricante> findById(Long id) {
		return repository.findById(id);
	}
}