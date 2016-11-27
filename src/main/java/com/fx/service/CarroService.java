package com.fx.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import com.fx.BLL.CarroBLL;
import com.fx.constants.Msgs;
import com.fx.model.TbCarro;
import com.fx.security.SessionContext;
import com.fx.util.jpa.Transactional;

public class CarroService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CarroBLL bll;
	
	@Transactional
	public TbCarro salvar(TbCarro item) {
		try {
			item.setTbFilial(SessionContext.getInstance().getFilial()); // Dados da Filial
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
