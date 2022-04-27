package com.LES.EcommerceOnPaper.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LES.EcommerceOnPaper.model.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho,Long> {

	public List<Carrinho> findAllByOrderById(); 
}
