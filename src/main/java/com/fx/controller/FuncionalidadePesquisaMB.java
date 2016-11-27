package com.fx.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.BLL.FuncionalidadeBLL;
import com.fx.dto.FiltroIN;
import com.fx.model.TbFuncionalidade;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class FuncionalidadePesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FuncionalidadeBLL funcionalidadeBLL;
	
	private FiltroIN filtro;
	
	private List<TbFuncionalidade> modulos;
	private List<TbFuncionalidade> funcionalidades;
	
	private TbFuncionalidade selecionado;
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			pesquisar();
		}
	}
	
	public FuncionalidadePesquisaMB() {
		filtro = new FiltroIN();
	}
	
	public void pesquisar() {
		modulos = funcionalidadeBLL.listarModulos();
		filtro.setDescricao("");
	}
	
	public void pesquisarPorModulos(TbFuncionalidade modulo) {
		funcionalidades = funcionalidadeBLL.listarPorModulos(modulo.getId());
	}

	public FiltroIN getFiltro() {
		return filtro;
	}

	public List<TbFuncionalidade> getModulos() {
		return modulos;
	}

	public void setModulos(List<TbFuncionalidade> modulos) {
		this.modulos = modulos;
	}

	public TbFuncionalidade getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbFuncionalidade selecionado) {
		this.selecionado = selecionado;
	}

	public void setFiltro(FiltroIN filtro) {
		this.filtro = filtro;
	}

	public List<TbFuncionalidade> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<TbFuncionalidade> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	
}