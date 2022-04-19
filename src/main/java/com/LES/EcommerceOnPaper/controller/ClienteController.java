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

import com.LES.EcommerceOnPaper.model.Cliente;
import com.LES.EcommerceOnPaper.service.ClienteService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ClienteController {
	
	final ClienteService service;

	public ClienteController(ClienteService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/cliente")
	public ResponseEntity<Object> create(@RequestBody Cliente request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/cliente/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id,@RequestBody Cliente request) {
		Optional<Cliente> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não Encontrada");
		}
		Cliente model = optional.get();
		model.setCarrinho(request.getCarrinho());
		model.setCartoes(request.getCartoes());
		model.setCpf(request.getCpf());
		model.setCupons(request.getCupons());
		model.setDataNascimento(request.getDataNascimento());
		model.setEmail(request.getEmail());
		model.setEnderecos(request.getEnderecos());
		model.setGenero(request.getGenero());
		model.setNome(request.getNome());
		model.setPedidos(request.getPedidos());
		model.setScore(request.getScore());
		model.setSenha(request.getSenha());
		model.setStatus(request.getStatus());
		model.setTelefones(request.getTelefones());
		model.setTipo(request.getTipo());
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Cliente> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não Encontrado");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso");
	}
	
	
	@GetMapping("/cliente")
	public ResponseEntity<List<Cliente>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/cliente/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Cliente> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
}