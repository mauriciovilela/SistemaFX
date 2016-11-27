package com.fx.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.BLL.FuncionalidadeBLL;
import com.fx.constants.Constants;
import com.fx.constants.Msgs;
import com.fx.model.TbFuncionalidade;
import com.fx.service.FuncionalidadeService;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class FuncionalidadeMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionalidadeService service;
	
	private TbFuncionalidade funcionalidade;
	private List<TbFuncionalidade> modulos;
	
	@Inject
	private FuncionalidadeBLL funcionalidadeBLL;
	
	public FuncionalidadeMB() {
		limpar();
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			setModulos(funcionalidadeBLL.listarModulos());
		}
	}
	
	private void limpar() {
		funcionalidade = new TbFuncionalidade();
	}
	
	public void salvar() {
		// Salva
		funcionalidade = service.salvar(funcionalidade);
		// Limpa a tela e objetos
		limpar();		
		// Mensagem de sucesso
		FacesUtil.addInfoMessage(Msgs.MSG_00);
	}

	public TbFuncionalidade getFuncionalidade() {
		return funcionalidade;
	}
	
	public void setFuncionalidade(TbFuncionalidade Funcionalidade) {
		funcionalidade = Funcionalidade;	
	}

	public boolean isEditando() {
		return funcionalidade.getId() != null;
	}

	public List<TbFuncionalidade> getModulos() {
		return modulos;
	}

	public void setModulos(List<TbFuncionalidade> modulos) {
		this.modulos = modulos;
	}

}
