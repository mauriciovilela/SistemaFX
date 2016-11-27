package com.fx.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import com.fx.BLL.PerfilBLL;
import com.fx.constants.Msgs;
import com.fx.model.TbPerfil;
import com.fx.util.jpa.Transactional;

public class PerfilService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PerfilBLL bll;
	
	@Transactional
	public TbPerfil salvar(TbPerfil item) {	
		try {
			return bll.guardar(item);			
		}
		catch (Exception ex) {
			if (ex.getCause() instanceof ConstraintViolationException) {
				throw new NegocioException(Msgs.MSG_07);
			}
			return null;
		}
	}
	
}
