package com.fx.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.fx.BLL.ProdutoBLL;
import com.fx.dto.FiltroIN;
import com.fx.model.TbProduto;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ProdutoPesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ProdutoBLL produtoBLL;
	
	private FiltroIN filtro;
	private LazyDataModel<TbProduto> filtrados;
	
	private TbProduto selecionado;

	public ProdutoPesquisaMB() {
		filtro = new FiltroIN();
		
		setFiltrados(new LazyDataModel<TbProduto>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<TbProduto> load(int first, int pageSize, String sortField, 
					SortOrder sortOrder, Map<String, Object> filters) {
				filtro.setFirst(first);
				filtro.setPageSize(pageSize);
				setRowCount(produtoBLL.filtradosQTD(filtro));
				return produtoBLL.filtrados(filtro);
			}
		});

	}
	
	public void excluir() {
		produtoBLL.remover(selecionado);
		FacesUtil.addInfoMessage("Registro excluído com sucesso.");
	}

	public FiltroIN getFiltro() {
		return filtro;
	}

	public LazyDataModel<TbProduto> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(LazyDataModel<TbProduto> filtrados) {
		this.filtrados = filtrados;
	}

	public TbProduto getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbProduto selecionado) {
		this.selecionado = selecionado;
	}

	public void setFiltro(FiltroIN filtro) {
		this.filtro = filtro;
	}
	
}