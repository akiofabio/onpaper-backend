package com.LES.EcommerceOnPaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LES.EcommerceOnPaper.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {

}