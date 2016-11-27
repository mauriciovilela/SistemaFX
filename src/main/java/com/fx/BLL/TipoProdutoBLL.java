package com.fx.BLL;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.fx.model.TbTipoProduto;

public class TipoProdutoBLL implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TbTipoProduto porId(Integer id) {
		return manager.find(TbTipoProduto.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TbTipoProduto> listar() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbTipoProduto.class);
		return criteria.list();
	}
	
}
