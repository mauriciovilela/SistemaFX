package com.fx.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProdutoOUT implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String dsDescricao;
	private BigDecimal vlTabela;
	private String cdTipoProduto;

	public ProdutoOUT() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getVlTabela() {
		return vlTabela;
	}

	public void setVlTabela(BigDecimal vlTabela) {
		this.vlTabela = vlTabela;
	}

	public String getDsDescricao() {
		return dsDescricao;
	}

	public void setDsDescricao(String dsDescricao) {
		this.dsDescricao = dsDescricao;
	}

	public String getCdTipoProduto() {
		return cdTipoProduto;
	}

	public void setCdTipoProduto(String cdTipoProduto) {
		this.cdTipoProduto = cdTipoProduto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ProdutoOUT other = (ProdutoOUT) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}