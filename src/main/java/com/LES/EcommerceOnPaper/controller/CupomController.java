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

import com.LES.EcommerceOnPaper.model.Cupom;
import com.LES.EcommerceOnPaper.service.CupomService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class CupomController {
	
	final CupomService service;

	public CupomController(CupomService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/cupom")
	public ResponseEntity<Object> create(@RequestBody Cupom request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/cupom/{id}")
	public ResponseEntity<Cupom> update(@PathVariable Long id,@RequestBody Cupom request) {
		Optional<Cupom> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cupom não Encontrado");
		}
		Cupom model = optional.get();
		model.setTipo(request.getTipo());
		model.setValor(request.getValor());
		model.setDescricao(request.getDescricao());
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	
	@DeleteMapping("/cupom/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Cupom> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cupom não Encontrado");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Cupom deletado com sucesso");
	}
	
	
	@GetMapping("/cupom")
	public ResponseEntity<List<Cupom>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/cupom/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Cupom> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cupom não Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
}
