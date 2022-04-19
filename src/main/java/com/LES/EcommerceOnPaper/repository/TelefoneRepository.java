package com.LES.EcommerceOnPaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LES.EcommerceOnPaper.model.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone,Long> {

}
