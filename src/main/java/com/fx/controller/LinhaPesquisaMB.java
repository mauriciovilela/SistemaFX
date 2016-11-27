package com.fx.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.fx.BLL.LinhaBLL;
import com.fx.dto.FiltroIN;
import com.fx.model.TbLinha;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class LinhaPesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private LinhaBLL linhaBLL;
	
	private FiltroIN filtro;
	private LazyDataModel<TbLinha> filtrados;
	
	private TbLinha selecionado;
	
	public LinhaPesquisaMB() {
		
		filtro = new FiltroIN();
		
		setFiltrados(new LazyDataModel<TbLinha>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<TbLinha> load(int first, int pageSize, String sortField, 
					SortOrder sortOrder, Map<String, Object> filters) {
				filtro.setFirst(first);
				filtro.setPageSize(pageSize);
				setRowCount(linhaBLL.filtradosQTD(filtro));
				return linhaBLL.filtrados(filtro);
			}
		});
	}
		
	@SuppressWarnings("unchecked")
	public void excluir() {
		linhaBLL.remover(selecionado);
		((List<TbLinha>) filtrados).remove(selecionado);
		FacesUtil.addInfoMessage("Registro excluído com sucesso.");
	}
		
	public FiltroIN getFiltro() {
		return filtro;
	}

	public TbLinha getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbLinha selecionado) {
		this.selecionado = selecionado;
	}

	public void setFiltro(FiltroIN filtro) {
		this.filtro = filtro;
	}

	public LazyDataModel<TbLinha> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(LazyDataModel<TbLinha> filtrados) {
		this.filtrados = filtrados;
	}
	
}