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
import com.fx.model.TbUsuario;
import com.fx.security.SessionContext;
import com.fx.service.NegocioException;
import com.fx.util.jpa.Transactional;

public class UsuarioBLL implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TbUsuario porId(Integer id) {
		return manager.find(TbUsuario.class, id);
	}

	public TbUsuario guardar(TbUsuario item) {
		return manager.merge(item);
	}

	@Transactional
	public void remover(TbUsuario item) {
		try {
			item = porId(item.getId());
			manager.remove(item);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(Msgs.MSG_08);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TbUsuario> listar() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbUsuario.class);
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial())); // Filtro da Filial
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TbUsuario> filtrados(FiltroIN filtro) {
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
		Criteria criteria = session.createCriteria(TbUsuario.class);
		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			criteria.add(Restrictions.ilike("dsNome", filtro.getDescricao(), MatchMode.ANYWHERE));
		}
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial())); // Filtro da Filial
		return criteria;
	}	

	public TbUsuario porLogin(String login, String senha) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbUsuario.class);
		criteria.add(Restrictions.eq("dsUsuario", login));
		criteria.add(Restrictions.eq("dsSenha", senha));
		//criteria.add(Restrictions.eq("tbFilial.id", tbFilial.getId()));
		if (!criteria.list().isEmpty()) {
			return (TbUsuario) criteria.list().get(0);
		};
		return null;
	}	
}
