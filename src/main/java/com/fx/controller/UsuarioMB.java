package com.fx.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.fx.BLL.ClienteBLL;
import com.fx.BLL.FilialBLL;
import com.fx.BLL.PerfilBLL;
import com.fx.constants.Constants;
import com.fx.constants.Msgs;
import com.fx.model.TbCliente;
import com.fx.model.TbFilial;
import com.fx.model.TbPerfil;
import com.fx.model.TbUsuario;
import com.fx.service.UsuarioService;
import com.fx.util.Util;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService service;
	
	private TbUsuario usuario;
	
	/* MODELO */
	@Inject
	private PerfilBLL perfilBLL;
	@NotNull
	private List<TbPerfil> perfis;
	
	/* CLIENTE */
	@Inject
	private ClienteBLL clienteBLL;
	@NotNull
	private List<TbCliente> clientes;
	
	/* FILIAL */
	@Inject
	private FilialBLL filialBLL;
	@NotNull
	private List<TbFilial> filiais;

	public UsuarioMB() {
		limpar();
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			setFiliais(filialBLL.listar());	
			setPerfis(perfilBLL.listar());			
			setClientes(clienteBLL.listar());
		}
	}
	
	private void limpar() {
		usuario = new TbUsuario();
	}
	
	public void salvar() {
		if (!isEditando()) {
			//Senha MD5
			usuario.setDsSenha(Util.md5Java(usuario.getDsSenha()));			
		}
		// Salva
		usuario = service.salvar(usuario);
		// Limpa a tela e objetos
		limpar();		
		// Mensagem de sucesso
		FacesUtil.addInfoMessage(Msgs.MSG_00);
	}
	
	public void atualizaCliente() {
		//do nothing
	}

	public TbUsuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(TbUsuario Usuario) {
		usuario = Usuario;	
	}

	public boolean isEditando() {
		return usuario.getId() != null;
	}

	public List<TbPerfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<TbPerfil> perfis) {
		this.perfis = perfis;
	}

	public List<TbCliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<TbCliente> clientes) {
		this.clientes = clientes;
	}

	public List<TbFilial> getFiliais() {
		return filiais;
	}

	public void setFiliais(List<TbFilial> filiais) {
		this.filiais = filiais;
	}

}
