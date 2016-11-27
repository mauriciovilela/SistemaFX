package com.fx.model;

import java.io.Serializable;

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
@Table(name="tb_vendedor" , uniqueConstraints=@UniqueConstraint(columnNames={"DS_NOME", "ID_FILIAL"}) )
public class TbVendedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	@NotBlank
	@Column(name="DS_NOME", length=100)
	private String dsNome;

	@Column(name="DS_FONE", length=15)
	private String dsFone;

	@Column(name="VL_COMISSAO")
	@NotNull
	private Short vlComissao;

	@Column(name="DS_OBS", length=200)
	private String dsObservacao;

	@ManyToOne
	@JoinColumn(name="ID_FILIAL")
	@NotNull
	private TbFilial tbFilial;
	
	public TbVendedor() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDsNome() {
		return dsNome;
	}

	public void setDsNome(String dsNome) {
		this.dsNome = dsNome;
	}

	public String getDsFone() {
		return dsFone;
	}

	public void setDsFone(String dsFone) {
		this.dsFone = dsFone;
	}

	public Short getVlComissao() {
		return vlComissao;
	}

	public void setVlComissao(Short vlComissao) {
		this.vlComissao = vlComissao;
	}

	public String getDsObservacao() {
		return dsObservacao;
	}

	public void setDsObservacao(String dsObservacao) {
		this.dsObservacao = dsObservacao;
	}

	public TbFilial getTbFilial() {
		return tbFilial;
	}

	public void setTbFilial(TbFilial tbFilial) {
		this.tbFilial = tbFilial;
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
		TbVendedor other = (TbVendedor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}