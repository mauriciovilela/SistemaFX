package com.fx.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import com.fx.BLL.ParametroBLL;
import com.fx.constants.Msgs;
import com.fx.model.TbParametro;
import com.fx.util.jpa.Transactional;

public class ParametroService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ParametroBLL bll;
	
	@Transactional
	public TbParametro salvar(TbParametro item) {	
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
