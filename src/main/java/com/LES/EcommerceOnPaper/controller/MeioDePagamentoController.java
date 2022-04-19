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

import com.LES.EcommerceOnPaper.model.MeioDePagamento;
import com.LES.EcommerceOnPaper.service.MeioDePagamentoService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class MeioDePagamentoController {
	
	final MeioDePagamentoService service;

	public MeioDePagamentoController(MeioDePagamentoService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/meio_pagamento")
	public ResponseEntity<Object> create(@RequestBody MeioDePagamento request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/meio_pagamento/{id}")
	public ResponseEntity<MeioDePagamento> update(@PathVariable Long id,@RequestBody MeioDePagamento request) {
		Optional<MeioDePagamento> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Meio de Pagamento não Encontrado");
		}
		MeioDePagamento model = optional.get();
		model.setTipo(request.getTipo());
		model.setValor(request.getValor());
		model.setDetalhes(request.getDetalhes());
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	@DeleteMapping("/meio_pagamento/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<MeioDePagamento> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Meio de Pagamento não Encontrado");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Meio de Pagamento deletado com sucesso");
	}
	
	
	@GetMapping("/meio_pagamento")
	public ResponseEntity<List<MeioDePagamento>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/meio_pagamento/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<MeioDePagamento> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Meio de Pagamento não Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
}