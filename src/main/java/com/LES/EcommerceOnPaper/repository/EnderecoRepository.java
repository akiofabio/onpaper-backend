package com.LES.EcommerceOnPaper.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.LES.EcommerceOnPaper.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Long> {

}
