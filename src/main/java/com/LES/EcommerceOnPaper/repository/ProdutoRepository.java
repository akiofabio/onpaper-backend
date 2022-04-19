package com.LES.EcommerceOnPaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LES.EcommerceOnPaper.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{

}
