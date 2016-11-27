package com.fx.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.BLL.ParametroBLL;
import com.fx.dto.FiltroIN;
import com.fx.model.TbParametro;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ParametroPesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ParametroBLL parametroBLL;
	
	private FiltroIN filtro;
	private List<TbParametro> filtrados;
	
	private TbParametro selecionado;
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			pesquisar();
		}
	}
	
	public ParametroPesquisaMB() {
		filtro = new FiltroIN();
	}
		
	public void pesquisar() {
		filtrados = parametroBLL.filtrados(filtro);
		filtro.setDescricao("");
	}

	public FiltroIN getFiltro() {
		return filtro;
	}

	public List<TbParametro> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<TbParametro> filtrados) {
		this.filtrados = filtrados;
	}

	public TbParametro getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbParametro selecionado) {
		this.selecionado = selecionado;
	}

	public void setFiltro(FiltroIN filtro) {
		this.filtro = filtro;
	}

	
}