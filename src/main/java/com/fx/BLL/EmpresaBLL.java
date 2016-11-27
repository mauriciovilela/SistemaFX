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
import com.fx.model.TbEmpresa;
import com.fx.security.SessionContext;
import com.fx.service.NegocioException;
import com.fx.util.jpa.Transactional;

public class EmpresaBLL implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TbEmpresa porId(Integer id) {
		return manager.find(TbEmpresa.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TbEmpresa> listar() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbEmpresa.class);
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial())); // Filtro da Filial
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<TbEmpresa> filtrados(FiltroIN filtro) {
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
		Criteria criteria = session.createCriteria(TbEmpresa.class);
		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			criteria.add(Restrictions.ilike("dsNome", filtro.getDescricao(), MatchMode.ANYWHERE));
		}
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial())); // Filtro da Filial
		return criteria;
	}	
	
	public TbEmpresa guardar(TbEmpresa item) {
		return manager.merge(item);
	}
	
	@Transactional
	public void remover(TbEmpresa item) {
		try {
			item = porId(item.getId());
			manager.remove(item);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(Msgs.MSG_08);
		}
	}
}
