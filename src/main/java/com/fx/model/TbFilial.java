package com.fx.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_filial" , uniqueConstraints=@UniqueConstraint(columnNames="DS_NOME") )
public class TbFilial implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@NotNull
	@Column(name="DS_NOME", length=50)
	private String dsNome;

	@NotNull
	@Column(name="DS_CIDADE", length=50)
	private String dsCidade;
	
	@Column(name="NR_CNPJ", length=18)
	private String nrCnpj;
	
	@Column(name="DS_ENDERECO", length=100)
	private String dsEndereco;

	@Column(name="DS_FONE", length=15)
	private String dsFone;
	
	public TbFilial() {
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

	public String getDsCidade() {
		return dsCidade;
	}

	public void setDsCidade(String dsCidade) {
		this.dsCidade = dsCidade;
	}

	public String getNrCnpj() {
		return nrCnpj;
	}

	public void setNrCnpj(String nrCnpj) {
		this.nrCnpj = nrCnpj;
	}

	public String getDsEndereco() {
		return dsEndereco;
	}

	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}

	public String getDsFone() {
		return dsFone;
	}

	public void setDsFone(String dsFone) {
		this.dsFone = dsFone;
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
		TbFilial other = (TbFilial) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}