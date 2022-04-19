package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Item;
import com.LES.EcommerceOnPaper.repository.ItemRepository;

@Service
public class ItemService {

	final ItemRepository repository;
	
	public ItemService(ItemRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Item save(Item model) {
		return repository.save(model);
	}
	
	@Transactional
	public void delete(Item model) {
		repository.delete(model);
	}
	
	public List<Item> findAll() {
		return repository.findAll();
	}
	
	public Optional<Item> findById(Long id) {
		return repository.findById(id);
	}
}
