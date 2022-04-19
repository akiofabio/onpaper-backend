package com.LES.EcommerceOnPaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LES.EcommerceOnPaper.model.Inativacao;

@Repository
public interface InativacaoRepository extends JpaRepository<Inativacao,Long> {

}
