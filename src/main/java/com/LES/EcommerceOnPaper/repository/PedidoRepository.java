package com.LES.EcommerceOnPaper.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.LES.EcommerceOnPaper.model.Cliente;
import com.LES.EcommerceOnPaper.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> , JpaSpecificationExecutor<Cliente> {

	//List<Pedido> findByStatusStatusAndStatusDataGreaterThanAndStatusDataLessThan(String status,Date dataInicio, Date dataFinal);

}
