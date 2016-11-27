package com.fx.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.constants.Constants;
import com.fx.constants.Msgs;
import com.fx.model.TbEmpresa;
import com.fx.service.EmpresaService;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class EmpresaMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EmpresaService service;
	
	private TbEmpresa empresa;
		
	public EmpresaMB() {
		limpar();
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			
		}
	}
	
	private void limpar() {
		empresa = new TbEmpresa();
	}
	
	public void salvar() {
		// Salva
		empresa = service.salvar(empresa);
		// Limpa a tela e objetos
		limpar();		
		// Mensagem de sucesso
		FacesUtil.addInfoMessage(Msgs.MSG_00);
	}

	public TbEmpresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(TbEmpresa Empresa) {
		empresa = Empresa;	
	}

	public boolean isEditando() {
		return empresa.getId() != null;
	}

}
