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

import com.LES.EcommerceOnPaper.model.Usuario;
import com.LES.EcommerceOnPaper.service.UsuarioService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
	
	final UsuarioService service;

	public UsuarioController(UsuarioService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/usuario")
	public ResponseEntity<Object> create(@RequestBody Usuario request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Long id,@RequestBody Usuario request) {
		Optional<Usuario> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não Encontrado");
		}
		
		Usuario model = optional.get();
		model.setEmail(request.getEmail());
		model.setSenha(request.getSenha());
		model.setTipo(request.getTipo());
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	@DeleteMapping("/usuario/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Usuario> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não Encontrado");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso");
	}
	
	
	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Usuario> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não Encontrada");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
	
	@GetMapping("/usuario/login/email={email}&senha={senha}")
	public ResponseEntity<Object> getLogin( @PathVariable String email , @PathVariable String senha ) {
		
		Optional<Usuario> optional = service.findByEmail( email );
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não Encontrada");
		}
		Usuario usuarioRes = optional.get();
		if( !usuarioRes.getSenha().equals( senha ) ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ou Senha Incorretos");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
}
