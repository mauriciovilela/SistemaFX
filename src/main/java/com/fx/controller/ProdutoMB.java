package com.fx.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.BLL.TipoProdutoBLL;
import com.fx.constants.Constants;
import com.fx.constants.Msgs;
import com.fx.model.TbProduto;
import com.fx.model.TbTipoProduto;
import com.fx.service.ProdutoService;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ProdutoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoService service;
	
	private TbProduto produto;
	
	@Inject
	private TipoProdutoBLL tipoProdutoBLL;
	private List<TbTipoProduto> tipos;
	
	public ProdutoMB() {
		limpar();
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			tipos = tipoProdutoBLL.listar();
		}
	}
	
	private void limpar() {
		produto = new TbProduto();
	}
	
	public void salvar() {
		// Salva
		produto = service.salvar(produto);
		// Limpa a tela e objetos
		limpar();		
		// Mensagem de sucesso
		FacesUtil.addInfoMessage(Msgs.MSG_00);
	}

	public TbProduto getProduto() {
		return produto;
	}
	
	public void setProduto(TbProduto Produto) {
		produto = Produto;	
	}

	public boolean isEditando() {
		return produto.getId() != null;
	}

	public List<TbTipoProduto> getTipos() {
		return tipos;
	}

	public void setTipos(List<TbTipoProduto> tipos) {
		this.tipos = tipos;
	}

}
