package com.fx.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.hibernate.exception.ConstraintViolationException;

import com.fx.BLL.VendedorBLL;
import com.fx.constants.Msgs;
import com.fx.model.TbVendedor;
import com.fx.security.SessionContext;
import com.fx.util.jpa.Transactional;

public class VendedorService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private VendedorBLL bll;
	
	@Transactional
	public TbVendedor salvar(TbVendedor item) {	
		item.setTbFilial(SessionContext.getInstance().getFilial()); // Dados da Filial
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
