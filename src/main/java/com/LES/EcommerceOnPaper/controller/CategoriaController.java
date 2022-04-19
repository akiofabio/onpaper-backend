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

import com.LES.EcommerceOnPaper.model.Categoria;
import com.LES.EcommerceOnPaper.service.CategoriaService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class CategoriaController {
	
	final CategoriaService service;

	public CategoriaController(CategoriaService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/categoria")
	public ResponseEntity<Object> create(@RequestBody Categoria request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/categoria/{id}")
	public ResponseEntity<Categoria> update(@PathVariable Long id,@RequestBody Categoria request) {
		Optional<Categoria> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartegoria não Encontrado");
		}
		Categoria model = optional.get();
		
		model.setNome(request.getNome());
		model.setDescricao(request.getDescricao());		
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	@DeleteMapping("/categoria/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Categoria> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartegoria não Encontrado");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Cartegoria deletado com sucesso");
	}
	
	
	@GetMapping("/categoria")
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/categoria/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Categoria> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartegoria não Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
}
