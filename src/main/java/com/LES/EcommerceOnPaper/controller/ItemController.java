package com.LES.EcommerceOnPaper.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

import com.LES.EcommerceOnPaper.model.Cliente;
import com.LES.EcommerceOnPaper.model.Cupom;
import com.LES.EcommerceOnPaper.model.DadosGrafico;
import com.LES.EcommerceOnPaper.model.Item;
import com.LES.EcommerceOnPaper.model.Pedido;
import com.LES.EcommerceOnPaper.model.Produto;
import com.LES.EcommerceOnPaper.model.StatusItem;
import com.LES.EcommerceOnPaper.service.ClienteService;
import com.LES.EcommerceOnPaper.service.ItemService;
import com.LES.EcommerceOnPaper.service.ProdutoService;
@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ItemController {
	
	final ItemService service;
	final ClienteService clienteService;
	final ProdutoService produtoService;
	public ItemController(ItemService service, ClienteService clienteService, ProdutoService produtoService) {
		super();
		this.service = service;
		this.clienteService = clienteService;
		this.produtoService = produtoService;
	}
	
	@PostMapping("/item")
	public ResponseEntity<Object> create(@RequestBody Item request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/item/{id}")
	public ResponseEntity<Item> update(@PathVariable Long id,@RequestBody Item request) {
		Optional<Item> optional = service.findById(id);
		
		
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não Encontrado");
		}
		Item model = optional.get();
		model.setStatus(request.getStatus());
		model.setNomeProduto(request.getNomeProduto());
		model.setIdProduto(request.getIdProduto());
		model.setQuantidade(request.getQuantidade());
		model.setPreco(request.getPreco());
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	@DeleteMapping("/item/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Item> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não Encontrado");
		}
		service.delete(optional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Item deletado com sucesso");
	}
	
	
	@GetMapping("/item")
	public ResponseEntity<List<Item>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping("/item/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Item> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
	
	@GetMapping("/item/dados/dataInicio={dataInicio}&dataFinal={dataFinal}&tipo={tipo}&escala={escala}")
	public ResponseEntity<DadosGrafico> getByDados(@PathVariable Date dataInicio, @PathVariable Date dataFinal, @PathVariable String tipo, @PathVariable String escala) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findDados(dataInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),dataFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),tipo,escala));
	}
	
	@PutMapping("/item/{acao}/{id}")
	public ResponseEntity<Object> updateStatusItem(@PathVariable String acao,@PathVariable Long id) {
		System.out.println("t1");
		Optional<Item> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não Encontrado");
		}
		Item model = optional.get();
		StatusItem st = new StatusItem(acao, LocalDateTime.now());
		model.getStatus().add(st);
		if(acao.equals("Trocado")) {
			System.out.println("id: " + id);
			Optional<Cliente> clienteOp = clienteService.findByPedidosItensId(id);
			if(!clienteOp.isPresent()){
				ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente do Pedido não Encontrado");
			}
			Cliente cliente = clienteOp.get();
			Cupom novoCupom = new Cupom();
			novoCupom.setDescricao("Cupom de troca do valor: R$ " + (model.getPreco()*model.getQuantidade()));
			novoCupom.setTipo("Cupom de Troca");
			novoCupom.setValor(model.getPreco()*model.getQuantidade());
			cliente.getCupons().add(novoCupom);
			clienteService.save(cliente);
		}
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	
	@PutMapping("/item/updateQuantidade/{id}/{quantidade}")
	public ResponseEntity<Item> updateQuantidade(@PathVariable Long id, @PathVariable int quantidade) {
		System.out.println("A");
		Optional<Item> optionalItem = service.findById(id);
		if(!optionalItem.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não Encontrado");
		}
		Item item = optionalItem.get();
		item.setQuantidadeTrocar(0);
		
		System.out.println(optionalItem.get().getQuantidadeTrocar());
		Optional<Produto> optional = produtoService.findById(item.getIdProduto());
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não Encontrado");
		}
		Produto model = optional.get();
		model.setQuantidade(model.getQuantidade()+ quantidade);
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(item));
	}
}

