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

import com.LES.EcommerceOnPaper.model.Cartao;
import com.LES.EcommerceOnPaper.service.CartaoService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class CartaoController {
	
	final CartaoService service;

	public CartaoController(CartaoService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/cartao")
	public ResponseEntity<Object> create(@RequestBody Cartao request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/cartao/{id}")
	public ResponseEntity<Cartao> update(@PathVariable Long id,@RequestBody Cartao request) {
		Optional<Cartao> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não Encontrado");
		}
		Cartao model = optional.get();
		model.setCodigoSeguranca(request.getCodigoSeguranca());
		model.setNome(request.getNome());
		model.setNumero(request.getNumero());
		model.setValidade(request.getValidade());
		model.setPreferencial(request.isPreferencial());
		
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	@DeleteMapping("/cartao/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Cartao> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não Encontrado");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Cartão deletado com sucesso");
	}
	
	
	@GetMapping("/cartao")
	public ResponseEntity<List<Cartao>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/cartao/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Cartao> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
}
