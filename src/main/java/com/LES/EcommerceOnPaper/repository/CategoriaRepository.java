package com.LES.EcommerceOnPaper.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.LES.EcommerceOnPaper.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long>{

}
