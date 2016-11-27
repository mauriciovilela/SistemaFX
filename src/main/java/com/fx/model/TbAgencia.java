package com.fx.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tb_agencia" , uniqueConstraints=@UniqueConstraint(columnNames="DS_NOME") )
public class TbAgencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name="DS_NOME", length=100)
	@NotBlank
	@NotNull
	private String dsNome;

	@Column(name="DS_RESPONSAVEL", length=60)
	@NotBlank
	@NotNull
	private String dsResponsavel;
	
	@Column(name="DS_FONE", length=15)
	private String dsFone;

	@Column(name="DS_ENDERECO", length=100)
	private String dsEndereco;
	
	@Column(name="DS_OBS", length=200)
	private String dsObservacao;
	
	public TbAgencia() {
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

	public String getDsFone() {
		return dsFone;
	}

	public void setDsFone(String dsFone) {
		this.dsFone = dsFone;
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
		TbAgencia other = (TbAgencia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}