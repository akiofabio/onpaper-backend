package com.LES.EcommerceOnPaper.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

import com.LES.EcommerceOnPaper.model.Categoria;
import com.LES.EcommerceOnPaper.model.Fabricante;
import com.LES.EcommerceOnPaper.model.Item;
import com.LES.EcommerceOnPaper.model.Produto;
import com.LES.EcommerceOnPaper.service.ProdutoService;
import com.LES.EcommerceOnPaper.service.ItemService;
@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ProdutoController {
	
	final ProdutoService service;
	final ItemService itemService;
	public ProdutoController(ProdutoService service, ItemService itemService) {
		super();
		this.service = service;
		this.itemService = itemService;
	}
	
	@PostMapping("/produto")
	public ResponseEntity<Object> create(@RequestBody Produto request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/produto/updateQuantidade/{id}/{quantidade}")
	public ResponseEntity<Produto> updateQuantidade(@PathVariable Long id, @PathVariable int quantidade) {
		System.out.println("A");
		Optional<Item> optionalItem = itemService.findById(id);
		if(!optionalItem.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não Encontrado");
		}
		Item item = optionalItem.get();
		item.setQuantidadeTrocar(0);
		itemService.save(item);
		
		optionalItem = itemService.findById(id);
		System.out.println(optionalItem.get().getQuantidadeTrocar());
		Optional<Produto> optional = service.findById(item.getIdProduto());
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não Encontrado");
		}
		Produto model = optional.get();
		model.setQuantidade(model.getQuantidade()+ quantidade);
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	
	@PutMapping("/produto/{id}")
	public ResponseEntity<Produto> update(@PathVariable Long id,@RequestBody Produto request) {
		Optional<Produto> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não Encontrado");
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
		model.setDestaque(request.isDestaque());
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
	
	@GetMapping("/produto/pesquisa")
	public ResponseEntity<List<Produto>> findByNome2(@RequestParam(name = "pes") Optional<String> pesquisa, @RequestParam(name = "cat") Optional<List<String>> categorias,@RequestParam(name = "fab") Optional<List<String>> fabricantes) {
		if(pesquisa.isPresent() && !pesquisa.get().isEmpty() && !pesquisa.get().isBlank()) {
			if(categorias.isPresent() && fabricantes.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(service.findByNomeAndCategoriasInAndFabricanteIn(pesquisa.get(),categorias,fabricantes));
			}
			if(categorias.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(service.findByNomeAndCategoriaIn(pesquisa.get(),categorias));
			}
			if(categorias.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(service.findByNomeAndFabricanteIn( pesquisa.get(), fabricantes));
			}
			return ResponseEntity.status(HttpStatus.OK).body(service.findByNome(pesquisa.get()));
		}
		else {
			System.out.println("deu certo 1");
			if(categorias.isPresent() && fabricantes.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(service.findByCategoriasInAndFabricanteIn(categorias,fabricantes));
			}
			if(categorias.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(service.findByCategoriaIn(categorias));
			}
			if(fabricantes.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(service.findByFabricanteIn(fabricantes));
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
		
	}
}
	
