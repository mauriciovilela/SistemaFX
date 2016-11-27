package com.fx.BLL;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import com.fx.constants.Msgs;
import com.fx.dto.AcessosOUT;
import com.fx.model.TbFuncionalidadeUsuario;
import com.fx.service.NegocioException;
import com.fx.util.jpa.Transactional;

public class FuncionalidadeUsuarioBLL implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TbFuncionalidadeUsuario porId(Short id) {
		return manager.find(TbFuncionalidadeUsuario.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<AcessosOUT> listarFuncionalidadesUsuario(Integer idUsuario) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbFuncionalidadeUsuario.class);
		criteria.createAlias("tbFuncionalidade", "F");
		criteria.createAlias("tbUsuario", "U");
		criteria.createAlias("F.tbFuncionalidadePai", "M");
		criteria.addOrder(Order.asc("M.nrOrdem"));
		criteria.addOrder(Order.asc("F.nrOrdem"));
		criteria.add(Restrictions.eq("U.id", idUsuario));
		criteria.setProjection( Projections.projectionList()
		    .add( Projections.property("F.id").as("idFuncionalidade") )
		    .add( Projections.property("F.dsNome").as("dsFuncionalidade") )
		    .add( Projections.property("F.dsPagina").as("dsPagina") )
		    .add( Projections.property("F.dsPaginaEdicao").as("dsPaginaEdicao") )
		    .add( Projections.property("M.dsNome").as("dsModulo") )
		    .add( Projections.property("F.flVisivel").as("visivel") ));
		criteria.setResultTransformer(new AliasToBeanResultTransformer(AcessosOUT.class));			
		return criteria.list();
	}

	public TbFuncionalidadeUsuario guardar(TbFuncionalidadeUsuario item) {
		return manager.merge(item);
	}
	
	@Transactional
	public void removerPorUsuario(Integer idUsuario) {
		try {
			Session session = manager.unwrap(Session.class);
			String hql = "delete from TbFuncionalidadeUsuario where tbUsuario.id = :idUsuario";  
			Query qrDelete = session.createQuery(hql).setInteger("idUsuario", idUsuario);
			qrDelete.executeUpdate();  
		} catch (PersistenceException e) {
			throw new NegocioException(Msgs.MSG_08);
		}
	}
}
