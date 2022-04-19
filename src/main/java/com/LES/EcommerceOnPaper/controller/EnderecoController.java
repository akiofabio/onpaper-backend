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

import com.LES.EcommerceOnPaper.model.Endereco;
import com.LES.EcommerceOnPaper.service.EnderecoService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EnderecoController {
	
	final EnderecoService service;

	public EnderecoController(EnderecoService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/endereco")
	public ResponseEntity<Object> create(@RequestBody Endereco request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/endereco/{id}")
	public ResponseEntity<Endereco> update(@PathVariable Long id,@RequestBody Endereco request) {
		Optional<Endereco> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não Encontrado");
		}
		Endereco model = optional.get();
		model.setNome(request.getNome());
		model.setCep(request.getCep());
		model.setEstado(request.getEstado());
		model.setCidade(request.getCidade());
		model.setBairro(request.getBairro());
		model.setTipoLogradouro(request.getTipoLogradouro());
		model.setLogradouro(request.getLogradouro());
		model.setNumero(request.getNumero());
		model.setObservacao(request.getObservacao());
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	@DeleteMapping("/endereco/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Endereco> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não Encontrado");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Endereço deletado com sucesso");
	}
	
	
	@GetMapping("/endereco")
	public ResponseEntity<List<Endereco>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/endereco/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Endereco> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
}
