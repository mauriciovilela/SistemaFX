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
import com.fx.model.TbCliente;
import com.fx.service.NegocioException;
import com.fx.util.jpa.Transactional;

public class ClienteBLL implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TbCliente porId(Integer id) {
		return manager.find(TbCliente.class, id);
	}

	public TbCliente guardar(TbCliente item) {
		return manager.merge(item);
	}
	
	@Transactional
	public void remover(TbCliente item) {
		try {
			item = porId(item.getId());
			manager.remove(item);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(Msgs.MSG_08);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TbCliente> filtrados(FiltroIN filtro) {
		Criteria criteria = filtradosCriteria(filtro);
		criteria.addOrder(Order.asc("dsNomeFantasia"));
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
		Criteria criteria = session.createCriteria(TbCliente.class);
		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			criteria.add( Restrictions.or(
				Restrictions.ilike("dsNomeFantasia", filtro.getDescricao(), MatchMode.ANYWHERE),
				Restrictions.ilike("dsRazaoSocial", filtro.getDescricao(), MatchMode.ANYWHERE)
			));
		}
		if (StringUtils.isNotBlank(filtro.getOutros())) {
			criteria.add(Restrictions.ilike("nrCnpj", filtro.getOutros(), MatchMode.ANYWHERE));			
		}
		return criteria;
	}	

	@SuppressWarnings("unchecked")
	public List<TbCliente> porNome(String nome) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbCliente.class);
		criteria.add(Restrictions.ilike("dsNomeFantasia", nome, MatchMode.ANYWHERE));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<TbCliente> listar() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbCliente.class);
		criteria.addOrder(Order.asc("dsNomeFantasia"));
		return criteria.list();
	}
}
