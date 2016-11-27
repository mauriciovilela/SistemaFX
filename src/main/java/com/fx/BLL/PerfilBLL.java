package com.fx.BLL;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.fx.dto.FiltroIN;
import com.fx.model.TbPerfil;

public class PerfilBLL implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TbPerfil porId(Integer id) {
		return manager.find(TbPerfil.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TbPerfil> listar() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbPerfil.class);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<TbPerfil> filtrados(FiltroIN filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbPerfil.class);
		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			criteria.add(Restrictions.ilike("dsNome", filtro.getDescricao(), MatchMode.ANYWHERE));
		}
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}
	
	public TbPerfil guardar(TbPerfil item) {
		return manager.merge(item);
	}
}
