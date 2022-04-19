package com.LES.EcommerceOnPaper.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.LES.EcommerceOnPaper.model.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao,Long> {

}
