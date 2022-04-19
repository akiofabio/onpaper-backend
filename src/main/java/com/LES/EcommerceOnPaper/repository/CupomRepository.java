package com.LES.EcommerceOnPaper.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.LES.EcommerceOnPaper.model.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom,Long> {

}
