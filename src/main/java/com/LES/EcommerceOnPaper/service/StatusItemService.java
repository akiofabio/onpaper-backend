package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Item;
import com.LES.EcommerceOnPaper.model.StatusItem;
import com.LES.EcommerceOnPaper.repository.CategoriaRepository;
import com.LES.EcommerceOnPaper.repository.ItemRepository;
import com.LES.EcommerceOnPaper.repository.ProdutoRepository;
import com.LES.EcommerceOnPaper.repository.StatusItemRepository;

@Service
public class StatusItemService {
	final ProdutoRepository produtoRepository;
	final StatusItemRepository repository;
	public StatusItemService(StatusItemRepository repository, CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
		super();
		this.repository = repository;
		this.produtoRepository = produtoRepository;
	}
	
	@Transactional
	public StatusItem save(StatusItem model) {
		return repository.save(model);
	}
	
	@Transactional
	public void delete(StatusItem model) {
		repository.delete(model);
	}
	
	public List<StatusItem> findAll() {
		return repository.findAll();
	}
	
	public Optional<StatusItem> findById(Long id) {
		return repository.findById(id);
	}
}
