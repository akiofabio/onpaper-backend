package com.LES.EcommerceOnPaper.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LES.EcommerceOnPaper.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {

	List<Pedido> findByPedidoStatusStatusAndPedidoStatusDataGreaterThanAndPedidoStatusDataLessThan(String status,Date dataInicio, Date dataFinal);

}
