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
import com.LES.EcommerceOnPaper.service.PedidoCustomRepository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> , JpaSpecificationExecutor<Cliente>, PedidoCustomRepository {
	@Query( value="SELECT  *"
			+ "	FROM onpaperdatabase.pedidos p INNER JOIN"
			+ "		("
			+ "			SELECT pedido_ped_id, MAX(status_stp_id) ultimo_id"
			+ "			FROM onpaperdatabase.pedidos_status p_s join"
			+ "				 onpaperdatabase.status_pedidos st1 on p_s.status_stp_id = st1.stp_id"
			+ "			GROUP BY pedido_ped_id"
			+ "		) MaxDates ON p.ped_id = MaxDates.pedido_ped_id INNER JOIN"
			+ "		onpaperdatabase.status_pedidos st2 ON   MaxDates.ultimo_id = st2.stp_id"
			+ "	WHERE stp_status = 'Em Processamento' or stp_status = 'Em Troca' or "
			+ "	stp_status = 'Aprovado' or stp_status ='Em Preparo' or stp_status ='Em Troca' or"
			+ "	stp_status ='Em Preparo' or stp_status ='Enviado' or stp_status ='Troca Aprovada'", nativeQuery = true)
	Optional<List<Pedido>> findByPendentes();

	@Query( value="select * from onpaperdatabase.pedidos p "
			+ "	join onpaperdatabase.pedidos_ｓtatus p_s on p.ped_id = p_s.pedido_ped_id"
			+ " join onpaperdatabase.status_pedidos st on p_s.ｓtatus_stp_id = st.stp_id"
			+ " where stp_status = :status and stp_data > :dataInicio and stp_data < :dataFinal", nativeQuery = true)
	List<Pedido> findDados(String status,Date dataInicio, Date dataFinal);

	Optional<Pedido> findByItensId(Long id);
	
}


