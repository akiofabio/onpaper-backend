package com.LES.EcommerceOnPaper.service;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.persistence.criteria.ParameterExpression;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

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
		Metamodel m = entityManager.getMetamodel();
		EntityType<Pedido> pedidoEntityType = m.entity(Pedido.class);
		Root<Pedido> root = query.from(pedidoEntityType);
		
		Join<Pedido, Item> itemsJoin = root.join("itens", JoinType.INNER);
        Join<Pedido, Cliente> clienteJoin = root.join("cliente", JoinType.INNER);
        Join<Pedido, StatusPedido> ultimoStatusPedidoJoin = root.join("ultimoStatus", JoinType.INNER);
        
        List<Predicate> predicates = new ArrayList<>();
        
        for(String parametro : parametros) {
        	if(parametro.equals("nomeCliente")) {
        		predicates.add(criteriaBuilder.like(clienteJoin.get("nome"), contains(conteudos.get(parametros.indexOf(parametro)))));
        	}
        	else if(parametro.equals("cpf")) {
        		predicates.add(criteriaBuilder.like(clienteJoin.get("cpf").as(String.class),contains(conteudos.get(parametros.indexOf(parametro)))));
        	}
        	
        	else if(parametro.equals("nomeProduto")) {
        		predicates.add(criteriaBuilder.like(itemsJoin.get("nomeProduto"), contains(conteudos.get(parametros.indexOf(parametro)))));
        	}
        	
        	else if(parametro.equals("status")) {
        		predicates.add(criteriaBuilder.like(ultimoStatusPedidoJoin.get("status"), contains(conteudos.get(parametros.indexOf(parametro)))));
        	}
        	
        	else if(parametro.equals("dataStatus")) {
        		predicates.add(criteriaBuilder.like(ultimoStatusPedidoJoin.get("data").as(String.class), contains(conteudos.get(parametros.indexOf(parametro)))));
        	}
        	
        	else if(parametro.equals("endereco")) {
        		predicates.add(criteriaBuilder.like(root.get("endereco"), contains(conteudos.get(parametros.indexOf(parametro)))));
        	}
        	
        	else if(parametro.equals("id")) {
        		predicates.add(criteriaBuilder.like(root.get("id").as(String.class), contains(conteudos.get(parametros.indexOf(parametro)))));
        	}
        }
        query.select(root).where(criteriaBuilder.and(predicates.toArray(new Predicate[0]))).distinct(true);
        return entityManager.createQuery(query).getResultList();
	}
	
	private static String contains(String expression) {
	    return MessageFormat.format("%{0}%", expression);
	}

}
