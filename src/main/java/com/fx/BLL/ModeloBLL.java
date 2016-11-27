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
import com.fx.model.TbModelo;
import com.fx.security.SessionContext;
import com.fx.service.NegocioException;
import com.fx.util.jpa.Transactional;

public class ModeloBLL implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TbModelo porId(Integer id) {
		return manager.find(TbModelo.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TbModelo> listar() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbModelo.class);
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial())); // Filtro da Filial
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<TbModelo> filtrados(FiltroIN filtro) {
		Criteria criteria = filtradosCriteria(filtro);
		criteria.addOrder(Order.asc("dsDescricao"));
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
		Criteria criteria = session.createCriteria(TbModelo.class);
		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			criteria.add(Restrictions.ilike("dsDescricao", filtro.getDescricao(), MatchMode.ANYWHERE));
		}
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial())); // Filtro da Filial
		return criteria;
	}	

	public TbModelo guardar(TbModelo item) {
		return manager.merge(item);
	}
	
	@Transactional
	public void remover(TbModelo item) {
		try {
			item = porId(item.getId());
			manager.remove(item);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(Msgs.MSG_08);
		}
	}
}
