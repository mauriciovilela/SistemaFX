package com.fx.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import com.fx.BLL.ContratoCarroBLL;
import com.fx.constants.Msgs;
import com.fx.model.TbContratoCarro;
import com.fx.util.jpa.Transactional;

public class ContratoCarroService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContratoCarroBLL bll;
	
	@Transactional
	public TbContratoCarro salvar(TbContratoCarro item) {
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
