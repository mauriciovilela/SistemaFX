package com.fx.BLL;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.hibernate.transform.Transformers;

import com.fx.constants.Msgs;
import com.fx.dto.FiltroIN;
import com.fx.dto.ProdutoOUT;
import com.fx.model.TbContratoProduto;
import com.fx.model.TbProduto;
import com.fx.security.SessionContext;
import com.fx.service.NegocioException;
import com.fx.util.jpa.Transactional;

public class ProdutoBLL implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TbProduto porId(Integer id) {
		return manager.find(TbProduto.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TbProduto> listar() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbProduto.class);
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial())); // Filtro da Filial
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<TbProduto> listarPorContrato(Integer cdContrato) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbContratoProduto.class, "I");
		criteria.createAlias("tbContrato", "C");
		criteria.createAlias("tbProduto", "P");
		criteria.add(Restrictions.eq("C.id", cdContrato));
		// Campos
		criteria.setProjection(Projections.projectionList()
	        .add( Projections.property("P.id").as("id") )
	        .add( Projections.property("P.dsDescricao").as("dsDescricao") )
		);
		criteria.setResultTransformer(Transformers.aliasToBean(ProdutoOUT.class));
		// Resultado
		List<ProdutoOUT> resultado = criteria.list(); 
		// Parse dos dados
		List<TbProduto> retorno = new ArrayList<TbProduto>();
		for (ProdutoOUT item : resultado) {
			retorno.add(new TbProduto(item.getId(), item.getDsDescricao()));
		}
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<TbProduto> filtrados(FiltroIN filtro) {
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
		Criteria criteria = session.createCriteria(TbProduto.class);
		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			criteria.add(Restrictions.ilike("dsDescricao", filtro.getDescricao(), MatchMode.ANYWHERE));
		}
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial())); // Filtro da Filial
		return criteria;
	}	

	
	public TbProduto guardar(TbProduto item) {
		return manager.merge(item);
	}
	
	@Transactional
	public void remover(TbProduto item) {
		try {
			item = porId(item.getId());
			manager.remove(item);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(Msgs.MSG_08);
		}
	}
}
