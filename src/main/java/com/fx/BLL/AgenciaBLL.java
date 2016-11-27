package com.fx.BLL;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.fx.constants.Msgs;
import com.fx.dto.FiltroIN;
import com.fx.model.TbAgencia;
import com.fx.service.NegocioException;
import com.fx.util.jpa.Transactional;

public class AgenciaBLL implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TbAgencia porId(Integer id) {
		return manager.find(TbAgencia.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TbAgencia> listar() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbAgencia.class);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TbAgencia> filtrados(FiltroIN filtro) {
		Criteria criteria = filtradosCriteria(filtro);
		criteria.addOrder(Order.asc("dsNome"));
		criteria.setFirstResult(filtro.getFirst());
		criteria.setMaxResults(filtro.getPageSize());
		return criteria.list();
	}
	
	public Integer filtradosQTD(FiltroIN filtro) {
		Criteria criteria = filtradosCriteria(filtro);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	private Criteria filtradosCriteria(FiltroIN filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbAgencia.class);
		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			criteria.add(Restrictions.ilike("dsNome", filtro.getDescricao(), MatchMode.ANYWHERE));
		}
		return criteria;
	}	

	@Transactional
	public TbAgencia guardar(TbAgencia item) {
		return manager.merge(item);
	}
	
	@Transactional
	public void remover(TbAgencia item) {
		try {
			item = porId(item.getId());
			manager.remove(item);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(Msgs.MSG_08);
		}
	}
}
