package com.fx.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.constants.Msgs;
import com.fx.model.TbVendedor;
import com.fx.service.VendedorService;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class VendedorMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VendedorService service;
	
	private TbVendedor vendedor;
		
	public VendedorMB() {
		limpar();
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			
		}
	}
	
	private void limpar() {
		vendedor = new TbVendedor();
	}
	
	public void salvar() {
		// Salva
		vendedor = service.salvar(vendedor);
		// Limpa a tela e objetos
		limpar();		
		// Mensagem de sucesso
		FacesUtil.addInfoMessage(Msgs.MSG_00);
	}

	public TbVendedor getVendedor() {
		return vendedor;
	}
	
	public void setVendedor(TbVendedor Vendedor) {
		vendedor = Vendedor;	
	}

	public boolean isEditando() {
		return vendedor.getId() != null;
	}

}
