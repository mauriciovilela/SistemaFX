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
@Table(name="tb_parametro" , uniqueConstraints=@UniqueConstraint(columnNames={"id", "ID_FILIAL"}) )
public class TbParametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Id
	@GeneratedValue
	private Integer pk;

	@NotNull
	private Integer id;

	@NotNull
	@NotBlank
	@Column(name="DS_NOME", length=50)
	private String dsNome;

	@NotNull
	@NotBlank
	@Column(name="DS_VALOR", length=1000)
	private String dsValor;

	@ManyToOne
	@JoinColumn(name="ID_FILIAL")
	@NotNull
	private TbFilial tbFilial;
	
	public TbParametro() {
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

	public String getDsValor() {
		return dsValor;
	}

	public void setDsValor(String dsValor) {
		this.dsValor = dsValor;
	}

	public TbFilial getTbFilial() {
		return tbFilial;
	}

	public void setTbFilial(TbFilial tbFilial) {
		this.tbFilial = tbFilial;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
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
		TbParametro other = (TbParametro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}