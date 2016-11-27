package com.fx.BLL;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.fx.model.TbContratoProduto;
import com.fx.security.SessionContext;

public class ContratoProdutoBLL implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TbContratoProduto porId(Integer id) {
		return manager.find(TbContratoProduto.class, id);
	}
	
	public List<TbContratoProduto> listarPorContrato(Integer cdContrato) {
		return listarPorContrato(cdContrato, false);
	}

	@SuppressWarnings("unchecked")
	public List<TbContratoProduto> listarPorContrato(Integer cdContrato, boolean excetoOutrosProdutos) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbContratoProduto.class);
		criteria.createAlias("tbContrato", "C");
		criteria.createAlias("tbProduto", "P");
		criteria.createAlias("P.tbTipoProduto", "TP");
		criteria.createAlias("C.tbFilial", "F");
		criteria.add(Restrictions.eq("C.id", cdContrato));
		criteria.add(Restrictions.eq("F.id", SessionContext.getInstance().getCdFilial()));
		if (excetoOutrosProdutos) {
			criteria.add(Restrictions.ne("TP.id", 3));	
		}
		return criteria.list();
	}

	public TbContratoProduto guardar(TbContratoProduto item) {
		return manager.merge(item);
	}
	
}
