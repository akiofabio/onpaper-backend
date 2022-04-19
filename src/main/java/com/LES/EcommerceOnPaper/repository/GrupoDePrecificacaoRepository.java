package com.LES.EcommerceOnPaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LES.EcommerceOnPaper.model.GrupoDePrecificacao;

@Repository
public interface GrupoDePrecificacaoRepository extends JpaRepository<GrupoDePrecificacao,Long>{

}
