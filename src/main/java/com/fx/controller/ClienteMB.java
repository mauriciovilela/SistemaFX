package com.fx.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.constants.Constants;
import com.fx.constants.Msgs;
import com.fx.model.TbCliente;
import com.fx.service.ClienteService;
import com.fx.util.Util;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ClienteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteService clienteService;
	
	private TbCliente cliente;
		
	public ClienteMB() {
		limpar();
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			
		}
	}
	
	private void limpar() {
		cliente = new TbCliente();
	}
	
	public void salvar() {
		// Valida CNPJ
		Util.validaCNPJ(cliente.getNrCnpj());
		cliente.setDsEmail(cliente.getDsEmail().toLowerCase());
		// Salva
		cliente = clienteService.salvar(cliente);
		// Limpa a tela e objetos
		limpar();		
		FacesUtil.addInfoMessage(Msgs.MSG_00);
	}

	public TbCliente getCliente() {
		return cliente;
	}
	
	public void setCliente(TbCliente Cliente) {
		cliente = Cliente;	
	}

	public boolean isEditando() {
		return cliente.getId() != null;
	}

}
