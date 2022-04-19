package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.LES.EcommerceOnPaper.model.Ativacao;

import com.LES.EcommerceOnPaper.repository.AtivacaoRepository;

@Service
public class AtivacaoService {
	final AtivacaoRepository repository;
	
	public AtivacaoService(AtivacaoRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Ativacao save(Ativacao model) {
		return repository.save(model);
	}
	
	@Transactional
	public void delete(Ativacao model) {
		repository.delete(model);
	}
	
	public List<Ativacao> findAll() {
		return repository.findAll();
	}
	
	public Optional<Ativacao> findById(Long id) {
		return repository.findById(id);
	}
}