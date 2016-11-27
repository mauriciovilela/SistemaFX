package com.fx.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.fx.security.SessionContext;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class HomeMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String mensagemAcesso;

	public void inicializar(ComponentSystemEvent e) {
		if (SessionContext.getInstance().getAttribute("menuModel") == null && StringUtils.isBlank(mensagemAcesso)) {
			mensagemAcesso = "O usuário foi cadastrado, porém não possui acesso nas funcionalidades";
			FacesUtil.addWarningMessage(mensagemAcesso);
		}
	}

	public String getMensagemAcesso() {
		return mensagemAcesso;
	}

	public void setMensagemAcesso(String mensagemAcesso) {
		this.mensagemAcesso = mensagemAcesso;
	}

}
