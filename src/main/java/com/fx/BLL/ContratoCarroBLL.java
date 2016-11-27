package com.fx.BLL;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fx.constants.Msgs;
import com.fx.model.TbContratoCarro;
import com.fx.security.SessionContext;
import com.fx.service.NegocioException;
import com.fx.util.jpa.Transactional;

public class ContratoCarroBLL implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TbContratoCarro porId(Integer id) {
		return manager.find(TbContratoCarro.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TbContratoCarro> listarPorContrato(Integer cdContrato) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbContratoCarro.class);
		criteria.createAlias("tbContrato", "C");
		criteria.createAlias("C.tbFilial", "F");
		criteria.add(Restrictions.eq("C.id", cdContrato));
		criteria.add(Restrictions.eq("F.id", SessionContext.getInstance().getCdFilial()));
		return criteria.list();
	}
	
	public TbContratoCarro guardar(TbContratoCarro item) {
		return manager.merge(item);
	}
	
	@Transactional
	public void remover(TbContratoCarro item) {
		try {
			item = porId(item.getId());
			manager.remove(item);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(Msgs.MSG_08);
		}
	}
	
}
