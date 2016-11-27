package com.fx.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.BLL.FuncionalidadeBLL;
import com.fx.BLL.FuncionalidadeUsuarioBLL;
import com.fx.BLL.UsuarioBLL;
import com.fx.constants.Constants;
import com.fx.constants.Msgs;
import com.fx.dto.AcessosOUT;
import com.fx.model.TbUsuario;
import com.fx.service.FuncionalidadeUsuarioService;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class FuncionalidadeUsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FuncionalidadeUsuarioBLL funcionalidadeUsuarioBLL;
	@Inject
	private FuncionalidadeBLL funcionalidadeBLL;
	
	private List<AcessosOUT> funcionalidades;
	
	@Inject
	private UsuarioBLL usuarioBLL;
	private List<TbUsuario> usuarios;
	private TbUsuario usuario;
	private TbUsuario usuarioCopia;
	
	@Inject
	private FuncionalidadeUsuarioService service;
		
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			usuarios = usuarioBLL.listar();
			limpar();
		}
	}
	
	private void pesquisarFuncionalidades() {
		funcionalidades = funcionalidadeBLL.listarModulosFuncionalidades();
	}
	
	public void pesquisarFuncionalidadesUsuario() {
		if (usuario != null) {
			List<AcessosOUT> funcionalidadesUsuario = funcionalidadeUsuarioBLL.listarFuncionalidadesUsuario(usuario.getId());
			for (AcessosOUT itemFunc : funcionalidades) {
				itemFunc.setSelecionado(false);
				for (AcessosOUT itemFuncUsu : funcionalidadesUsuario) {
					if (itemFunc.equals(itemFuncUsu)) {
						itemFunc.setSelecionado(true);
					}
				}
			}
		}
	}
	
	public void salvar() {
		service.salvar(usuario, funcionalidades);
		FacesUtil.addInfoMessage(Msgs.MSG_00);
		limpar();
	}
	
	public void salvarCopia() {
		service.salvar(usuarioCopia, funcionalidades);
		FacesUtil.addInfoMessage(Msgs.MSG_00);
		limpar();
	}
	
	private void limpar() {
		usuario = new TbUsuario();
		usuarioCopia = new TbUsuario();
		pesquisarFuncionalidades();
	}
	
	public List<TbUsuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<TbUsuario> usuarios) {
		this.usuarios = usuarios;
	}

	public TbUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(TbUsuario usuario) {
		this.usuario = usuario;
	}

	public List<AcessosOUT> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<AcessosOUT> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public TbUsuario getUsuarioCopia() {
		return usuarioCopia;
	}

	public void setUsuarioCopia(TbUsuario usuarioCopia) {
		this.usuarioCopia = usuarioCopia;
	}
	
}