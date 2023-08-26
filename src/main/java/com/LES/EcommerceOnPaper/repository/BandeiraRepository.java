package com.LES.EcommerceOnPaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LES.EcommerceOnPaper.model.Bandeira;



@Repository
public interface BandeiraRepository extends JpaRepository<Bandeira,Long> {

}
