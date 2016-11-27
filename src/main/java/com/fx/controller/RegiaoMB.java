package com.fx.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.constants.Msgs;
import com.fx.model.TbRegiao;
import com.fx.service.RegiaoService;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class RegiaoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private RegiaoService service;
	
	private TbRegiao regiao;
		
	public RegiaoMB() {
		limpar();
	}
	
	private void limpar() {
		regiao = new TbRegiao();
	}
	
	public void salvar() {
		// Salva
		regiao = service.salvar(regiao);
		// Limpa a tela e objetos
		limpar();		
		// Mensagem de sucesso
		FacesUtil.addInfoMessage(Msgs.MSG_00);
	}

	public TbRegiao getRegiao() {
		return regiao;
	}
	
	public void setRegiao(TbRegiao tbRegiao) {
		regiao = tbRegiao;	
	}

	public boolean isEditando() {
		return regiao.getId() != null;
	}

}
