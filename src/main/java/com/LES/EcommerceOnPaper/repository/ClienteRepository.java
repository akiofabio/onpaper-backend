package com.LES.EcommerceOnPaper.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.LES.EcommerceOnPaper.model.Cliente;
import com.LES.EcommerceOnPaper.model.Item;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>,JpaSpecificationExecutor<Cliente>{

	Optional<List<Cliente>> findByNomeContainsAndCpfInAndDataNascimentoIn(Optional<String> nomes, Optional<List<String>> cpfs, Optional<List<Date>> dataNacimentos);

	Optional<List<Cliente>> findByNomeContains(Optional<String> nomes);
	
	Optional<Cliente> findByPedidosId(Long id);
	
	Optional<Cliente> findByPedidosItensId(Long id);

	Optional<Cliente> findByCuponsId(long id);

	Optional<Cliente> findByCartoesId(long id);
}
