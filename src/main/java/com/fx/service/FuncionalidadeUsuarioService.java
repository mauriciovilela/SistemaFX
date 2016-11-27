package com.fx.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.fx.BLL.FuncionalidadeUsuarioBLL;
import com.fx.dto.AcessosOUT;
import com.fx.model.TbFuncionalidade;
import com.fx.model.TbFuncionalidadeUsuario;
import com.fx.model.TbUsuario;
import com.fx.util.jpa.Transactional;

public class FuncionalidadeUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FuncionalidadeUsuarioBLL bll;
	
	@Transactional
	public TbFuncionalidadeUsuario salvar(TbUsuario usuario, List<AcessosOUT> funcionalidades) {
		
		// Remove todas as funcionalidades do Usuario
		bll.removerPorUsuario(usuario.getId());
		
		TbFuncionalidadeUsuario novo = null;
		
		for (AcessosOUT item : funcionalidades) {
			if (item.isSelecionado()) {
				novo = new TbFuncionalidadeUsuario();
				novo.setTbUsuario(usuario);
				novo.setTbFuncionalidade(new TbFuncionalidade(item.getIdFuncionalidade()));
				bll.guardar(novo);							
			}
		}
		
		return null;
	}
	
}
