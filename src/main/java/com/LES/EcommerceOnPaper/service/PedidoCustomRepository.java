package com.LES.EcommerceOnPaper.service;

import java.util.List;

import com.LES.EcommerceOnPaper.model.Pedido;

public interface PedidoCustomRepository {
	List<Pedido> findParametros(List<String> pesquisa, List<String> param);
}
