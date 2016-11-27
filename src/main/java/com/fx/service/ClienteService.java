package com.fx.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import com.fx.BLL.ClienteBLL;
import com.fx.constants.Msgs;
import com.fx.model.TbCliente;
import com.fx.util.jpa.Transactional;

public class ClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteBLL bll;
	
	@Transactional
	public TbCliente salvar(TbCliente item) {
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
