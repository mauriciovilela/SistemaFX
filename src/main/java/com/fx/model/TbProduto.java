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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tb_produto" , uniqueConstraints=@UniqueConstraint(columnNames={"DS_DESCRICAO", "ID_FILIAL"}) )
public class TbProduto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@NotBlank
	@Column(name="DS_DESCRICAO", length=100)
	private String dsDescricao;

	@Column(name="VL_TABELA")
	@NotNull
	private BigDecimal vlTabela;

	@Column(name="VL_FMT")
	@NotNull
	private BigDecimal vlFmt;

	@Column(name="VL_EMPRESA")
	@NotNull
	private BigDecimal vlEmpresa;
	
	@ManyToOne
	@JoinColumn(name="ID_TIPO_PRODUTO")
	@NotNull	
	private TbTipoProduto tbTipoProduto;
	
	@ManyToOne
	@JoinColumn(name="ID_FILIAL")
	@NotNull
	private TbFilial tbFilial;
	
	public TbProduto() {
	}

	public TbProduto(Integer id, String dsDescricao) {
		this.id = id;
		this.dsDescricao = dsDescricao;
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

	public TbTipoProduto getTbTipoProduto() {
		return tbTipoProduto;
	}

	public void setTbTipoProduto(TbTipoProduto tbTipoProduto) {
		this.tbTipoProduto = tbTipoProduto;
	}

	public TbFilial getTbFilial() {
		return tbFilial;
	}

	public void setTbFilial(TbFilial tbFilial) {
		this.tbFilial = tbFilial;
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
		TbProduto other = (TbProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}