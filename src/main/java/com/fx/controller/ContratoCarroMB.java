package com.fx.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.primefaces.context.RequestContext;

import com.fx.BLL.CarroBLL;
import com.fx.BLL.ContratoBLL;
import com.fx.BLL.ContratoCarroBLL;
import com.fx.BLL.ProdutoBLL;
import com.fx.constants.Msgs;
import com.fx.dto.ContratoCarroOUT;
import com.fx.model.TbCarro;
import com.fx.model.TbContrato;
import com.fx.model.TbContratoCarro;
import com.fx.model.TbProduto;
import com.fx.security.SessionContext;
import com.fx.service.ContratoService;
import com.fx.util.Util;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ContratoCarroMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContratoService service;
	
	private TbContrato contrato;
	private TbContratoCarro contratoCarro;
	private List<TbContratoCarro> contratoCarros;
	
	/* PRODUTO */
	@Inject
	private ProdutoBLL produtoBLL;
	@NotNull
	private List<TbProduto> produtos;
	
	/* CARROS */
	@Inject
	private CarroBLL carroBLL;
	@NotNull
	private List<TbCarro> carros;

	@Inject
	private ContratoBLL contratoBLL;

	@Inject
	private ContratoCarroBLL contratoCarroBLL;

	@NotNull
	private Integer codigoContrato;
	
	private List<ContratoCarroOUT> alugados;
	
	private TbContratoCarro selecionado;
	
	public ContratoCarroMB() {
		limpar();
	}

	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			contratoCarro = new TbContratoCarro();
			contratoCarros = new ArrayList<TbContratoCarro>();
			produtos = produtoBLL.listar();
		}
	}
	
	public void pesquisar() {
		codigoContrato = Util.fillIdContrato(codigoContrato);
		contrato = contratoBLL.porCodigo(codigoContrato);
		if (contrato == null) {
			FacesUtil.addWarningMessage(Msgs.MSG_04);
			setCodigoContrato(null);
		}
		else {
			produtos = produtoBLL.listarPorContrato(codigoContrato);
			//Lista carros por contrato
			contratoCarros = contratoCarroBLL.listarPorContrato(codigoContrato);
			//carros = carroBLL.listarCarrosDisponiveis();
			carros = carroBLL.listar();
		}
	}

	private void limpar() {
		contrato = new TbContrato();
	}
	
	public String limparItem() {
		contratoCarro = new TbContratoCarro();
        return null;
    }
	
	public void verificaAdicionarItem() {
		// Verifica se o carro está alugado
		if (carroEstaAlugado()) {
			RequestContext.getCurrentInstance().execute("PF('confirmValidacao').show();");
		}
		else {
			adicionarItem();
		}
	}
	
	public void adicionarItem() {
		// Verifica se o item já foi adicionado
		if (!itemExistente()) {
			// Verifica se há tipos de produtos iguais para o mesmo carro
			if ( tipoProdutoDiferenteMesmoCarro() ) {
				contratoCarro.setTbContrato(contrato);
		    	contratoCarros.add(contratoCarro);
		    	contratoCarro = new TbContratoCarro();		
		    	salvarContratoCarro();
			}			
		}
    }
	
	/*
	 * Verifica se o item já foi adicionado
	 */
	private boolean itemExistente() {
		for (TbContratoCarro item : contratoCarros) {
			if (item.getTbProduto().equals(contratoCarro.getTbProduto()) && 
				item.getTbCarro().equals(contratoCarro.getTbCarro())) {
				// Mensagem 
				FacesUtil.addWarningMessage(Msgs.MSG_01);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Verifica se há tipos de produtos iguais para o mesmo carro
	 */
	private boolean tipoProdutoDiferenteMesmoCarro() {
		for (TbContratoCarro item : contratoCarros) {
			if (item.getTbCarro().equals(contratoCarro.getTbCarro())) {
				if (item.getTbProduto().getTbTipoProduto().getId().equals(contratoCarro.getTbProduto().getTbTipoProduto().getId())) {
					// Mensagem 
					FacesUtil.addWarningMessage(String.format(Msgs.MSG_02, contratoCarro.getTbProduto().getTbTipoProduto().getDsNome()));
					return false;					
				}
			}
		}
		return true;
	}

	/*
	 * Verifica se o carro está alugado
	 */
	private boolean carroEstaAlugado() {
		alugados = carroBLL.listarCarrosAlugados(contratoCarro.getTbCarro().getId(), contrato.getDtInicio());
		return alugados.size() > 0;
	}
	
	private void salvarContratoCarro() {
		// Salva
		service.salvarContratoCarrosProdutos(contratoCarros);
		// Mensagem de sucesso
		FacesUtil.addInfoMessage(Msgs.MSG_00);
	}
	
	public void removerItem() {
		contratoCarros.remove(selecionado);
		if (selecionado.getId() != null) {
			contratoCarroBLL.remover(selecionado);			
		}
	}
	
	public void modalContratoProdutos() {
		SessionContext.getInstance().setCdContrato(codigoContrato);
		RequestContext.getCurrentInstance().openDialog("/restrita/modal/ContratoProdutoMD"); 
	}

	public TbContrato getContrato() {
		return contrato;
	}
	
	public void setContrato(TbContrato Contrato) {
		contrato = Contrato;	
	}

	public boolean isEditando() {
		return contrato.getId() != null;
	}

	public List<TbProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<TbProduto> produtos) {
		this.produtos = produtos;
	}
	
	public Integer getCodigoContrato() {
		return codigoContrato;
	}

	public void setCodigoContrato(Integer codigoContrato) {
		this.codigoContrato = codigoContrato;
	}

	public TbContratoCarro getContratoCarro() {
		return contratoCarro;
	}

	public void setContratoCarro(TbContratoCarro contratoCarro) {
		this.contratoCarro = contratoCarro;
	}

	public List<TbCarro> getCarros() {
		return carros;
	}

	public void setCarros(List<TbCarro> carros) {
		this.carros = carros;
	}

	public List<TbContratoCarro> getContratoCarros() {
		return contratoCarros;
	}

	public void setItens(List<TbContratoCarro> itens) {
		this.contratoCarros = itens;
	}

	public List<ContratoCarroOUT> getAlugados() {
		return alugados;
	}

	public void setAlugados(List<ContratoCarroOUT> alugados) {
		this.alugados = alugados;
	}

	public TbContratoCarro getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbContratoCarro selecionado) {
		this.selecionado = selecionado;
	}


}
