package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.GrupoDePrecificacao;
import com.LES.EcommerceOnPaper.repository.GrupoDePrecificacaoRepository;

@Service
public class GrupoDePrecificacaoService {

	final GrupoDePrecificacaoRepository repository;
	
	public GrupoDePrecificacaoService(GrupoDePrecificacaoRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public GrupoDePrecificacao save(GrupoDePrecificacao model) {
		return repository.save(model);
	}
	
	public void delete(GrupoDePrecificacao model) {
		repository.delete(model);
	}
	
	public List<GrupoDePrecificacao> findAll() {
		return repository.findAll();
	}
	
	public Optional<GrupoDePrecificacao> findById(Long id) {
		return repository.findById(id);
	}
}
