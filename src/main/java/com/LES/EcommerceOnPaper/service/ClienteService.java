package com.LES.EcommerceOnPaper.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Cliente;
import com.LES.EcommerceOnPaper.repository.ClienteRepository;

@Service
public class ClienteService {

	final ClienteRepository repository;
	
	public ClienteService(ClienteRepository repository) {
		super();
		this.repository = repository;
	}
	
	@Transactional
	public Cliente save(Cliente model) {
		
		return repository.save(model);
	}
	
	public void delete(Cliente model) {
		repository.delete(model);
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Optional<Cliente> findById(Long id) {
		return repository.findById(id);
	}
	
	/*public Optional<List<Cliente>> findByNomeContainsAndCpfInAndDataNascimentoIn(Optional<String> nomes, Optional<List<String>> cpfs, Optional<List<Date>> dataNacimentos){
		//return repository.findByNomeContainsAndCpfInAndDataNascimentoIn(nomes, cpfs, dataNacimentos);
		return repository.findByNomeContains(nomes);
	}
	*/
	
	public List<Cliente> findByParametros(Optional<List<String>> pesquisas, Optional<List<String>> parametros){
		List<String> nomes = new ArrayList<String>();
		List<String> cpfs =  new ArrayList<String>();
		List<Date> datasNascimento =  new ArrayList<Date>();
		
		if(!pesquisas.isPresent()) {
			return repository.findAll();
		}
		
		for(String parametro : parametros.get()) {
			if(parametro.equals("nome")) {
				System.out.println(pesquisas.get().get(parametros.get().indexOf(parametro)));
				if(!pesquisas.get().get(parametros.get().indexOf(parametro)).isBlank())
					nomes.add(pesquisas.get().get(parametros.get().indexOf(parametro)));
			}
			if(parametro.equals("cpf")) {
				cpfs.add(pesquisas.get().get(parametros.get().indexOf(parametro)));
			}
			if(parametro.equals("dataNacimento")) {
				//datasNascimento.add(
				//		pesquisas.get().get(parametros.get().indexOf(parametro)));
			}
		}
		if(nomes.isEmpty()) {
			return repository.findAll();
		}
		
		Specification<Cliente> specification = Specification
				.where(nomes.isEmpty() ? null : parametroIn( nomes, "nome" ))
			    .and(cpfs.isEmpty() ? null : parametroIn( cpfs , "cpf"))
			    //.and(datasNascimento.isEmpty() ? null : dataNascimentoIn((String[]) cpfs.toArray())
		;
		
		return repository.findAll(specification);
	}
	
	private static String contains(String expression) {
	    return MessageFormat.format("%{0}%", expression);
	}
	
	public static Specification<Cliente> parametroIn(List<String> values , String parametro) {
	    return (root, query, builder) -> builder.or(values.stream()
	        .map(value -> builder.like(root.get(parametro), contains(value)))
	        .toArray(Predicate[]::new));
	}
	
	public Optional<Cliente> findByPedidosId(Long id) {
		return repository.findByPedidosId(id);
	}
	
	public Optional<Cliente> findByPedidosItensId(Long id) {
		return repository.findByPedidosItensId(id);
	}

	public Optional<Cliente> findByCuponsId(long id) {
		return repository.findByCuponsId(id);
	}
	
}
