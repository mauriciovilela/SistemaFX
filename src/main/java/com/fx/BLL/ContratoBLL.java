package com.fx.BLL;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
import org.hibernate.transform.AliasToBeanResultTransformer;

import com.fx.constants.Msgs;
import com.fx.dto.FiltroIN;
import com.fx.dto.RelContratosOUT;
import com.fx.model.TbContrato;
import com.fx.security.SessionContext;
import com.fx.service.NegocioException;
import com.fx.util.Util;
import com.fx.util.jpa.Transactional;

public class ContratoBLL implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TbContrato porId(Integer id) {
		return manager.find(TbContrato.class, id);
	}

	@SuppressWarnings("unchecked")
	public TbContrato porCodigo(Integer idContrato) {
		idContrato = Util.fillIdContrato(idContrato);
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbContrato.class);
		criteria.add(Restrictions.eq("id", idContrato));	
		// Filtro da Filial 
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial()));
		List<TbContrato> lista = criteria.list();
		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<TbContrato> listar() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbContrato.class);
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial())); // Filtro da Filial	
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<RelContratosOUT> relatorioContratos(Integer mes, Integer ano) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbContrato.class, "C");
		//Criteria criteria = session.createCriteria(TbContratoCarro.class);
		//criteria.createAlias("tbContrato", "C");
		criteria.createAlias("C.tbCliente", "CL");
		// Mes e Ano
		criteria.add(Restrictions.sqlRestriction( "Month(DT_INICIO) = " + mes));
		criteria.add(Restrictions.sqlRestriction( "Year(DT_INICIO) = " + ano));
		// Filtro da Filial
		criteria.add(Restrictions.eq("C.tbFilial.id", SessionContext.getInstance().getCdFilial())); 
		criteria.setProjection( Projections.projectionList()
				.add( Projections.groupProperty("C.pk").as("pk") )
			    .add( Projections.groupProperty("C.id").as("idContrato") )
			    .add( Projections.groupProperty("CL.dsRazaoSocial").as("dsRazao") )
			    .add( Projections.groupProperty("CL.nrCnpj").as("nrCnpj") )
			    .add( Projections.groupProperty("C.vlTotal").as("vlTotal") )
			    .add( Projections.groupProperty("C.vlFmt").as("vlFmt") )
			    .add( Projections.groupProperty("C.vlEmpresa").as("vlEmpresa") ));
		criteria.setResultTransformer(new AliasToBeanResultTransformer(RelContratosOUT.class));		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<RelContratosOUT> relatorioRepasse(Integer mes, Integer ano) {
		Session session = manager.unwrap(Session.class);
//		Criteria criteria = session.createCriteria(TbContratoCarro.class);
//		criteria.createAlias("tbContrato", "C");
		Criteria criteria = session.createCriteria(TbContrato.class, "C");
		criteria.createAlias("C.tbCliente", "CL");
		// Mes e Ano
		criteria.add(Restrictions.sqlRestriction( "Month(DT_REPASSE) = " + mes));
		criteria.add(Restrictions.sqlRestriction( "Year(DT_REPASSE) = " + ano));
		// Filtro da Filial
		criteria.add(Restrictions.eq("C.tbFilial.id", SessionContext.getInstance().getCdFilial())); 
		criteria.setProjection( Projections.projectionList()
				.add( Projections.groupProperty("C.pk").as("pk") )
			    .add( Projections.groupProperty("C.id").as("idContrato") )
			    .add( Projections.groupProperty("CL.dsRazaoSocial").as("dsRazao") )
			    .add( Projections.groupProperty("CL.nrCnpj").as("nrCnpj") )
			    .add( Projections.groupProperty("C.vlTotal").as("vlTotal") )
			    .add( Projections.groupProperty("C.vlFmt").as("vlFmt") )
			    .add( Projections.groupProperty("C.vlEmpresa").as("vlEmpresa") ));
		criteria.setResultTransformer(new AliasToBeanResultTransformer(RelContratosOUT.class));		
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<TbContrato> filtrados(FiltroIN filtro) {
		Criteria criteria = filtradosCriteria(filtro);
		criteria.setFirstResult(filtro.getFirst());
		criteria.setMaxResults(filtro.getPageSize());
		criteria.addOrder(Order.desc("id"));
		return criteria.list();
	}
	
	public Integer filtradosQTD(FiltroIN filtro) {
		Criteria criteria = filtradosCriteria(filtro);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	private Criteria filtradosCriteria(FiltroIN filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbContrato.class);
		if (filtro.getCodigo() != null) {
			criteria.add(Restrictions.eq("id", filtro.getCodigo()));
		}
		else {
			if (StringUtils.isNotBlank(filtro.getDescricao())) {
				criteria.add(Restrictions.ilike("dsCampanha", filtro.getDescricao(), MatchMode.ANYWHERE));
			}			
		}
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial())); // Filtro da Filial
		return criteria;
	}
	
	@SuppressWarnings("unchecked")
	public List<TbContrato> filtradosPorCliente(Integer codCliente) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbContrato.class);
		criteria.add(Restrictions.eq("tbCliente.id", codCliente));
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial())); // Filtro da Filial	
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	public TbContrato guardar(TbContrato item) {
		return manager.merge(item);
	}
	
	@Transactional
	public void remover(TbContrato item) {
		try {
			item = porId(item.getId());
			manager.remove(item);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(Msgs.MSG_08);
		}
	}
	
	public Integer getContratoGerado() {
		
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbContrato.class);
		// Filtro da Filial 
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial()));
		criteria.setProjection(Projections.max("id"));
		
		Integer cdMax = (Integer) criteria.uniqueResult();
		
		if (cdMax == null) {
			Calendar hoje = Calendar.getInstance();
			hoje.setTime(new Date());
			Integer anoAtual = hoje.get(Calendar.YEAR);
			return Integer.parseInt(anoAtual.toString().concat("0001"));
		}
		else {
			return (cdMax + 1);
		}
		
	}

}
