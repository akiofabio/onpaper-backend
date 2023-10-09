package com.LES.EcommerceOnPaper.service;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Categoria;
import com.LES.EcommerceOnPaper.model.Cliente;
import com.LES.EcommerceOnPaper.model.Item;
import com.LES.EcommerceOnPaper.model.Pedido;
import com.LES.EcommerceOnPaper.model.StatusPedido;
import com.LES.EcommerceOnPaper.repository.CategoriaRepository;
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

	
	public ResponseEntity<List<Pedido>> findByParametros(Optional<List<String>> pesquisas, Optional<List<String>> parametros){
		if(pesquisas.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
		}
		return ResponseEntity.status(HttpStatus.OK).body(repository.findParametros(pesquisas.get(), parametros.get()));
	}
	
	public Optional<List<Pedido>> findByPendentes() {
		return repository.findByPendentes();
	}

	public Optional<Pedido> findByItensId(Long id) {
		return repository.findByItensId( id);
	}

	
}
