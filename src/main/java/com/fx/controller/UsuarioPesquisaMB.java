package com.fx.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.fx.BLL.UsuarioBLL;
import com.fx.dto.FiltroIN;
import com.fx.model.TbUsuario;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class UsuarioPesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioBLL usuarioBLL;
	
	private FiltroIN filtro;
	private LazyDataModel<TbUsuario> filtrados;
	
	private TbUsuario selecionado;
	
	public UsuarioPesquisaMB() {
		filtro = new FiltroIN();
		
		setFiltrados(new LazyDataModel<TbUsuario>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<TbUsuario> load(int first, int pageSize, String sortField, 
					SortOrder sortOrder, Map<String, Object> filters) {
				filtro.setFirst(first);
				filtro.setPageSize(pageSize);
				setRowCount(usuarioBLL.filtradosQTD(filtro));
				return usuarioBLL.filtrados(filtro);
			}
		});
	}
	
	public void excluir() {
		usuarioBLL.remover(selecionado);
		FacesUtil.addInfoMessage("Registro excluído com sucesso.");
	}
	
	public FiltroIN getFiltro() {
		return filtro;
	}

	public LazyDataModel<TbUsuario> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(LazyDataModel<TbUsuario> filtrados) {
		this.filtrados = filtrados;
	}

	public TbUsuario getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbUsuario selecionado) {
		this.selecionado = selecionado;
	}

	public void setFiltro(FiltroIN filtro) {
		this.filtro = filtro;
	}
	
}