package com.LES.EcommerceOnPaper.controller;

import java.util.Date;
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

import com.LES.EcommerceOnPaper.model.DadosGrafico;
import com.LES.EcommerceOnPaper.model.Item;
import com.LES.EcommerceOnPaper.model.Pedido;
import com.LES.EcommerceOnPaper.service.ItemService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ItemController {
	
	final ItemService service;

	public ItemController(ItemService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/item")
	public ResponseEntity<Object> create(@RequestBody Item request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/item/{id}")
	public ResponseEntity<Item> update(@PathVariable Long id,@RequestBody Item request) {
		Optional<Item> optional = service.findById(id);
		
		
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não Encontrado");
		}
		Item model = optional.get();
		model.setStatus(request.getStatus());
		model.setNomeProduto(request.getNomeProduto());
		model.setIdProduto(request.getIdProduto());
		model.setQuantidade(request.getQuantidade());
		model.setPreco(request.getPreco());
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	@DeleteMapping("/item/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Item> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não Encontrado");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Item deletado com sucesso");
	}
	
	
	@GetMapping("/item")
	public ResponseEntity<List<Item>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/item/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Item> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
	
	@GetMapping("/item/dados/dataInicio={dataInicio}&dataFinal={dataFinal}&tipo={tipo}&escala={escala}")
	public ResponseEntity<DadosGrafico> getByDados(@PathVariable Date dataInicio, @PathVariable Date dataFinal, @PathVariable String tipo, @PathVariable String escala) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findDados(dataInicio,dataFinal,tipo,escala));
	}
}