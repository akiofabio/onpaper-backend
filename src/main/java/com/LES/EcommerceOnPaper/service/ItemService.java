package com.LES.EcommerceOnPaper.service;

import java.text.SimpleDateFormat;
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

	public DadosGrafico findDados(Date dataInicio, Date dataFinal, String tipo, String escala) {
		ArrayList<Item> itens = (ArrayList<Item>) repository.findDados("ENTREGUE",dataInicio,dataFinal);
		ArrayList<Categoria> categorias = (ArrayList<Categoria>) categoriaRepository.findAll();
		SimpleDateFormat  formatoData = new SimpleDateFormat("ddMMyyyy");
		
		Calendar data =  Calendar.getInstance();
		ArrayList<Calendar> datas = new ArrayList<Calendar>();
		ArrayList<String> labelDatas = new ArrayList<String>();
		ArrayList<String> labelCategorias = new ArrayList<String>();
		List<List<Double>> dados = new ArrayList<List<Double>>();
		int escalaCalendar = 0;
		Calendar dataTenp = Calendar.getInstance();
				
		for(Categoria categoria : categorias) {
			labelCategorias.add(categoria.getNome());
			dados.add(new ArrayList<Double>());
		}
		data.setTime(dataInicio);
		dataTenp.setTime(data.getTime());
		datas.add(dataTenp);
		
		if(escala.equals("DIA")) {
			escalaCalendar= Calendar.DAY_OF_MONTH;
			formatoData = new SimpleDateFormat("dd/MM/yyyy");
			labelDatas.add(formatoData.format(data.getTime() ));
			data.add(escalaCalendar, 1);
			for(List<Double> dado : dados) {
				dado.add(0d);
			}
		}
		else if(escala.equals("MES")) {
			escalaCalendar= Calendar.MONTH;
			formatoData = new SimpleDateFormat("MM/yyyy");
			labelDatas.add(formatoData.format(data.getTime()));
			data.add(Calendar.DAY_OF_MONTH, -data.get(Calendar.DAY_OF_MONTH) + 1);
			data.add(escalaCalendar, 1);
			for(List<Double> dado : dados) {
				dado.add(0d);
			}
		}
		else if(escala.equals("ANO")) {
			escalaCalendar= Calendar.YEAR;
			formatoData = new SimpleDateFormat("yyyy");
			labelDatas.add(formatoData.format(data.getTime()));
			data.add(Calendar.DAY_OF_MONTH, -data.get(Calendar.DAY_OF_MONTH) + 1);
			data.add(Calendar.MONTH, -data.get(Calendar.MONTH) + 1);
			data.add(escalaCalendar, 1);
			for(List<Double> dado : dados) {
				dado.add(0d);
			}
		}
		else if(escala.equals("SEMANA")) {
			escalaCalendar= Calendar.WEEK_OF_MONTH;
			formatoData = new SimpleDateFormat("WW MM/yyyy");
		}
		
		while(data.getTime().before(dataFinal)) {
			dataTenp = Calendar.getInstance();
			dataTenp.setTime(data.getTime());
			datas.add(dataTenp);
			labelDatas.add(formatoData.format(data.getTime()));
			data.add(escalaCalendar, 1);
			for(List<Double> dado : dados) {
				dado.add(0d);
			}
		}
		dataTenp = Calendar.getInstance();
		dataTenp.setTime(dataFinal);
		datas.add(dataTenp);
		
		for(Calendar dt : datas) {
			System.out.println(dt.getTime());
		}
		if(itens!=null && !itens.isEmpty()) {
			for(Item item: itens) {
				for(StatusItem status : item.getStatus()) {
					if(status.getStatus().equals("ENTREGUE")) {
						for(int i=0; i<labelDatas.size(); i++) {
							if(status.getData().after(datas.get(i).getTime()) && status.getData().before(datas.get(i+1).getTime())){
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
		}
		DadosGrafico dadosGraficos = new DadosGrafico();
		dadosGraficos.setCategoriaLabel(labelCategorias);
		dadosGraficos.setDataLebel(labelDatas);
		dadosGraficos.setDados(dados);
		return dadosGraficos;
	}
}
