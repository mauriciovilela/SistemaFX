package com.fx.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.BLL.PerfilBLL;
import com.fx.dto.FiltroIN;
import com.fx.model.TbPerfil;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PerfilPesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PerfilBLL perfilBLL;
	
	private FiltroIN filtro;
	private List<TbPerfil> filtrados;
	
	private TbPerfil selecionado;
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			pesquisar();
		}
	}
	
	public PerfilPesquisaMB() {
		filtro = new FiltroIN();
	}
	
	public void pesquisar() {
		filtrados = perfilBLL.filtrados(filtro);
		filtro.setDescricao("");
	}

	public FiltroIN getFiltro() {
		return filtro;
	}

	public List<TbPerfil> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<TbPerfil> filtrados) {
		this.filtrados = filtrados;
	}

	public TbPerfil getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbPerfil selecionado) {
		this.selecionado = selecionado;
	}

	public void setFiltro(FiltroIN filtro) {
		this.filtro = filtro;
	}

	
}