package com.fx.dto;

import java.io.Serializable;

import com.fx.model.TbEmpresa;

public class CheckIn implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private TbEmpresa empresa;
	private Integer tipoProduto;
	private String campanha;
	private Integer linha;

	public CheckIn() {
	}

	public TbEmpresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(TbEmpresa empresa) {
		this.empresa = empresa;
	}

	public Integer getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(Integer tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public String getCampanha() {
		return campanha;
	}

	public void setCampanha(String campanha) {
		this.campanha = campanha;
	}

	public Integer getLinha() {
		return linha;
	}

	public void setLinha(Integer linha) {
		this.linha = linha;
	}

}