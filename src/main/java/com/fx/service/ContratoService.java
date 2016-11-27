package com.fx.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;

import com.fx.BLL.ContratoBLL;
import com.fx.constants.Msgs;
import com.fx.model.TbContrato;
import com.fx.model.TbContratoCarro;
import com.fx.model.TbContratoProduto;
import com.fx.security.SessionContext;
import com.fx.util.jpa.Transactional;

public class ContratoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContratoBLL bll;

	@Inject
	private ContratoProdutoService contratoProdutService;

	@Inject
	private ContratoCarroService contratoCarroService;
	
	@Transactional
	public TbContrato salvar(TbContrato contrato) {
		return bll.guardar(contrato);
	}
	
	@Transactional
	public TbContrato salvarContratoProdutos(TbContrato contrato, List<TbContratoProduto> itens) {
		
		contrato.setTbFilial(SessionContext.getInstance().getFilial()); // Dados da Filial

		TbContrato novo = bll.guardar(contrato);
		
		if (CollectionUtils.isNotEmpty(itens)) {
			for (TbContratoProduto item : itens) {
				item.setTbContrato(novo);
				contratoProdutService.salvar(item);
			}			
		}
		else {
			throw new NegocioException(Msgs.MSG_03);
		}
		
		return novo;
	}

	@Transactional
	public TbContrato salvarContratoCarrosProdutos(List<TbContratoCarro> itens) {
		
		if (CollectionUtils.isNotEmpty(itens)) {
			for (TbContratoCarro item : itens) {
				item.setId( contratoCarroService.salvar(item).getId() );
			}			
		}
		else {
			throw new NegocioException(Msgs.MSG_03);
		}
		
		return null;
	}
}
