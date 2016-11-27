package com.fx.BLL;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import com.fx.constants.Msgs;
import com.fx.dto.CheckIn;
import com.fx.dto.ContratoCarroOUT;
import com.fx.dto.FiltroCarroIN;
import com.fx.dto.RelCheckInOUT;
import com.fx.model.TbCarro;
import com.fx.model.TbContratoCarro;
import com.fx.model.TbEmpresa;
import com.fx.security.SessionContext;
import com.fx.service.NegocioException;
import com.fx.util.jpa.Transactional;

public class CarroBLL implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public TbCarro porId(Integer id) {
		return manager.find(TbCarro.class, id);
	}

	@SuppressWarnings("unchecked")
	public TbCarro porCodigo(String carro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbCarro.class);
		criteria.add(Restrictions.eq("id", carro));	
		// Filtro da Filial 
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial()));
		List<TbCarro> lista = criteria.list();
		if (lista.size() > 0) {
			return lista.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<TbCarro> listar() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbCarro.class);
		// Filtro da Filial 
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial()));
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<TbCarro> listarPorEmpresa(Integer idEmpresa) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbCarro.class);
		criteria.add(Restrictions.eq("tbEmpresa.id", idEmpresa));
		// Filtro da Filial 
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial()));	
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ContratoCarroOUT> listarCarros() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbCarro.class);
		criteria.createAlias("tbEmpresa", "E");
		criteria.createAlias("tbFilial", "F");
		// Filtro da Filial 
		criteria.add(Restrictions.eq("F.id", SessionContext.getInstance().getCdFilial()));
		//criteria.addOrder(Order.asc("id"));
		criteria.setProjection( Projections.projectionList()
			    .add( Projections.property("id").as("codigoCarro") )
			    .add( Projections.property("E.dsNome").as("dsEmpresa") )
			    .add( Projections.property("dsPlaca").as("placaCarro") ));
		criteria.setResultTransformer(new AliasToBeanResultTransformer(ContratoCarroOUT.class));		
		return criteria.list();
	}
	
	public List<ContratoCarroOUT> listarCarrosAlugados(String cdCarro) {
		return listarCarrosAlugados(cdCarro, new Date());
	}

	@SuppressWarnings("unchecked")
	public List<ContratoCarroOUT> listarCarrosAlugados(String cdCarro, Date dataInicio) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbContratoCarro.class, "CC");
		criteria.createAlias("CC.tbContrato", "CT");
		criteria.createAlias("CC.tbCarro", "CR");
		criteria.createAlias("CR.tbEmpresa", "E");
		criteria.createAlias("CR.tbLinha", "L");
		if (StringUtils.isNotBlank(cdCarro)) {
			criteria.add(Restrictions.eq("CR.id", cdCarro));			
		}
		criteria.add(Restrictions.le("CT.dtInicio", dataInicio)); 
		criteria.add(Restrictions.ge("CT.dtFim", dataInicio));
		// Filtro da Filial 
		criteria.add(Restrictions.eq("CT.tbFilial.id", SessionContext.getInstance().getCdFilial()));
		criteria.setProjection( Projections.projectionList()
			    .add( Projections.groupProperty("CR.id").as("codigoCarro") )
			    .add( Projections.groupProperty("E.dsNome").as("dsEmpresa") )
			    .add( Projections.groupProperty("L.dsNome").as("dsLinha") )
			    .add( Projections.groupProperty("CT.id").as("codigoContrato") )
			    .add( Projections.groupProperty("CT.dsCampanha").as("dsCampanha") )
			    .add( Projections.groupProperty("CT.dtInicio").as("dtInicio") )
			    .add( Projections.groupProperty("CT.dtFim").as("dtFim") ));
		criteria.setResultTransformer(new AliasToBeanResultTransformer(ContratoCarroOUT.class));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<RelCheckInOUT> relatorioCheckIN_Disponibilidade(TbEmpresa empresa, Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Session session = manager.unwrap(Session.class);
		StringBuilder strSQL = new StringBuilder();
		strSQL.append(" SELECT 						                                                      ");
		strSQL.append(" 	car.id AS carro,                                                              ");
		strSQL.append(" 	e.ds_nome AS empresa,                                                         ");
		strSQL.append(" 	(SELECT ct.ds_campanha                                                        ");
		strSQL.append(" 	 FROM tb_contrato ct                                                          ");
		strSQL.append(" 	 INNER JOIN tb_contrato_carro cc ON cc.id_contrato = ct.pk                    ");
		strSQL.append(" 	 WHERE cc.id_carro = car.pk AND                                               ");
		strSQL.append(" 	   (ct.dt_baixa_contrato is null) LIMIT 1) AS campanha,       ");
		strSQL.append(" 	(SELECT count(1)                                               				  ");
		strSQL.append(" 	 FROM tb_contrato ct                                                          ");
		strSQL.append(" 	 INNER JOIN tb_contrato_carro cc ON cc.id_contrato = ct.pk                    ");
		strSQL.append(" 	 INNER JOIN tb_produto p ON cc.id_produto = p.id                              ");
		strSQL.append(" 	 WHERE cc.id_carro = car.pk AND                                               ");
		strSQL.append(" 	   (ct.dt_baixa_contrato is null) AND p.id_tipo_produto = 1) AS externo, ");
		strSQL.append(" 	(SELECT count(1)                                                              ");
		strSQL.append(" 	 FROM tb_contrato ct                                                          ");
		strSQL.append(" 	 INNER JOIN tb_contrato_carro cc ON cc.id_contrato = ct.pk                    ");
		strSQL.append(" 	 INNER JOIN tb_produto p ON cc.id_produto = p.id                              ");
		strSQL.append(" 	 WHERE cc.id_carro = car.pk AND                                               ");
		strSQL.append(" 	   (ct.dt_baixa_contrato is null) AND p.id_tipo_produto = 2) AS interno  ");
		strSQL.append(" FROM                                                                              ");
		strSQL.append(" 	tb_carro car                                                                  ");
		strSQL.append(" 	INNER JOIN tb_empresa e ON car.id_empresa = e.id                              ");
		strSQL.append(" WHERE car.id_filial = {1}                                                         ");
		if (empresa != null) {
			strSQL.append(" AND e.id = {2}	    	                                                      ");			
		}
		strSQL.append(" GROUP BY                                                                          ");
		strSQL.append(" 	car.id, e.ds_nome		                                                      ");
		String executeSQL = strSQL.toString();
		executeSQL = executeSQL.replace("{0}", sdf.format(data));
		executeSQL = executeSQL.replace("{1}", SessionContext.getInstance().getCdFilial().toString());
		if (empresa != null) {
			executeSQL = executeSQL.replace("{2}", empresa.getId().toString());
		}
		SQLQuery query = session.createSQLQuery(executeSQL);
		query.setResultTransformer(new AliasToBeanResultTransformer(RelCheckInOUT.class));
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<RelCheckInOUT> relatorioCheckIN(CheckIn filtro) {
		Session session = manager.unwrap(Session.class);
		// Quando o filtro campanha nao for preenchido
		if (StringUtils.isBlank(filtro.getCampanha())) {
			StringBuilder strSQLComum = new StringBuilder();
			strSQLComum.append(" 	 FROM tb_contrato ct                                            ");
			strSQLComum.append(" 	 INNER JOIN tb_contrato_carro cc ON cc.id_contrato = ct.pk      ");
			strSQLComum.append(" 	 INNER JOIN tb_produto p ON cc.id_produto = p.id                ");
			strSQLComum.append(" 	 WHERE cc.id_carro = car.pk                                     ");
			strSQLComum.append(" 	   AND ct.id_filial = {F} AND car.id_filial = {F}               ");
			strSQLComum.append(" 	   AND (ct.dt_baixa_contrato is null)                           ");
			if (filtro.getLinha() != null) {
				strSQLComum.append("       AND car.id_linha = {L}                                    ");				
			}
			strSQLComum.append(" 	   AND p.id_tipo_produto = {TP} LIMIT 1           				");		
			
			StringBuilder strSQL = new StringBuilder();
			strSQL.append(" SELECT 						                                        	");
			strSQL.append(" 	car.id AS carro,                                                	");
			strSQL.append(" 	(SELECT ct.ds_campanha " + strSQLComum.toString() + ") AS campanha, ");
			strSQL.append(" 	(SELECT ct.dt_inicio " + strSQLComum.toString() + ") AS inicio, 	");
			strSQL.append(" 	(SELECT ct.dt_fim " + strSQLComum.toString() + ") AS fim     		");
			strSQL.append(" FROM                                                                	");
			strSQL.append(" 	tb_carro car                                                    	");
			strSQL.append(" 	INNER JOIN tb_empresa e ON car.id_empresa = e.id                	");		
			strSQL.append(" WHERE                                                               	");
			strSQL.append("     car.id_filial = {F} AND e.id = {E}                              	");
			if (filtro.getLinha() != null) {
				strSQL.append("     AND car.id_linha = {L}                                          ");				
			}
			strSQL.append(" GROUP BY                                                            	");
			strSQL.append(" 	car.id, e.ds_nome		                                        	");
			strSQL.append(" ORDER BY                                                            	");
			strSQL.append(" 	car.id            		                                        	");
			String executeSQL = strSQL.toString();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			executeSQL = executeSQL.replace("{DT}", sdf.format(data));
			executeSQL = executeSQL.replace("{TP}", filtro.getTipoProduto().toString());
			executeSQL = executeSQL.replace("{F}", SessionContext.getInstance().getCdFilial().toString());
			executeSQL = executeSQL.replace("{E}", filtro.getEmpresa().getId().toString());
			if (filtro.getLinha() != null) {
				executeSQL = executeSQL.replace("{L}", filtro.getLinha().toString());				
			}
			SQLQuery query = session.createSQLQuery(executeSQL);
			query.setResultTransformer(new AliasToBeanResultTransformer(RelCheckInOUT.class));
			return query.list();
		}
		else {
			Criteria criteria = session.createCriteria(TbContratoCarro.class, "CC");
			criteria.createAlias("CC.tbContrato", "CT");
			criteria.createAlias("CC.tbCarro", "CR");
			criteria.createAlias("CR.tbEmpresa", "E");
			criteria.createAlias("CR.tbLinha", "L");
			criteria.createAlias("CC.tbProduto", "P");
			if (StringUtils.isNotBlank(filtro.getCampanha())) {
				criteria.add(Restrictions.ilike("CT.dsCampanha", filtro.getCampanha(), MatchMode.ANYWHERE));
			}
			if (filtro.getLinha() != null) {
				criteria.add(Restrictions.eq("L.id", filtro.getLinha()));
			}
			criteria.add(Restrictions.isNull("CT.dtBaixaContrato")); 
			criteria.add(Restrictions.eq("P.tbTipoProduto.id", filtro.getTipoProduto()));
			criteria.add(Restrictions.eq("E.id", filtro.getEmpresa().getId()));
			// Filtro da Filial 
			criteria.add(Restrictions.eq("CT.tbFilial.id", SessionContext.getInstance().getCdFilial()));
			criteria.add(Restrictions.eq("CR.tbFilial.id", SessionContext.getInstance().getCdFilial()));
			criteria.addOrder(Order.asc("CR.id"));
			criteria.setProjection( Projections.projectionList()
				    .add( Projections.property("CR.id").as("carro") )
				    .add( Projections.property("CT.dsCampanha").as("campanha") )
				    .add( Projections.property("CT.dtInicio").as("inicio") )
				    .add( Projections.property("CT.dtFim").as("fim") )
				    .add( Projections.groupProperty("CR.id") )
				    .add( Projections.groupProperty("E.dsNome") ));
			criteria.setResultTransformer(new AliasToBeanResultTransformer(RelCheckInOUT.class));
			return criteria.list();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<TbCarro> filtrados(FiltroCarroIN filtro) {
		Criteria criteria = filtradosCriteria(filtro);
		criteria.addOrder(Order.asc("id"));
		criteria.setFirstResult(filtro.getFirst());
		criteria.setMaxResults(filtro.getPageSize());
		return criteria.list();
	}
	
	public Integer filtradosQTD(FiltroCarroIN filtro) {
		Criteria criteria = filtradosCriteria(filtro);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	private Criteria filtradosCriteria(FiltroCarroIN filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(TbCarro.class, "C");
		criteria.createAlias("C.tbEmpresa", "E");
		criteria.createAlias("C.tbLinha", "L");
		if (StringUtils.isNotEmpty(filtro.getCodigo())) {
			criteria.add(Restrictions.ilike("id", filtro.getCodigo(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotEmpty(filtro.getLinha())) {
			criteria.add(Restrictions.ilike("L.dsNome", filtro.getLinha(), MatchMode.ANYWHERE));
		}
		if (filtro.getEmpresa() != null) {
			criteria.add(Restrictions.eq("E.id", filtro.getEmpresa()));
		}
		// Filtro da Filial 
		criteria.add(Restrictions.eq("tbFilial.id", SessionContext.getInstance().getCdFilial()));	
		return criteria;
	}	

	public TbCarro guardar(TbCarro item) {
		return manager.merge(item);
	}
	
	@Transactional
	public void remover(TbCarro item) {
		try {
			item = porId(item.getPk());
			manager.remove(item);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException(Msgs.MSG_08);
		}
	}
}
