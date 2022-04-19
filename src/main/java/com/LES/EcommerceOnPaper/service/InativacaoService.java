package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Inativacao;
import com.LES.EcommerceOnPaper.repository.InativacaoRepository;

@Service
public class InativacaoService {

	final InativacaoRepository repository;
	
	public InativacaoService(InativacaoRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Inativacao save(Inativacao model) {
		return repository.save(model);
	}
	
	public void delete(Inativacao model) {
		repository.delete(model);
	}
	
	public List<Inativacao> findAll() {
		return repository.findAll();
	}
	
	public Optional<Inativacao> findById(Long id) {
		return repository.findById(id);
	}
}
