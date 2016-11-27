package com.fx.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.constants.Constants;
import com.fx.constants.Msgs;
import com.fx.model.TbModelo;
import com.fx.service.ModeloService;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ModeloMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ModeloService service;
	
	private TbModelo modelo;
		
	public ModeloMB() {
		limpar();
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			
		}
	}
	
	private void limpar() {
		modelo = new TbModelo();
	}
	
	public void salvar() {
		// Salva
		modelo = service.salvar(modelo);
		// Limpa a tela e objetos
		limpar();		
		// Mensagem de sucesso
		FacesUtil.addInfoMessage(Msgs.MSG_00);
	}

	public TbModelo getModelo() {
		return modelo;
	}
	
	public void setModelo(TbModelo Modelo) {
		modelo = Modelo;	
	}

	public boolean isEditando() {
		return modelo.getId() != null;
	}

}
