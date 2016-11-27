package com.fx.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class RelCheckInOUT implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String carro;
	private String campanha;
	private String empresa;
	private Date inicio;
	private Date fim;
	private BigInteger interno;
	private BigInteger externo;

	public RelCheckInOUT() {
	}

	public String getCarro() {
		return carro;
	}

	public void setCarro(String carro) {
		this.carro = carro;
	}

	public String getCampanha() {
		return campanha;
	}

	public void setCampanha(String campanha) {
		this.campanha = campanha;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public BigInteger getInterno() {
		return interno;
	}

	public void setInterno(BigInteger interno) {
		this.interno = interno;
	}

	public BigInteger getExterno() {
		return externo;
	}

	public void setExterno(BigInteger externo) {
		this.externo = externo;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}
	
}