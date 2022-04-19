package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Endereco;
import com.LES.EcommerceOnPaper.repository.EnderecoRepository;

@Service
public class EnderecoService {

	final EnderecoRepository repository;
	
	public EnderecoService(EnderecoRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Endereco save(Endereco model) {
		return repository.save(model);
	}
	
	public void delete(Endereco model) {
		repository.delete(model);
	}
	
	public List<Endereco> findAll() {
		return repository.findAll();
	}
	
	public Optional<Endereco> findById(Long id) {
		return repository.findById(id);
	}
}
