package com.LES.EcommerceOnPaper.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LES.EcommerceOnPaper.model.Categoria;
import com.LES.EcommerceOnPaper.model.Cliente;
import com.LES.EcommerceOnPaper.model.DadosGrafico;
import com.LES.EcommerceOnPaper.model.Item;
import com.LES.EcommerceOnPaper.model.Pedido;
import com.LES.EcommerceOnPaper.model.Produto;
import com.LES.EcommerceOnPaper.model.StatusItem;
import com.LES.EcommerceOnPaper.model.StatusPedido;
import com.LES.EcommerceOnPaper.repository.CategoriaRepository;
import com.LES.EcommerceOnPaper.repository.ItemRepository;
import com.LES.EcommerceOnPaper.repository.ProdutoRepository;

@Service
public class ItemService {

	final ItemRepository repository;
	final CategoriaRepository categoriaRepository;
	final ProdutoRepository produtoRepository;
	
	public ItemService(ItemRepository repository, CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
		super();
		this.repository = repository;
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
	}
	
	@Transactional
	public Item save(Item model) {
		return repository.save(model);
	}
	
	@Transactional
	public void delete(Item model) {
		repository.delete(model);
	}
	
	public List<Item> findAll() {
		return repository.findAll();
	}
	
	public Optional<Item> findById(Long id) {
		return repository.findById(id);
	}

	public DadosGrafico findDados(LocalDateTime dataInicio, LocalDateTime dataFinal, String tipo, String escala) {
		ArrayList<Item> itens = (ArrayList<Item>) repository.findDados("ENTREGUE",dataInicio,dataFinal);
		ArrayList<Categoria> categorias = (ArrayList<Categoria>) categoriaRepository.findAll();
		String  formatoData = "ddMMyyyy";
		
		
		ArrayList<LocalDateTime> datas = new ArrayList<LocalDateTime>();
		ArrayList<String> labelDatas = new ArrayList<String>();
		ArrayList<String> labelCategorias = new ArrayList<String>();
		List<List<Double>> dados = new ArrayList<List<Double>>();
		ChronoUnit escalaCalendar = ChronoUnit.MONTHS;
		
		LocalDateTime data =  LocalDateTime.now();
		LocalDateTime dataTemp = LocalDateTime.now();
		
		for(Categoria categoria : categorias) {
			labelCategorias.add(categoria.getNome());
			dados.add(new ArrayList<Double>());
		}
		data = LocalDateTime.parse(dataInicio.toString());
		dataTemp = LocalDateTime.parse(data.toString());
		datas.add(dataTemp);
		
		if(escala.equals("DIA")) {
			escalaCalendar= ChronoUnit.DAYS;
			formatoData = "dd/MM/yyyy";
			labelDatas.add(DateTimeFormatter.ofPattern(formatoData).format(data));
			data = data.plus(1,escalaCalendar);
			for(List<Double> dado : dados) {
				dado.add(0d);
			}
		}
		else if(escala.equals("MES")) {
			escalaCalendar= ChronoUnit.MONTHS;
			formatoData = "MM/yyyy";
			labelDatas.add(DateTimeFormatter.ofPattern(formatoData).format(data));
			data = data.plus(-data.getDayOfMonth() + 1,ChronoUnit.DAYS);
			data = data.plus(1,escalaCalendar);
			for(List<Double> dado : dados) {
				dado.add(0d);
			}
		}
		else if(escala.equals("ANO")) {
			escalaCalendar= ChronoUnit.YEARS;
			formatoData = "yyyy";
			labelDatas.add(DateTimeFormatter.ofPattern(formatoData).format(data));
			data = data.plus(-data.getDayOfMonth() + 1,ChronoUnit.DAYS);
			data = data.plus(-data.getMonthValue() + 1,ChronoUnit.MONTHS);
			data = data.plus(1 , escalaCalendar);
			for(List<Double> dado : dados) {
				dado.add(0d);
			}
		}
		while(data.isBefore(dataFinal)) {
			dataTemp = LocalDateTime.now();
			dataTemp = LocalDateTime.parse(data.toString());
			datas.add(dataTemp);
			labelDatas.add(DateTimeFormatter.ofPattern(formatoData).format(data));
			data = data.plus(1,escalaCalendar);
			for(List<Double> dado : dados) {
				dado.add(0d);
			}
		}
		dataTemp = LocalDateTime.now();
		dataTemp = LocalDateTime.parse(dataFinal.toString());
		datas.add(dataTemp);
		
		for(LocalDateTime dt : datas) {
			System.out.println(dt);
		}
		if(itens!=null && !itens.isEmpty()) {
			for(Item item: itens) {
				if(item.getUltimoStatus().getStatus().equals("Entregue")) {
					for(int i=0; i<labelDatas.size(); i++) {
						if(item.getUltimoStatus().getData().isAfter(datas.get(i)) && item.getUltimoStatus().getData().isBefore(datas.get(i+1))){
							Produto produto = produtoRepository.getById(item.getIdProduto());
							for(Categoria categoria : produto.getCategorias()) {
								for(int j = 0; j < labelCategorias.size(); j++) {
									if(labelCategorias.get(j).equals(categoria.getNome())){
										if(tipo.equals("VALOR")) {
											dados.get(j).set(i, item.getPreco() * item.getQuantidade());
										}
										else if(tipo.equals("QUANTIDADE")) {
											dados.get(j).set(i,dados.get(j).get(i) + (double) item.getQuantidade());
										}
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		DadosGrafico dadosGraficos = new DadosGrafico();
		dadosGraficos.setCategoriaLabel(labelCategorias);
		dadosGraficos.setDataLebel(labelDatas);
		dadosGraficos.setDados(dados);
		return dadosGraficos;
	}
}
