package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Telefone;
import com.LES.EcommerceOnPaper.repository.TelefoneRepository;

@Service
public class TelefoneService {

	final TelefoneRepository repository;
	
	public TelefoneService(TelefoneRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Telefone save(Telefone model) {
		return repository.save(model);
	}
	
	public void delete(Telefone model) {
		repository.delete(model);
	}
	
	public List<Telefone> findAll() {
		return repository.findAll();
	}
	
	public Optional<Telefone> findById(Long id) {
		return repository.findById(id);
	}
}
