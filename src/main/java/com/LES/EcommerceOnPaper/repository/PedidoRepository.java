package com.LES.EcommerceOnPaper.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.LES.EcommerceOnPaper.model.Cliente;
import com.LES.EcommerceOnPaper.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> , JpaSpecificationExecutor<Cliente> {
	@Query( value="select DISTINCT * from onpaperdatabase.pedidos p "
			+ "	join onpaperdatabase.pedidos_ｓtatus p_s on p.ped_id = p_s.pedido_ped_id"
			+ " join onpaperdatabase.status_pedidos st on p_s.ｓtatus_stp_id = st.stp_id"
			+ " where stp_status = 'Em Processamento' or stp_status = 'Em Troca' or "
			+ "stp_status = 'Aprovado' or stp_status ='Em Preparo' or stp_status ='Em Devolução'", nativeQuery = true)
	List<Pedido> findByPendentes();

	@Query( value="select * from onpaperdatabase.pedidos p "
			+ "	join onpaperdatabase.pedidos_ｓtatus p_s on p.ped_id = p_s.pedido_ped_id"
			+ " join onpaperdatabase.status_pedidos st on p_s.ｓtatus_stp_id = st.stp_id"
			+ " where stp_status = :status and stp_data > :dataInicio and stp_data < :dataFinal", nativeQuery = true)
	List<Pedido> findDados(String status,Date dataInicio, Date dataFinal);

	Optional<Pedido> findByItensId(Long id);

}
