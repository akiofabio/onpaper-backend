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

import com.LES.EcommerceOnPaper.model.Ativacao;
import com.LES.EcommerceOnPaper.service.AtivacaoService;


@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class AtivacaoController {
	
	final AtivacaoService service;

	public AtivacaoController(AtivacaoService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/ativacao")
	public ResponseEntity<Object> create(@RequestBody Ativacao request) {
		if(request.getMotivo().isBlank()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("O Motivo é Obrigatório");
		}
		request.setData(new Date());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/ativacao/{id}")
	public ResponseEntity<Ativacao> update(@PathVariable Long id,@RequestBody Ativacao request) {
		Optional<Ativacao> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ativação não Encontrada");
		}
		Ativacao model = optional.get();
		model.setGrupo(request.getGrupo());
		model.setMotivo(request.getMotivo());
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	@DeleteMapping("/ativacao/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Ativacao> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ativação não Encontrada");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Ativação deletada com sucesso");
	}
	
	
	@GetMapping("/ativacao")
	public ResponseEntity<List<Ativacao>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/ativacao/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Ativacao> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ativação não Encontrada");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
}
