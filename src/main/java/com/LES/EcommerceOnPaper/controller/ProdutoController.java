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

import com.LES.EcommerceOnPaper.model.Produto;
import com.LES.EcommerceOnPaper.service.ProdutoService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ProdutoController {
	
	final ProdutoService service;

	public ProdutoController(ProdutoService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/produto")
	public ResponseEntity<Object> create(@RequestBody Produto request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/produto/{id}")
	public ResponseEntity<Produto> update(@PathVariable Long id,@RequestBody Produto request) {
		Optional<Produto> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ativação não Encontrado");
		}
		Produto model = optional.get();
		model.setStatus(request.getStatus());
		model.setAltura(request.getAltura());
		model.setAtivacoes(request.getAtivacoes());
		model.setCodigoDeBarra(request.getCodigoDeBarra());
		model.setComprimento(request.getComprimento());
		model.setCusto(request.getCusto());
		model.setDescricao(request.getDescricao());
		model.setGrupoDePrecificacao(request.getGrupoDePrecificacao());
		model.setImagens(request.getImagens());
		model.setInativacoes(request.getInativacoes());
		model.setLargura(request.getLargura());
		model.setNome(request.getNome());
		model.setPeso(request.getPeso());
		model.setPreco(request.getPreco());
		model.setQuantidade(request.getQuantidade());
		model.setQuantidadeBloqueada(request.getQuantidadeBloqueada());
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	@DeleteMapping("/produto/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Produto> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não Encontrado");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso");
	}
	
	
	@GetMapping("/produto")
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/produto/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Produto> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
}
	
