package com.LES.EcommerceOnPaper.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

import com.LES.EcommerceOnPaper.model.Cliente;
import com.LES.EcommerceOnPaper.model.Cupom;
import com.LES.EcommerceOnPaper.model.Item;
import com.LES.EcommerceOnPaper.model.MeioDePagamento;
import com.LES.EcommerceOnPaper.model.Pedido;
import com.LES.EcommerceOnPaper.model.Produto;
import com.LES.EcommerceOnPaper.model.StatusItem;
import com.LES.EcommerceOnPaper.model.StatusPedido;
import com.LES.EcommerceOnPaper.service.ClienteService;
import com.LES.EcommerceOnPaper.service.ItemService;
import com.LES.EcommerceOnPaper.service.MeioDePagamentoService;
import com.LES.EcommerceOnPaper.service.PedidoService;
import com.LES.EcommerceOnPaper.service.ProdutoService;
import com.LES.EcommerceOnPaper.service.StatusItemService;
import com.LES.EcommerceOnPaper.service.CupomService;
@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class PedidoController {
	
	final PedidoService service;
	final ItemService itemService;
	final MeioDePagamentoService meioDePagamentoService;
	final StatusItemService statusItemService;
	final ClienteService clienteService;
	final ProdutoService produtoService;
	final CupomService cupomService;
	public PedidoController(PedidoService service, ItemService itemService, MeioDePagamentoService meioDePagamentoService, StatusItemService statusItemService, ClienteService clienteService, ProdutoService produtoService, CupomService cupomService) {
		super();
		this.service = service;
		this.itemService = itemService;
		this.meioDePagamentoService = meioDePagamentoService;
		this.statusItemService = statusItemService;
		this.clienteService = clienteService;
		this.produtoService = produtoService;
		this.cupomService = cupomService;
	}
	
	@PostMapping("/pedido")
	public ResponseEntity<Object> create(@RequestBody Pedido request) {
		float total = 0;
		float totalPago = 0;
		ArrayList<MeioDePagamento> cartoes = new ArrayList<MeioDePagamento>() ;
		ArrayList<MeioDePagamento> cuponsTroca = new ArrayList<MeioDePagamento>();
		MeioDePagamento cupomPromocional = null;

		for(Item item : request.getItens()) {
		    total += item.getPreco() *item.getQuantidade();
		} 

		for( MeioDePagamento meio : request.getMeioDePagamentos() ){
		    totalPago += meio.getValor();
		    if(meio.getTipo().equals( "Cartão de Credito" ) ) {
		        cartoes.add( meio );
		    }
		    else if ( meio.getTipo().equals( "Cupom de Troca" ) ) {
		        cuponsTroca.add( meio );
		    }
		    else if ( meio.getTipo().equals( "Cupom Promocional" ) ) {
		        if( cupomPromocional == null ) {
		            cupomPromocional = meio;
		        }
		        else{
		            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "Só é permitido um cupom promocional por compra" ) ;
		        } 
		    }
		}
		if( ( total + request.getFrete() ) > totalPago ) {
		    return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "Valor insuficiente" ) ;
		}
		else if( ( total + request.getFrete() ) < totalPago) {
		    if( !cartoes.isEmpty() ) {
		        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "Valor excedido! Não é possível gerar cupom de troca enquando utiliza um ou mais cartão de crédito" );
		    }
		    if( !cuponsTroca.isEmpty() ) { 
		        ArrayList<MeioDePagamento> cupons = cuponsTroca;
		        if( cupomPromocional != null ) {
		            cupons.add(cupomPromocional);
		        }
		        Collections.sort( cuponsTroca , new Comparator<MeioDePagamento>(){ 
		            public int compare(MeioDePagamento m1, MeioDePagamento m2){ 
		                if(m1.getValor() == m2.getValor()) {
		                    return 0;
		                }
		                return m1.getValor() < m2.getValor() ? -1 : 1; 
		            }
		        });
		        float totalCupons = 0 ;
		        for( MeioDePagamento cupom : cupons ) {
		            if( totalCupons > (total + request.getFrete())) {
		                return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "Não é possível usar cupons que excedem o total a pagar" );
		            }
		            totalCupons += cupom.getValor();
		        }
		        
		    }
		}
		else if( ( total + request.getFrete() ) == totalPago ) {
			if( ( cartoes.size() > 1 ) || ( ( cuponsTroca.isEmpty() ) && ( cupomPromocional == null ) ) ) {
				for(MeioDePagamento cartao : cartoes) {
					if(cartao.getValor()<10) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O valor minimo de cada cartão de crédito é de R$10,00");
					}
				}
			}
		}
		if(request.getStatus()==null || request.getStatus().size()==0) {
			Set<StatusPedido> statusSet = new HashSet<StatusPedido>();
			
			StatusPedido statusProc= new StatusPedido();
			statusProc.setStatus("Em Processamento");
			statusProc.setData(LocalDateTime.now());
			statusSet.add(statusProc);
			request.setStatus(statusSet);
			for(Item item : request.getItens()) {
				Set<StatusItem> stItemSet = new HashSet<StatusItem>();
				StatusItem st = new StatusItem("Em Processamento",statusProc.getData());
				stItemSet.add(st);
				item.setStatus(stItemSet);
				itemService.save(item);
			}
		}
		for(MeioDePagamento meio : request.getMeioDePagamentos()) {
			if(meio.getTipo().equals( "Cupom de Troca" ) || meio.getTipo().equals( "Cupom Promocional")){
				Optional<Cliente> clienteOP= clienteService.findByCuponsId(meio.getIdTipo());
				if(!clienteOP.isPresent() || clienteOP.isEmpty()) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado");
				}
				Cliente cliente = clienteOP.get();
				Optional<Cupom> cupom = cupomService.findById(meio.getIdTipo());
				if(!cupom.isPresent() || cupom.isEmpty()) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cupom não encontrado");
				}
				cliente.getCupons().remove(cupom.get());
				clienteService.save(cliente);
			}
		}
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
	
	@PutMapping("/pedido/{acao}/{id}")
	public ResponseEntity<Object> updateStatus(@PathVariable String acao,@PathVariable Long id) {
		Optional<Pedido> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não Encontrado");
		}
		Pedido model = optional.get();
		StatusPedido st = new StatusPedido(acao,LocalDateTime.now());
		for(Item item : model.getItens()) {
			if(item.getUltimoStatus().getStatus().equals(model.getUltimoStatus().getStatus())) {
				Set<StatusItem> stItemSet = new HashSet<StatusItem>();
				StatusItem stI = new StatusItem(acao,st.getData());
				stItemSet.add(stI);
				item.setStatus(stItemSet);
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não é permitido mudar estado do pedido intero com um item devolvido/trocado");
			}
		}
		model.getStatus().add(st);
		if(acao.equals("Aprovado")) {
			for(Item item : model.getItens()) {
				Optional<Produto> produtoOp = produtoService.findById(item.getIdProduto());
				if(!produtoOp.isPresent()) {
					ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto do Pedido não Encontrado");
				}
				Produto produto = produtoOp.get();
				produto.setQuantidade(produto.getQuantidade()-item.getQuantidade());
				produto.setQuantidadeBloqueada(produto.getQuantidadeBloqueada()-item.getQuantidade());
			}
		}
		else if(acao.equals("Trocado")) {
			Optional<Cliente> clienteOp = clienteService.findByPedidosId(id);
			if(!clienteOp.isPresent()){
				ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente do Pedido não Encontrado");
			}
			Cliente cliente = clienteOp.get();
			Cupom novoCupom = new Cupom();
			novoCupom.setDescricao("Cupom de troca do valor: R$ " + model.getTotal());
			novoCupom.setTipo("Cupom de Troca");
			novoCupom.setValor(model.getTotal());
			cliente.getCupons().add(novoCupom);
			clienteService.save(cliente);
		}
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
	
	@GetMapping("/pedido/pendentes")
	public ResponseEntity<Object> getPedidosPendentes(){
		Optional<List<Pedido>> optional = service.findByPendentes();
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há Pedidos Pendentes");
		}
		if(optional.get().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há Pedidos Pendentes");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
	
	@GetMapping("/pedido/{id}")
	public ResponseEntity<Object> getById(@PathVariable Long id) {
		Optional<Pedido> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
	
	@GetMapping("/pedido/pesquisa")
	public ResponseEntity<List<Pedido>> findByParametros(@RequestParam(name = "pes") Optional<List<String>> pesquisas, @RequestParam(name = "par") Optional<List<String>> parametros) {
		return service.findByParametros(pesquisas, parametros);
		
	}

	/*@GetMapping("/pedido/datas/dataInicio={dataInicio}&dataFinal={dataFinal}")
	public ResponseEntity<List<Pedido>> getByDatas(@PathVariable Date dataInicio, @PathVariable Date dataFinal) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findByDatas(dataInicio,dataFinal));
	}
	*/
	
}
