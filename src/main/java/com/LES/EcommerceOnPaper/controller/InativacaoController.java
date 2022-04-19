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

import com.LES.EcommerceOnPaper.model.Inativacao;
import com.LES.EcommerceOnPaper.service.InativacaoService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class InativacaoController {
	
	final InativacaoService service;

	public InativacaoController(InativacaoService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/inativacao")
	public ResponseEntity<Object> create(@RequestBody Inativacao request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/inativacao/{id}")
	public ResponseEntity<Inativacao> update(@PathVariable Long id,@RequestBody Inativacao request) {
		Optional<Inativacao> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inativacao não Encontrada");
		}
		Inativacao model = optional.get();
		model.setGrupo(request.getGrupo());
		model.setMotivo(request.getMotivo());
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	@DeleteMapping("/inativacao/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Inativacao> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inativacao não Encontrada");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Inativacao deletada com sucesso");
	}
	
	
	@GetMapping("/inativacao")
	public ResponseEntity<List<Inativacao>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/inativacao/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Inativacao> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inativacao não Encontrada");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
}
