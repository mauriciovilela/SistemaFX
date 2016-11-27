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
import com.fx.model.TbParametro;
import com.fx.security.SessionContext;

public class ParametroBLL implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TbParametro porId(Integer id) {
		return manager.find(TbParametro.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TbParametro> listar() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbParametro.class);
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial())); // Filtro da Filial
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<TbParametro> filtrados(FiltroIN filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbParametro.class);
		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			criteria.add(Restrictions.ilike("dsNome", filtro.getDescricao(), MatchMode.ANYWHERE));
		}
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial())); // Filtro da Filial
		criteria.addOrder(Order.asc("dsNome"));
		return criteria.list();
	}
	
	public TbParametro guardar(TbParametro item) {
		return manager.merge(item);
	}
	
}
