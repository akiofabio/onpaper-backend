package com.LES.EcommerceOnPaper.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Produto;
import com.LES.EcommerceOnPaper.repository.ProdutoRepository;

@Service
public class ProdutoService {

	final ProdutoRepository repository;
	
	public ProdutoService(ProdutoRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Produto save(Produto model) {
		return repository.save(model);
	}
	
	public void delete(Produto model) {
		repository.delete(model);
	}
	
	public List<Produto> findAll() {
		return repository.findAll();
	}
	
	public Optional<Produto> findById(Long id) {
		return repository.findById(id);
	}
	public List<Produto> findByNome(String nome) {
		return repository.findByNomeLike("%"+nome+"%");
	}
	public List<Produto> findByNomeAndCategoriaIn(String nome, Optional<List<String>> categorias) {
		return repository.findByNomeLikeAndCategoriasNomeIn("%"+nome+"%",categorias);
	}
	
	public List<Produto> findByNomeAndCategoriasInAndFabricanteIn(String nome, Optional<List<String>> categorias, Optional<List<String>> fabricantes) {
		return repository.findByNomeLikeAndCategoriasNomeInAndFabricanteNomeIn("%"+nome+"%",categorias,fabricantes);
	}

	public List<Produto> findByNomeAndFabricanteIn(String nome, Optional<List<String>> fabricantes) {
		return repository.findByNomeLikeAndFabricanteIn("%"+nome+"%",fabricantes);
	}

	public List<Produto> findByCategoriasInAndFabricanteIn(Optional<List<String>> categorias, Optional<List<String>> fabricantes) {
		return repository.findByCategoriasNomeInAndFabricanteNomeIn(categorias, fabricantes);
	}

	public List<Produto> findByCategoriaIn(Optional<List<String>> categorias) {
		return repository.findByCategoriasNomeIn(categorias);
	}
	
	public List<Produto> findByFabricanteIn(Optional<List<String>> fabricantes) {
		return repository.findByFabricanteNomeIn(fabricantes);
	}
}
