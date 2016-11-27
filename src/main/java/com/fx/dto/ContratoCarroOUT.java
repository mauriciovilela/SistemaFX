package com.fx.dto;

import java.io.Serializable;
import java.util.Date;

public class ContratoCarroOUT implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer codigoContrato;
	private String codigoCarro;
	private String dsCliente;
	private String dsAgencia;
	private String dsVendedor;
	private String dsCampanha;
	private Date dtContrato;
	private Date dtInicio;
	private Date dtFim;
	private String dsLinha;
	private String placaCarro;
	private String dsEmpresa;
	private String dsProduto;
	private String tipoProduto;

	public ContratoCarroOUT() {
	}

	public Integer getCodigoContrato() {
		return codigoContrato;
	}

	public void setCodigoContrato(Integer codigoContrato) {
		this.codigoContrato = codigoContrato;
	}

	public String getDsCliente() {
		return dsCliente;
	}

	public void setDsCliente(String dsCliente) {
		this.dsCliente = dsCliente;
	}

	public String getDsAgencia() {
		return dsAgencia;
	}

	public void setDsAgencia(String dsAgencia) {
		this.dsAgencia = dsAgencia;
	}

	public String getDsVendedor() {
		return dsVendedor;
	}

	public void setDsVendedor(String dsVendedor) {
		this.dsVendedor = dsVendedor;
	}

	public String getDsCampanha() {
		return dsCampanha;
	}

	public void setDsCampanha(String dsCampanha) {
		this.dsCampanha = dsCampanha;
	}

	public Date getDtContrato() {
		return dtContrato;
	}

	public void setDtContrato(Date dtContrato) {
		this.dtContrato = dtContrato;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public String getDsLinha() {
		return dsLinha;
	}

	public void setDsLinha(String dsLinha) {
		this.dsLinha = dsLinha;
	}

	public String getPlacaCarro() {
		return placaCarro;
	}

	public void setPlacaCarro(String placaCarro) {
		this.placaCarro = placaCarro;
	}

	public String getDsEmpresa() {
		return dsEmpresa;
	}

	public void setDsEmpresa(String dsEmpresa) {
		this.dsEmpresa = dsEmpresa;
	}

	public String getDsProduto() {
		return dsProduto;
	}

	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}

	public String getTipoProduto() {
		return tipoProduto;		
	}

	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public String getCodigoCarro() {
		return codigoCarro;
	}

	public void setCodigoCarro(String codigoCarro) {
		this.codigoCarro = codigoCarro;
	}
	
}