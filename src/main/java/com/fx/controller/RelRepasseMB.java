package com.fx.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.BLL.ContratoBLL;
import com.fx.dto.MesesOUT;
import com.fx.dto.RelContratosOUT;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class RelRepasseMB implements Serializable {

	private static final long serialVersionUID = 1L;

	/* CONTRATO */
	@Inject
	private ContratoBLL contratoBLL;
	
	private List<MesesOUT> ultimosMeses;
	private Integer mes;
	private Integer ano;
	
	private List<RelContratosOUT> relatorio;
	private BigDecimal vlTotal;
	private BigDecimal vlFmt;
	private BigDecimal vlEmpresa;
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			mes = Calendar.getInstance().get(Calendar.MONTH);
			ano = Calendar.getInstance().get(Calendar.YEAR);
		}
	}
	
	public void relatorioRepasse() {
		relatorio = contratoBLL.relatorioRepasse(mes, ano);
		vlTotal = new BigDecimal(0);
		vlFmt = new BigDecimal(0);
		vlEmpresa = new BigDecimal(0);
		for (RelContratosOUT item : relatorio) {
			vlFmt = vlFmt.add(item.getVlFmt());
			vlEmpresa = vlEmpresa.add(item.getVlEmpresa());
			vlTotal = vlTotal.add(item.getVlEmpresa().add(item.getVlFmt()));
			item.setVlTotal(item.getVlEmpresa().add(item.getVlFmt()));
		}
	}

	public List<RelContratosOUT> getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(List<RelContratosOUT> relatorio) {
		this.relatorio = relatorio;
	}

	public BigDecimal getVlTotal() {
		return vlTotal;
	}

	public void setVlTotal(BigDecimal vlTotal) {
		this.vlTotal = vlTotal;
	}

	public List<MesesOUT> getUltimosMeses() {
		return ultimosMeses;
	}

	public void setUltimosMeses(List<MesesOUT> ultimosMeses) {
		this.ultimosMeses = ultimosMeses;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public BigDecimal getVlFmt() {
		return vlFmt;
	}

	public void setVlFmt(BigDecimal vlFmt) {
		this.vlFmt = vlFmt;
	}

	public BigDecimal getVlEmpresa() {
		return vlEmpresa;
	}

	public void setVlEmpresa(BigDecimal vlEmpresa) {
		this.vlEmpresa = vlEmpresa;
	}

}
