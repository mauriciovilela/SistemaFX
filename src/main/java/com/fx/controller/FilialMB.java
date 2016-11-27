package com.fx.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.constants.Constants;
import com.fx.constants.Msgs;
import com.fx.model.TbFilial;
import com.fx.service.FilialService;
import com.fx.util.Util;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class FilialMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FilialService service;
	
	private TbFilial filial;

	public FilialMB() {
		limpar();
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
				
		}
	}
	
	private void limpar() {
		filial = new TbFilial();
	}
	
	public void salvar() {
		// Valida CNPJ
		Util.validaCNPJ(filial.getNrCnpj());
		// Salva
		filial = service.salvar(filial);
		// Limpa a tela e objetos
		limpar();		
		// Mensagem de sucesso
		FacesUtil.addInfoMessage(Msgs.MSG_00);
	}

	public TbFilial getFilial() {
		return filial;
	}
	
	public void setFilial(TbFilial Filial) {
		filial = Filial;	
	}

	public boolean isEditando() {
		return filial.getId() != null;
	}

}
