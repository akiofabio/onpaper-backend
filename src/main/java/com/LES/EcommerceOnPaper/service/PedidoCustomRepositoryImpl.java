package com.LES.EcommerceOnPaper.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.LES.EcommerceOnPaper.model.Pedido;
import com.LES.EcommerceOnPaper.model.Produto;
import com.LES.EcommerceOnPaper.model.StatusPedido;
import com.LES.EcommerceOnPaper.model.Cliente;
import com.LES.EcommerceOnPaper.model.Item;

public class PedidoCustomRepositoryImpl implements PedidoCustomRepository {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Pedido> findParametros(List<String> conteudos, List<String> parametros) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pedido> query = criteriaBuilder.createQuery(Pedido.class);
		Root<Pedido> root = query.from(Pedido.class);
		Root<Cliente> rootCliente = query.from(Cliente.class);
		
		Join<Pedido, Item> itemsJoin = root.join("itens", JoinType.INNER);
        Join<Pedido, StatusPedido> statusPedidoJoin = root.join("status", JoinType.INNER);
        Join<Pedido, Cliente> clienteJoin = root.join("cliente", JoinType.INNER);
        //Join<Item, Produto> produtoJoin = itemsJoin.join("idProduto", JoinType.INNER);
        
        List<Predicate> predicates = new ArrayList<>();
        
        for(String parametro : parametros) {
        	if(parametro.equals("nomeCliente")) {
        		predicates.add(criteriaBuilder.like(clienteJoin.get("nome"), contains(conteudos.get(parametros.indexOf(parametro)))));
        	}
        	
        	if(parametro.equals("nomeProduto")) {
        		predicates.add(criteriaBuilder.like(itemsJoin.get("nomeProduto"), contains(conteudos.get(parametros.indexOf(parametro)))));
        	}
        	if(parametro.equals("status")) {
        		predicates.add(criteriaBuilder.like(statusPedidoJoin.get("status"), contains(conteudos.get(parametros.indexOf(parametro)))));
        	}
        	if(parametro.equals("endereco")) {
        		predicates.add(criteriaBuilder.like(root.get("endereco"), contains(conteudos.get(parametros.indexOf(parametro)))));
        	}
        	if(parametro.equals("id")) {
        		predicates.add(criteriaBuilder.equal(root.get("id"), Long.parseLong(conteudos.get(parametros.indexOf(parametro)))));
        	}
        }
        System.out.println(predicates.size());
        query.select(root).where(criteriaBuilder.and(predicates.toArray(new Predicate[0]))).distinct(true);
        return entityManager.createQuery(query).getResultList();
	}
	
	private static String contains(String expression) {
	    return MessageFormat.format("%{0}%", expression);
	}

}
