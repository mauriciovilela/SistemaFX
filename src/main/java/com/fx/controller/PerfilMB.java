package com.fx.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.fx.BLL.PerfilBLL;
import com.fx.constants.Constants;
import com.fx.constants.Msgs;
import com.fx.model.TbPerfil;
import com.fx.service.PerfilService;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PerfilMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PerfilService service;
	
	private TbPerfil perfil;
	
	/* MODELO */
	@Inject
	private PerfilBLL perfilBLL;
	@NotNull
	private List<TbPerfil> perfis;
	
	public PerfilMB() {
		limpar();
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			setPerfis(perfilBLL.listar());			
		}
	}
	
	private void limpar() {
		perfil = new TbPerfil();
	}
	
	public void salvar() {
		// Salva
		perfil = service.salvar(perfil);
		// Limpa a tela e objetos
		limpar();		
		// Mensagem de sucesso
		FacesUtil.addInfoMessage(Msgs.MSG_00);
	}

	public TbPerfil getPerfil() {
		return perfil;
	}
	
	public void setPerfil(TbPerfil Perfil) {
		perfil = Perfil;	
	}

	public boolean isEditando() {
		return perfil.getId() != null;
	}

	public List<TbPerfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<TbPerfil> perfis) {
		this.perfis = perfis;
	}

}
