package com.fx.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.fx.BLL.AgenciaBLL;
import com.fx.dto.FiltroIN;
import com.fx.model.TbAgencia;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class AgenciaPesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private AgenciaBLL agenciaBLL;
	
	private FiltroIN filtro;
	private LazyDataModel<TbAgencia> filtrados;
	
	private TbAgencia selecionado;
	
	public AgenciaPesquisaMB() {
		
		filtro = new FiltroIN();
		
		setFiltrados(new LazyDataModel<TbAgencia>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<TbAgencia> load(int first, int pageSize, String sortField, 
					SortOrder sortOrder, Map<String, Object> filters) {
				filtro.setFirst(first);
				filtro.setPageSize(pageSize);
				setRowCount(agenciaBLL.filtradosQTD(filtro));
				return agenciaBLL.filtrados(filtro);
			}
		});
	}
		
	public void excluir() {
		agenciaBLL.remover(selecionado);
		FacesUtil.addInfoMessage("Registro excluído com sucesso.");
	}
		
	public FiltroIN getFiltro() {
		return filtro;
	}

	public TbAgencia getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbAgencia selecionado) {
		this.selecionado = selecionado;
	}

	public void setFiltro(FiltroIN filtro) {
		this.filtro = filtro;
	}

	public LazyDataModel<TbAgencia> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(LazyDataModel<TbAgencia> filtrados) {
		this.filtrados = filtrados;
	}
	
}