package com.LES.EcommerceOnPaper.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.LES.EcommerceOnPaper.model.Fabricante;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante,Long> {

}
