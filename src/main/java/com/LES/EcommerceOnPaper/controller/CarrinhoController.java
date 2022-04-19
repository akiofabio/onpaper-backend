package com.LES.EcommerceOnPaper.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LES.EcommerceOnPaper.model.Carrinho;
import com.LES.EcommerceOnPaper.model.Item;
import com.LES.EcommerceOnPaper.model.Produto;
import com.LES.EcommerceOnPaper.service.CarrinhoService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class CarrinhoController {
	final CarrinhoService service;

	public CarrinhoController(CarrinhoService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/carrinho")
	public ResponseEntity<Object> create(@RequestBody Carrinho request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/carrinho/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id,@RequestBody Carrinho request) {
		Optional<Carrinho> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( "Carrinho não Encontrado" );
		}
		Carrinho model = optional.get();
		
		for( Item item: request.getItens() ) {
			for( Item itemAnt: model.getItens() ) {
				if( item.getId() == itemAnt.getId() ) {
					Produto produto = itemAnt.getProduto();
				    int qtdDesbloqueio = itemAnt.getQuantidade() - item.getQuantidade();
				    int qtdDisponivel = produto.getQuantidade() - produto.getQuantidadeBloqueada();

				    if( itemAnt.getQuantidade() > item.getQuantidade() ){
				        produto.setQuantidadeBloqueada( produto.getQuantidadeBloqueada() - qtdDesbloqueio);
				        item.setProduto( produto );
				    }
				    else if( itemAnt.getQuantidade() < item.getQuantidade() ) {
				        
				        if( item.getStatus().equals( "DISPONIVEL" ) ) {
				            qtdDisponivel += itemAnt.getQuantidade();
				        }
				        
				        if( qtdDisponivel < item.getQuantidade() ) {
				        	return ResponseEntity.status( HttpStatus.NOT_ACCEPTABLE ).body( "Carrinho Quantidade Insuficiente" );
				        }
				        else{
				             produto.setQuantidadeBloqueada( produto.getQuantidadeBloqueada() + ( item.getQuantidade() - itemAnt.getQuantidade() ) );
				             item.setProduto( produto );
				        }
				    }
				} 
			}
		}
		
		
		model.setEndereco( request.getEndereco() );
		model.setItens( request.getItens() );
		model.setUltimoAdicionado( request.getUltimoAdicionado() );
		return ResponseEntity.status( HttpStatus.OK).body(service.save(model) );
	}
	
	@DeleteMapping("/carrinho/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Carrinho> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrinho não Encontrado");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Carrinho deletado com sucesso");
	}
	
	
	@GetMapping("/carrinho")
	public ResponseEntity<List<Carrinho>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/carrinho/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Carrinho> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrinho não Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
	
	@PutMapping("/carrinho/add/{id}")
	public ResponseEntity<Carrinho> add(@PathVariable Long id,@RequestBody Item request) {
		Optional<Carrinho> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrinho não Encontrado");
		}
		Carrinho model = optional.get();
		model.getItens().add(request);
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	
	@PutMapping("/carrinho/remove/{id}")
	public ResponseEntity<Carrinho> remove(@PathVariable Long id,@RequestBody Item request) {
		Optional<Carrinho> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrinho não Encontrado");
		}
		Carrinho model = optional.get();
		model.getItens().remove(request);
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
}

