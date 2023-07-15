package com.LES.EcommerceOnPaper.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.LES.EcommerceOnPaper.model.Item;
import com.LES.EcommerceOnPaper.model.Pedido;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
	
	@Query( value="select * from onpaperdatabase.itens i "
			+ "	join onpaperdatabase.itens_status i_st on i.ite_id = i_st.item_ite_id"
			+ " join onpaperdatabase.status_itens st on status_sti_id = st.sti_id"
			+ " where sti_status = :status and sti_data > :dataInicio and sti_data < :dataFinal", nativeQuery = true)
	List<Item> findDados(String status,Date dataInicio, Date dataFinal);

}
