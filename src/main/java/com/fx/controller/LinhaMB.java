package com.fx.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.constants.Msgs;
import com.fx.model.TbLinha;
import com.fx.service.LinhaService;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class LinhaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LinhaService service;
	
	private TbLinha linha;
		
	public LinhaMB() {
		limpar();
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			
		}
	}
	
	private void limpar() {
		linha = new TbLinha();
	}
	
	public void salvar() {
		// Salva
		linha = service.salvar(linha);
		// Limpa a tela e objetos
		limpar();		
		// Mensagem de sucesso
		FacesUtil.addInfoMessage(Msgs.MSG_00);
	}

	public TbLinha getLinha() {
		return linha;
	}
	
	public void setLinha(TbLinha Linha) {
		linha = Linha;	
	}

	public boolean isEditando() {
		return linha.getId() != null;
	}

}
