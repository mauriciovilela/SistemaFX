package com.fx.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_contrato_produto" , uniqueConstraints=@UniqueConstraint(columnNames={"ID_PRODUTO", "ID_CONTRATO"}) )
public class TbContratoProduto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	@Column(name="NR_QUANTIDADE")
	@Min(value=1, message=" deve ser maior que zero")
	private Short nrQuantidade;
	
	@NotNull
	@Column(name="NR_MESES")
	@Min(value=1, message=" deve ser maior que zero")
	private Short nrMeses;

	@Column(name="VL_UNITARIO")
	@NotNull
	private BigDecimal vlUnitario;

	@Column(name="VL_TOTAL")
	@NotNull
	private BigDecimal vlTotal;

	@ManyToOne
	@JoinColumn(name="ID_PRODUTO")
	@NotNull
	private TbProduto tbProduto;

	@ManyToOne
	@JoinColumn(name="ID_CONTRATO")
	@NotNull
	private TbContrato tbContrato;

	public TbContratoProduto() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getNrQuantidade() {
		return nrQuantidade;
	}

	public void setNrQuantidade(Short nrQuantidade) {
		this.nrQuantidade = nrQuantidade;
	}

	public Short getNrMeses() {
		return nrMeses;
	}

	public void setNrMeses(Short nrMeses) {
		this.nrMeses = nrMeses;
	}

	public BigDecimal getVlUnitario() {
		return vlUnitario;
	}

	public void setVlUnitario(BigDecimal vlUnitario) {
		this.vlUnitario = vlUnitario;
	}

	public BigDecimal getVlTotal() {
		return vlTotal;
	}

	public void setVlTotal(BigDecimal vlTotal) {
		this.vlTotal = vlTotal;
	}

	public TbProduto getTbProduto() {
		return tbProduto;
	}

	public void setTbProduto(TbProduto tbProduto) {
		this.tbProduto = tbProduto;
	}

	public TbContrato getTbContrato() {
		return tbContrato;
	}

	public void setTbContrato(TbContrato tbContrato) {
		this.tbContrato = tbContrato;
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
		TbContratoProduto other = (TbContratoProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}