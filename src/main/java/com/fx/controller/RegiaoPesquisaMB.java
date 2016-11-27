package com.fx.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.fx.BLL.RegiaoBLL;
import com.fx.dto.FiltroIN;
import com.fx.model.TbRegiao;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class RegiaoPesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private RegiaoBLL regiaoBLL;
	
	private FiltroIN filtro;
	private LazyDataModel<TbRegiao> filtrados;
	
	private TbRegiao selecionado;
	
	public RegiaoPesquisaMB() {
		
		filtro = new FiltroIN();
		
		setFiltrados(new LazyDataModel<TbRegiao>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<TbRegiao> load(int first, int pageSize, String sortField, 
					SortOrder sortOrder, Map<String, Object> filters) {
				filtro.setFirst(first);
				filtro.setPageSize(pageSize);
				setRowCount(regiaoBLL.filtradosQTD(filtro));
				return regiaoBLL.filtrados(filtro);
			}
		});
	}
		
	public void excluir() {
		regiaoBLL.remover(selecionado);
		FacesUtil.addInfoMessage("Registro excluído com sucesso.");
	}
		
	public FiltroIN getFiltro() {
		return filtro;
	}

	public TbRegiao getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbRegiao selecionado) {
		this.selecionado = selecionado;
	}

	public void setFiltro(FiltroIN filtro) {
		this.filtro = filtro;
	}

	public LazyDataModel<TbRegiao> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(LazyDataModel<TbRegiao> filtrados) {
		this.filtrados = filtrados;
	}
	
}