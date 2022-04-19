package com.LES.EcommerceOnPaper.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.LES.EcommerceOnPaper.model.MeioDePagamento;

@Repository
public interface MeioDePagamentoRepository extends JpaRepository<MeioDePagamento,Long> {

}
