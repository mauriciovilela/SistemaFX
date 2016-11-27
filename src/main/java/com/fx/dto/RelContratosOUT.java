package com.fx.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class RelContratosOUT implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer pk;
	private Integer idContrato;
	private String dsRazao;
	private String nrCnpj;
	private BigDecimal vlProducao;
	private BigDecimal vlFmt;
	private BigDecimal vlEmpresa;
	private BigDecimal vlTotal;

	public RelContratosOUT() {
		
	}

	public Integer getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}

	public String getDsRazao() {
		return dsRazao;
	}

	public void setDsRazao(String dsRazao) {
		this.dsRazao = dsRazao;
	}

	public String getNrCnpj() {
		return nrCnpj;
	}

	public void setNrCnpj(String nrCnpj) {
		this.nrCnpj = nrCnpj;
	}

	public BigDecimal getVlProducao() {
		return vlProducao;
	}

	public void setVlProducao(BigDecimal vlProducao) {
		this.vlProducao = vlProducao;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idContrato == null) ? 0 : idContrato.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelContratosOUT other = (RelContratosOUT) obj;
		if (idContrato == null) {
			if (other.idContrato != null)
				return false;
		} else if (!idContrato.equals(other.idContrato))
			return false;
		return true;
	}

	public BigDecimal getVlTotal() {
		return vlTotal;
	}

	public void setVlTotal(BigDecimal vlTotal) {
		this.vlTotal = vlTotal;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

}