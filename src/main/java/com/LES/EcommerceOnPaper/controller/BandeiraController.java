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

import com.LES.EcommerceOnPaper.model.Bandeira;
import com.LES.EcommerceOnPaper.service.BandeiraService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class BandeiraController {
	final BandeiraService service;

	public BandeiraController(BandeiraService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/bandeira")
	public ResponseEntity<Object> create(@RequestBody Bandeira request) {
		if(request.getNome().isBlank()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("A Bandeira é Obrigatória");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/bandeira/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id,@RequestBody Bandeira request) {
		Optional<Bandeira> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bandeira não Encontrada");
		}
		Bandeira model = optional.get();
		model.setNome(request.getNome());
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	@DeleteMapping("/bandeira/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Bandeira> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bandeira não Encontrada");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Bandeira deletada com sucesso");
	}
	
	
	@GetMapping("/bandeira")
	public ResponseEntity<List<Bandeira>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/bandeira/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Bandeira> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bandeira não Encontrada");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
}

