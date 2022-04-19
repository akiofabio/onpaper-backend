package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Pedido;
import com.LES.EcommerceOnPaper.repository.PedidoRepository;

@Service
public class PedidoService {

	final PedidoRepository repository;
	
	public PedidoService(PedidoRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Pedido save(Pedido model) {
		return repository.save(model);
	}
	
	public void delete(Pedido model) {
		repository.delete(model);
	}
	
	public List<Pedido> findAll() {
		return repository.findAll();
	}
	
	public Optional<Pedido> findById(Long id) {
		return repository.findById(id);
	}
}
