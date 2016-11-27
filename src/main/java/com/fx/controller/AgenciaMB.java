package com.fx.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.constants.Msgs;
import com.fx.model.TbAgencia;
import com.fx.service.AgenciaService;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class AgenciaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AgenciaService service;
	
	private TbAgencia agencia;
		
	public AgenciaMB() {
		limpar();
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			
		}
	}
	
	private void limpar() {
		agencia = new TbAgencia();
		agencia.setDsNome(null);
	}
	
	public void salvar() {
			// Salva
			agencia = service.salvar(agencia);
			// Limpa a tela e objetos
			limpar();		
			// Mensagem de sucesso
			FacesUtil.addInfoMessage(Msgs.MSG_00);
	}

	public TbAgencia getAgencia() {
		return agencia;
	}
	
	public void setAgencia(TbAgencia Agencia) {
		agencia = Agencia;	
	}

	public boolean isEditando() {
		return agencia.getId() != null;
	}

}
