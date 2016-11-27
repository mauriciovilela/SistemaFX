package com.fx.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.fx.BLL.FilialBLL;
import com.fx.dto.FiltroIN;
import com.fx.model.TbFilial;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class FilialPesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FilialBLL filialBLL;
	
	private FiltroIN filtro;
	private LazyDataModel<TbFilial> filtrados;
	
	private TbFilial selecionado;
	
	public FilialPesquisaMB() {
		filtro = new FiltroIN();
		
		setFiltrados(new LazyDataModel<TbFilial>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<TbFilial> load(int first, int pageSize, String sortField, 
					SortOrder sortOrder, Map<String, Object> filters) {
				filtro.setFirst(first);
				filtro.setPageSize(pageSize);
				setRowCount(filialBLL.filtradosQTD(filtro));
				return filialBLL.filtrados(filtro);
			}
		});

	}
	
	public void excluir() {
		filialBLL.remover(selecionado);
		FacesUtil.addInfoMessage("Registro excluído com sucesso.");
	}

	public FiltroIN getFiltro() {
		return filtro;
	}

	public LazyDataModel<TbFilial> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(LazyDataModel<TbFilial> filtrados) {
		this.filtrados = filtrados;
	}

	public TbFilial getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbFilial selecionado) {
		this.selecionado = selecionado;
	}

	public void setFiltro(FiltroIN filtro) {
		this.filtro = filtro;
	}

	
}