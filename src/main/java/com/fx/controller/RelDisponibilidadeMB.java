package com.fx.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.fx.BLL.CarroBLL;
import com.fx.BLL.EmpresaBLL;
import com.fx.dto.RelCheckInOUT;
import com.fx.model.TbEmpresa;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class RelDisponibilidadeMB implements Serializable {

	private static final long serialVersionUID = 1L;

	/* EMPRESA */
	@Inject
	private EmpresaBLL empresaBLL;
	@NotNull
	private List<TbEmpresa> empresas;
	
	private TbEmpresa empresa;
	
	@NotNull
	private Date data;
	private String dataFormatada;
	
	/* CARROS */
	@Inject
	private CarroBLL carroBLL;
	private List<RelCheckInOUT> relatorio;
	
	boolean encontrou;
	
	public RelDisponibilidadeMB() {
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			empresas = empresaBLL.listar();
		}
	}
	
	public void pesquisar() {
		relatorio = carroBLL.relatorioCheckIN_Disponibilidade(empresa, data);
		formataData();
	}
	
	private void formataData() {
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy"); 
		dataFormatada = dt.format(data); 
	}
	
	public List<TbEmpresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<TbEmpresa> empresas) {
		this.empresas = empresas;
	}

	public TbEmpresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(TbEmpresa empresa) {
		this.empresa = empresa;
	}

	public List<RelCheckInOUT> getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(List<RelCheckInOUT> relatorio) {
		this.relatorio = relatorio;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDataFormatada() {
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

}
