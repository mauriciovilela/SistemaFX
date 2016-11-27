package com.fx.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.BLL.ContratoProdutoBLL;
import com.fx.BLL.ParametroBLL;
import com.fx.constants.Parametro;
import com.fx.model.TbContrato;
import com.fx.model.TbContratoProduto;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ContratoGeracaoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private TbContrato contrato;
	
	/* CONTRATO CARRO */
	@Inject
	private ContratoProdutoBLL contratoCarroBLL;
	
	/* PARAMETRO */
	@Inject
	private ParametroBLL parametroBLL;
	
	private List<TbContratoProduto> contratoProduto;
	private double totalMesesLocacao;
	private BigDecimal vlTotalRepasse;
	
	private String termosContrato;

	public void initGeracao(ComponentSystemEvent event) throws IOException {
		// Busca os produtos do contrato
		contratoProduto = contratoCarroBLL.listarPorContrato(contrato.getId());
		// Parâmetro 1 - Termos contrato
		termosContrato = parametroBLL.porId(Parametro.TermosContrato.getValue()).getDsValor();
	}

	public void initGeracaoIC(ComponentSystemEvent event) {
		// Busca os produtos do contrato
		contratoProduto = contratoCarroBLL.listarPorContrato(contrato.getId());
	}

	public void initGeracaoPMU(ComponentSystemEvent event) {
		// Busca os produtos do contrato
		contratoProduto = contratoCarroBLL.listarPorContrato(contrato.getId(), true);
		// Parâmetro 2 - Termos contrato PMU
		termosContrato = parametroBLL.porId(Parametro.TermosContratoPIPMU.getValue()).getDsValor();
	}
	
	private void limpar() {
		contratoProduto = new ArrayList<TbContratoProduto>();
	}
	
	private void carregarTempoMesesValorRepasse() {
		if (contrato != null) {			
			Date dtInicio = contrato.getDtInicio();
			Date dtFim = contrato.getDtFim();
	        double MES_EM_MILISEGUNDOS = 30.0 * 24.0 * 60.0 * 60.0 * 1000.0;     
	        totalMesesLocacao = (double) ( (dtFim.getTime() - dtInicio.getTime() ) / MES_EM_MILISEGUNDOS);
	        totalMesesLocacao = Math.round(totalMesesLocacao);
	        //
	        vlTotalRepasse = contrato.getVlFmt().add(contrato.getVlEmpresa());
		}
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			limpar();
		}
	}

	public TbContrato getContrato() {
		carregarTempoMesesValorRepasse();
		return contrato;
	}

	public void setContrato(TbContrato contrato) {
		this.contrato = contrato;
	}

	public List<TbContratoProduto> getContratoProduto() {
		return contratoProduto;
	}

	public void setContratoProduto(List<TbContratoProduto> lstContratoProduto) {
		this.contratoProduto = lstContratoProduto;
	}

	public String getTermosContrato() {
		return termosContrato;
	}

	public void setTermosContrato(String termosContrato) {
		this.termosContrato = termosContrato;
	}

	public double getTotalMesesLocacao() {
		return totalMesesLocacao;
	}

	public void setTotalMesesLocacao(double totalMesesLocacao) {
		this.totalMesesLocacao = totalMesesLocacao;
	}

	public BigDecimal getVlTotalRepasse() {
		return vlTotalRepasse;
	}

	public void setVlTotalRepasse(BigDecimal vlTotalRepasse) {
		this.vlTotalRepasse = vlTotalRepasse;
	}

}
