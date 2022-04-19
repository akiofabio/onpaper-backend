package com.LES.EcommerceOnPaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LES.EcommerceOnPaper.model.Ativacao;

@Repository
public interface AtivacaoRepository extends JpaRepository<Ativacao,Long>{

}
