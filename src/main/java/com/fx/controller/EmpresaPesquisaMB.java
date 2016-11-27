package com.fx.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.fx.BLL.EmpresaBLL;
import com.fx.dto.FiltroIN;
import com.fx.model.TbEmpresa;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EmpresaPesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaBLL empresaBLL;
	
	private FiltroIN filtro;
	private LazyDataModel<TbEmpresa> filtrados;
	
	private TbEmpresa selecionado;
	
	public EmpresaPesquisaMB() {
		filtro = new FiltroIN();
		
		setFiltrados(new LazyDataModel<TbEmpresa>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<TbEmpresa> load(int first, int pageSize, String sortField, 
					SortOrder sortOrder, Map<String, Object> filters) {
				filtro.setFirst(first);
				filtro.setPageSize(pageSize);
				setRowCount(empresaBLL.filtradosQTD(filtro));
				return empresaBLL.filtrados(filtro);
			}
		});
	}
	
	public void excluir() {
		empresaBLL.remover(selecionado);
		FacesUtil.addInfoMessage("Registro excluído com sucesso.");
	}

	public FiltroIN getFiltro() {
		return filtro;
	}

	public LazyDataModel<TbEmpresa> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(LazyDataModel<TbEmpresa> filtrados) {
		this.filtrados = filtrados;
	}

	public TbEmpresa getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbEmpresa selecionado) {
		this.selecionado = selecionado;
	}

	public void setFiltro(FiltroIN filtro) {
		this.filtro = filtro;
	}

	
}