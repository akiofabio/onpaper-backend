package com.LES.EcommerceOnPaper.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.LES.EcommerceOnPaper.model.Cupom;
import com.LES.EcommerceOnPaper.model.Item;
import com.LES.EcommerceOnPaper.model.MeioDePagamento;
import com.LES.EcommerceOnPaper.model.Pedido;
import com.LES.EcommerceOnPaper.model.StatusPedido;
import com.LES.EcommerceOnPaper.service.ItemService;
import com.LES.EcommerceOnPaper.service.MeioDePagamentoService;
import com.LES.EcommerceOnPaper.service.PedidoService;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class PedidoController {
	
	final PedidoService service;
	final ItemService itemService;
	final MeioDePagamentoService meioDePagamentoService;
	public PedidoController(PedidoService service, ItemService itemService, MeioDePagamentoService meioDePagamentoService) {
		super();
		this.service = service;
		this.itemService = itemService;
		this.meioDePagamentoService = meioDePagamentoService;
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
		    if(meio.getTipo().equals( "Cart??o de Credito" ) ) {
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
		            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "S?? ?? permitido um cupom promocional por compra" ) ;
		        } 
		    }
		}
		if( ( total + request.getFrete() ) > totalPago ) {
		    return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "Valor insuficiente" ) ;
		}
		else if( ( total + request.getFrete() ) < totalPago) {
		    if( !cartoes.isEmpty() ) {
		        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "Valor excedido! N??o ?? poss??vel gerar cupom de troca enquando utiliza um ou mais cart??o de cr??dito" );
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
		            if( totalCupons > total ) {
		                return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( "N??o ?? poss??vel usar cupons que excedem o total a pagar" );
		            }
		            totalCupons += cupom.getValor();
		        }
		    }
		}
		else if( ( total + request.getFrete() ) == totalPago ) {
			if( ( cartoes.size() > 1 ) || ( ( cuponsTroca.isEmpty() ) && ( cupomPromocional == null ) ) ) {
				for(MeioDePagamento cartao : cartoes) {
					if(cartao.getValor()<10) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O valor minimo de cada cart??o de cr??dito ?? de R$10,00");
					}
				}
			}
		}
		if(request.getStatus()==null || request.getStatus().size()==0) {
		Set<StatusPedido> statusSet = new HashSet<StatusPedido>();
		
		StatusPedido statusConcluido= new StatusPedido();
		statusConcluido.setData(new Date());
		statusConcluido.setStatus("Concluido");
		statusSet.add(statusConcluido);
		
		StatusPedido statusProc= new StatusPedido();
		statusProc.setStatus("Em Processamento");		
		statusSet.add(statusProc);
		
		request.setStatus(statusSet);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(request));
	}
	
	@PutMapping("/pedido/{id}")
	public ResponseEntity<Pedido> update(@PathVariable Long id,@RequestBody Pedido request) {
		Optional<Pedido> optional = service.findById(id);
		if(!optional.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido n??o Encontrado");
		}
		Pedido model = optional.get();
		
		return ResponseEntity.status(HttpStatus.OK).body(service.save(model));
	}
	
	@DeleteMapping("/pedido/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id){
		Optional<Pedido> optional = service.findById(id);
		if(!optional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido n??o Encontrado");
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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido n??o Encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(optional.get());
	}
	
	@GetMapping("/pedido/datas/dataInicio={dataInicio}&dataFinal={dataFinal}")
	public ResponseEntity<List<Pedido>> getByDatas(@PathVariable Date dataInicio, @PathVariable Date dataFinal) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findByDatas(dataInicio,dataFinal));
	}
}
