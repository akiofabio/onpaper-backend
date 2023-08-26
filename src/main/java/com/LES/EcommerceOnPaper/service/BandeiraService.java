package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Ativacao;
import com.LES.EcommerceOnPaper.model.Bandeira;
import com.LES.EcommerceOnPaper.repository.AtivacaoRepository;
import com.LES.EcommerceOnPaper.repository.BandeiraRepository;

@Service
public class BandeiraService {
	final BandeiraRepository repository;
	
	public BandeiraService(BandeiraRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Bandeira save(Bandeira model) {
		return repository.save(model);
	}
	
	@Transactional
	public void delete(Bandeira model) {
		repository.delete(model);
	}
	
	public List<Bandeira> findAll() {
		return repository.findAll();
	}
	
	public Optional<Bandeira> findById(Long id) {
		return repository.findById(id);
	}
	
}
