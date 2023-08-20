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
		List<String> nomes = new ArrayList<String>();
		List<String> cpfs =  new ArrayList<String>();
		
		return  ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
		
		/*if(!pesquisas.isPresent()) {
			return  ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
		}
		
		//Specification<Pedido> specification = Specification.where(parametroIn( pesquisas.get()),parametros.get());
		
		for(String parametro : parametros.get()) {
			if(parametro.equals("nome")) {
				System.out.println(pesquisas.get().get(parametros.get().indexOf(parametro)));
				if(!pesquisas.get().get(parametros.get().indexOf(parametro)).isBlank())
					nomes.add(pesquisas.get().get(parametros.get().indexOf(parametro)));
			}
			if(parametro.equals("cpf")) {
				cpfs.add(pesquisas.get().get(parametros.get().indexOf(parametro)));
			}
			if(parametro.equals("dataNacimento")) {
				//datasNascimento.add(
				//		pesquisas.get().get(parametros.get().indexOf(parametro)));
			}
		}
		
		
		
		return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
		*/
	}
	
	private static String contains(String expression) {
	    return MessageFormat.format("%{0}%", expression);
	}
	
	public static Specification<Pedido> parametroIn(List<String> values , String parametro) {
	    return (root, query, builder) -> builder.or(values.stream()
	        .map(value -> builder.like(root.get(parametro), contains(value)))
	        .toArray(Predicate[]::new));
	}

	public List<Pedido> findByPendentes() {
		return repository.findByPendentes();
	}

	
}
