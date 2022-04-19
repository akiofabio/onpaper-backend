package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Cliente;
import com.LES.EcommerceOnPaper.repository.ClienteRepository;

@Service
public class ClienteService {

	final ClienteRepository repository;
	
	public ClienteService(ClienteRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Cliente save(Cliente model) {
		return repository.save(model);
	}
	
	public void delete(Cliente model) {
		repository.delete(model);
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Optional<Cliente> findById(Long id) {
		return repository.findById(id);
	}
}
