package com.LES.EcommerceOnPaper.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LES.EcommerceOnPaper.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{
	List<Produto> findByNomeLike(String nome);
	
	List<Produto> findByNomeLikeAndCategoriasNomeIn(String nome, Optional<List<String>> categorias);
	List<Produto> findByNomeLikeAndCategoriasNomeInAndFabricanteNomeIn(String string, Optional<List<String>> categorias, Optional<List<String>> fabricantes);
	List<Produto> findByNomeLikeAndFabricanteIn(String nome, Optional<List<String>> fabricantes);
	
	List<Produto> findByCategoriasNomeInAndFabricanteNomeIn(Optional<List<String>> categorias, Optional<List<String>> fabricantes);
	List<Produto> findByCategoriasNomeIn(Optional<List<String>> categorias);
	List<Produto> findByFabricanteNomeIn(Optional<List<String>> fabricantes);
}
