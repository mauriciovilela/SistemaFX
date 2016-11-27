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
@Table(name="tb_regiao" , uniqueConstraints=@UniqueConstraint(columnNames={"DS_NOME", "ID_FILIAL"}) )
public class TbRegiao implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@NotBlank
	@Column(name="DS_NOME", length=100)
	private String dsNome;
	
	@NotNull
	@NotBlank
	@Column(name="DS_BAIRRO", length=100)
	private String dsBairro;

	@ManyToOne
	@JoinColumn(name="ID_FILIAL")
	@NotNull
	private TbFilial tbFilial;
	
	public TbRegiao() {
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

	public String getDsBairro() {
		return dsBairro;
	}

	public void setDsBairro(String dsBairro) {
		this.dsBairro = dsBairro;
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
		TbRegiao other = (TbRegiao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}