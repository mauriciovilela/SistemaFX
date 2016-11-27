package com.fx.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.fx.BLL.ParametroBLL;
import com.fx.constants.Constants;
import com.fx.constants.Msgs;
import com.fx.model.TbParametro;
import com.fx.service.ParametroService;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ParametroMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ParametroService service;
	
	private TbParametro parametro;
	
	/* MODELO */
	@Inject
	private ParametroBLL parametroBLL;
	@NotNull
	private List<TbParametro> perfis;
	
	public ParametroMB() {
		limpar();
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			setPerfis(parametroBLL.listar());			
		}
	}
	
	private void limpar() {
		parametro = new TbParametro();
	}
	
	public void salvar() {
		// Salva
		parametro = service.salvar(parametro);
		// Limpa a tela e objetos
		limpar();		
		// Mensagem de sucesso
		FacesUtil.addInfoMessage(Msgs.MSG_00);
	}

	public TbParametro getParametro() {
		return parametro;
	}
	
	public void setParametro(TbParametro Parametro) {
		parametro = Parametro;	
	}

	public boolean isEditando() {
		return parametro.getId() != null;
	}

	public List<TbParametro> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<TbParametro> perfis) {
		this.perfis = perfis;
	}

}
