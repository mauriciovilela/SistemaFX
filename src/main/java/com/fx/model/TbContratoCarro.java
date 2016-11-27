package com.fx.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_contrato_carro" , uniqueConstraints=@UniqueConstraint(columnNames={"ID_CARRO", "ID_CONTRATO", "ID_PRODUTO"}) )
public class TbContratoCarro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@ManyToOne
	@JoinColumn(name="ID_CARRO")
	private TbCarro tbCarro;

	@NotNull
	@ManyToOne
	@JoinColumn(name="ID_CONTRATO")
	private TbContrato tbContrato;

	@NotNull
	@ManyToOne
	@JoinColumn(name="ID_PRODUTO")
	private TbProduto tbProduto;

	public TbContratoCarro() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TbCarro getTbCarro() {
		return tbCarro;
	}

	public void setTbCarro(TbCarro tbCarro) {
		this.tbCarro = tbCarro;
	}

	public TbContrato getTbContrato() {
		return tbContrato;
	}

	public void setTbContrato(TbContrato tbContrato) {
		this.tbContrato = tbContrato;
	}

	public TbProduto getTbProduto() {
		return tbProduto;
	}

	public void setTbProduto(TbProduto tbProduto) {
		this.tbProduto = tbProduto;
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
		TbContratoCarro other = (TbContratoCarro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}