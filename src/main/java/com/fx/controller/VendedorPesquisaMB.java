package com.fx.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.fx.BLL.VendedorBLL;
import com.fx.dto.FiltroIN;
import com.fx.model.TbVendedor;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class VendedorPesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private VendedorBLL vendedorBLL;
	
	private FiltroIN filtro;
	private LazyDataModel<TbVendedor> filtrados;
	
	private TbVendedor selecionado;
	
	public VendedorPesquisaMB() {
		filtro = new FiltroIN();
		
		setFiltrados(new LazyDataModel<TbVendedor>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<TbVendedor> load(int first, int pageSize, String sortField, 
					SortOrder sortOrder, Map<String, Object> filters) {
				filtro.setFirst(first);
				filtro.setPageSize(pageSize);
				setRowCount(vendedorBLL.filtradosQTD(filtro));
				return vendedorBLL.filtrados(filtro);
			}
		});
	}
	
	public void excluir() {
		vendedorBLL.remover(selecionado);
		FacesUtil.addInfoMessage("Registro excluído com sucesso.");
	}
	
	public FiltroIN getFiltro() {
		return filtro;
	}

	public LazyDataModel<TbVendedor> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(LazyDataModel<TbVendedor> filtrados) {
		this.filtrados = filtrados;
	}

	public TbVendedor getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbVendedor selecionado) {
		this.selecionado = selecionado;
	}

	public void setFiltro(FiltroIN filtro) {
		this.filtro = filtro;
	}

}