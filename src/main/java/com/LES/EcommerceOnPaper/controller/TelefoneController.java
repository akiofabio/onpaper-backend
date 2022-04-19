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

import com.LES.EcommerceOnPaper.model.Telefone;
import com.LES.EcommerceOnPaper.service.TelefoneService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class TelefoneController {
	
	final TelefoneService service;

	public TelefoneController(TelefoneService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/telefone")
	public ResponseEntity<Object> create(@RequestBody Telefone request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/telefone/{id}")
	public ResponseEntity<Telefone> update(@PathVariable Long id,@RequestBody Telefone request) {
		Optional<Telefone> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Telefone não Encontrado");
		}
		Telefone model = optional.get();
		model.setTipo(request.getTipo());
		model.setDdd(request.getDdd());
		model.setNumero(request.getNumero());
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	@DeleteMapping("/telefone/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Telefone> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Telefone não Encontrado");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Telefone deletado com sucesso");
	}
	
	
	@GetMapping("/telefone")
	public ResponseEntity<List<Telefone>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/telefone/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Telefone> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Telefone não Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
}
