package com.fx.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.fx.BLL.ClienteBLL;
import com.fx.dto.FiltroIN;
import com.fx.model.TbCliente;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ClientePesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ClienteBLL clienteBLL;
	
	private FiltroIN filtro;
	private LazyDataModel<TbCliente> filtrados;
	
	private TbCliente selecionado;
	
	public ClientePesquisaMB() {
		filtro = new FiltroIN();
		
		setFiltrados(new LazyDataModel<TbCliente>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<TbCliente> load(int first, int pageSize, String sortField, 
					SortOrder sortOrder, Map<String, Object> filters) {
				filtro.setFirst(first);
				filtro.setPageSize(pageSize);
				setRowCount(clienteBLL.filtradosQTD(filtro));
				return clienteBLL.filtrados(filtro);
			}
		});
	}
	
	public void excluir() {
		clienteBLL.remover(selecionado);
		FacesUtil.addInfoMessage("Registro excluído com sucesso.");
	}

	public FiltroIN getFiltro() {
		return filtro;
	}

	public LazyDataModel<TbCliente> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(LazyDataModel<TbCliente> filtrados) {
		this.filtrados = filtrados;
	}

	public TbCliente getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbCliente selecionado) {
		this.selecionado = selecionado;
	}

	public void setFiltro(FiltroIN filtro) {
		this.filtro = filtro;
	}

}