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

import com.LES.EcommerceOnPaper.model.Fabricante;
import com.LES.EcommerceOnPaper.service.FabricanteService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class FabricanteController {
	
	final FabricanteService service;

	public FabricanteController(FabricanteService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/fabricante")
	public ResponseEntity<Object> create(@RequestBody Fabricante request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/fabricante/{id}")
	public ResponseEntity<Fabricante> update(@PathVariable Long id,@RequestBody Fabricante request) {
		Optional<Fabricante> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não Encontrado");
		}
		Fabricante model = optional.get();
		model.setNome(request.getNome());
		model.setDescricao(request.getDescricao());
		
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	@DeleteMapping("/fabricante/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Fabricante> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não Encontrado");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Cartão deletado com sucesso");
	}
	
	
	@GetMapping("/fabricante")
	public ResponseEntity<List<Fabricante>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/fabricante/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Fabricante> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
}
