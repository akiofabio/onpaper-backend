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

import com.LES.EcommerceOnPaper.model.GrupoDePrecificacao;
import com.LES.EcommerceOnPaper.service.GrupoDePrecificacaoService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class GrupoDePrecificacaoController {
	
	final GrupoDePrecificacaoService service;

	public GrupoDePrecificacaoController(GrupoDePrecificacaoService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/grp_precificacao")
	public ResponseEntity<Object> create(@RequestBody GrupoDePrecificacao request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/grp_precificacao/{id}")
	public ResponseEntity<GrupoDePrecificacao> update(@PathVariable Long id,@RequestBody GrupoDePrecificacao request) {
		Optional<GrupoDePrecificacao> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grupo de Precificação não Encontrado");
		}
		GrupoDePrecificacao model = optional.get();
		model.setNome(request.getNome());
		model.setDescricao(request.getDescricao());
		model.setMargemLucroMin(request.getMargemLucroMin());
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	@DeleteMapping("/grp_precificacao/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<GrupoDePrecificacao> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grupo de Precificação não Encontrado");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Grupo de Precificação deletado com sucesso");
	}
	
	
	@GetMapping("/grp_precificacao")
	public ResponseEntity<List<GrupoDePrecificacao>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/grp_precificacao/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<GrupoDePrecificacao> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Grupo de Precificação não Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
}
