package com.LES.EcommerceOnPaper.model;

import java.util.List;

public class DadosGrafico {
	private List<String> categoriaLabel;
	private List<String> dataLebel;
	private List<List<Double>> dados;
	public List<String> getCategoriaLabel() {
		return categoriaLabel;
	}
	public void setCategoriaLabel(List<String> categoriaLabel) {
		this.categoriaLabel = categoriaLabel;
	}
	public List<String> getDataLebel() {
		return dataLebel;
	}
	public void setDataLebel(List<String> dataLebel) {
		this.dataLebel = dataLebel;
	}
	public List<List<Double>> getDados() {
		return dados;
	}
	public void setDados(List<List<Double>> dados) {
		this.dados = dados;
	}
	
	
}
