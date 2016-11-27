package com.fx.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.BLL.ContratoProdutoBLL;
import com.fx.model.TbContratoProduto;
import com.fx.security.SessionContext;

@Named
@ViewScoped
public class ContratoProdutoMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/* VENDEDOR */
	@Inject
	private ContratoProdutoBLL contratoProdutoBLL;
	
	private List<TbContratoProduto> contratoProdutos;
	
	private Integer codigo;
	
	public void initContratoProdutoMB(ComponentSystemEvent event) {
		if (SessionContext.getInstance().getCdContrato() != null) {
			codigo = SessionContext.getInstance().getCdContrato();
			// Busca os produtos e carros do contrato
			contratoProdutos = contratoProdutoBLL.listarPorContrato(codigo);			
		}
	}

	public List<TbContratoProduto> getContratoProdutos() {
		return contratoProdutos;
	}

	public void setContratoProdutos(List<TbContratoProduto> itens) {
		this.contratoProdutos = itens;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


}
