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


/**
 * The persistent class for the tb_empresa database table.
 * 
 */
@Entity
@Table(name="tb_empresa" , uniqueConstraints=@UniqueConstraint(columnNames={"DS_NOME", "ID_FILIAL"}) )
public class TbEmpresa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@NotBlank
	@Column(name="DS_NOME", length=100)
	private String dsNome;

	@Column(name="DS_ENDERECO", length=100)
	private String dsEndereco;

	@Column(name="DS_FONE_RESIDENCIAL", length=15)
	private String dsFoneResidencial;
	
	@Column(name="DS_FONE_CELULAR", length=15)
	private String dsFoneCelular;

	@Column(name="DS_RESPONSAVEL", length=60)
	private String dsResponsavel;

	@Column(name="DS_OBS", length=200)
	private String dsObservacao;

	@ManyToOne
	@JoinColumn(name="ID_FILIAL")
	@NotNull
	private TbFilial tbFilial;
	
	public TbEmpresa() {
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

	public String getDsResponsavel() {
		return dsResponsavel;
	}

	public void setDsResponsavel(String dsResponsavel) {
		this.dsResponsavel = dsResponsavel;
	}

	public String getDsObservacao() {
		return dsObservacao;
	}

	public void setDsObservacao(String dsObservacao) {
		this.dsObservacao = dsObservacao;
	}

	public String getDsEndereco() {
		return dsEndereco;
	}

	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}

	public String getDsFoneResidencial() {
		return dsFoneResidencial;
	}

	public void setDsFoneResidencial(String dsFoneResidencial) {
		this.dsFoneResidencial = dsFoneResidencial;
	}

	public String getDsFoneCelular() {
		return dsFoneCelular;
	}

	public TbFilial getTbFilial() {
		return tbFilial;
	}

	public void setTbFilial(TbFilial tbFilial) {
		this.tbFilial = tbFilial;
	}

	public void setDsFoneCelular(String dsFoneCelular) {
		this.dsFoneCelular = dsFoneCelular;
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
		TbEmpresa other = (TbEmpresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}