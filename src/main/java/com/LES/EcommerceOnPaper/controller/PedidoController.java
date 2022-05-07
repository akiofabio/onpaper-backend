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

import com.LES.EcommerceOnPaper.model.Pedido;
import com.LES.EcommerceOnPaper.service.PedidoService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class PedidoController {
	
	final PedidoService service;

	public PedidoController(PedidoService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/pedido")
	public ResponseEntity<Object> create(@RequestBody Pedido request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/pedido/{id}")
	public ResponseEntity<Pedido> update(@PathVariable Long id,@RequestBody Pedido request) {
		Optional<Pedido> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não Encontrado");
		}
		Pedido model = optional.get();
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	
	@DeleteMapping("/pedido/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Pedido> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não Encontrado");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Pedido deletado com sucesso");
	}
	
	@GetMapping("/pedido")
	public ResponseEntity<List<Pedido>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/pedido/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Pedido> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
	
	@GetMapping("/pedido/datas/dataInicio={dataInicio}&dataFinal={dataFinal}")
	public ResponseEntity<List<Pedido>> getByDatas(@PathVariable Date dataInicio, @PathVariable Date dataFinal) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findByDatas(dataInicio,dataFinal));
	}
}
