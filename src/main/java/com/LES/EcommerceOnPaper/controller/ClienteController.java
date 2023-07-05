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
import org.springframework.web.bind.annotation.RequestParam;
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
	@GetMapping("/cliente/pesquisa")
	public ResponseEntity<List<Cliente>> getByParametros(@RequestParam(name = "pes") Optional<List<String>> pesquisas, @RequestParam(name = "par") Optional<List<String>> parametros) {
		/*Optional<List<String>> nomes= Optional.empty();
		Optional<List<String>> cpfs = Optional.empty();
		Optional<List<Date>> dataNascimento = Optional.empty();
		System.out.println(pesquisas.get().get(0));
		for(String parametro : parametros.get()) {
			if(parametro.equals("nome")) {
				if(nomes.isEmpty()) {
					nomes = pesquisas.get().get(parametros.get().indexOf(parametro));
				}
				else {
					nomes += " OR " + pesquisas.get().get(parametros.get().indexOf(parametro));
				}
			}
		}
		//System.out.println(nomes);
		Optional<String> opNomes = Optional.ofNullable(nomes).filter(s -> !s.isEmpty());
		//System.out.println(opNomes.get());
		//System.out.println(cpfs.get());
		 */
		return ResponseEntity.status(HttpStatus.OK).body(service.findByParametros(pesquisas, parametros));
	}
	
}