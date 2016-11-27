package com.fx.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.fx.BLL.ModeloBLL;
import com.fx.dto.FiltroIN;
import com.fx.model.TbModelo;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ModeloPesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ModeloBLL modeloBLL;
	
	private FiltroIN filtro;
	private LazyDataModel<TbModelo> filtrados;
	
	private TbModelo selecionado;
	
	public ModeloPesquisaMB() {
		filtro = new FiltroIN();
		
		setFiltrados(new LazyDataModel<TbModelo>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<TbModelo> load(int first, int pageSize, String sortField, 
					SortOrder sortOrder, Map<String, Object> filters) {
				filtro.setFirst(first);
				filtro.setPageSize(pageSize);
				setRowCount(modeloBLL.filtradosQTD(filtro));
				return modeloBLL.filtrados(filtro);
			}
		});

	}
	
	public void excluir() {
		modeloBLL.remover(selecionado);
		FacesUtil.addInfoMessage("Registro excluído com sucesso.");
	}

	public FiltroIN getFiltro() {
		return filtro;
	}

	public LazyDataModel<TbModelo> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(LazyDataModel<TbModelo> filtrados) {
		this.filtrados = filtrados;
	}

	public TbModelo getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbModelo selecionado) {
		this.selecionado = selecionado;
	}

	public void setFiltro(FiltroIN filtro) {
		this.filtro = filtro;
	}

	
}