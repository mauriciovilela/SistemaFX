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
@Table(name="tb_modelo" , uniqueConstraints=@UniqueConstraint(columnNames={"DS_DESCRICAO", "ID_FILIAL"}) )
public class TbModelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@NotBlank
	@Column(name="DS_DESCRICAO", length=100)
	private String dsDescricao;

	@Column(name="DS_GAB_BUS", length=100)
	private String dsGabBus;

	@Column(name="DS_GAB_BACK", length=100)
	private String dsGabBack;

	@Column(name="DS_GAB_IN", length=100)
	private String dsGabIn;

	@ManyToOne
	@JoinColumn(name="ID_FILIAL")
	@NotNull
	private TbFilial tbFilial;
	
	public TbModelo() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDsDescricao() {
		return dsDescricao;
	}


	public void setDsDescricao(String dsDescricao) {
		this.dsDescricao = dsDescricao;
	}


	public String getDsGabBus() {
		return dsGabBus;
	}


	public void setDsGabBus(String dsGabBus) {
		this.dsGabBus = dsGabBus;
	}


	public String getDsGabBack() {
		return dsGabBack;
	}


	public void setDsGabBack(String dsGabBack) {
		this.dsGabBack = dsGabBack;
	}


	public String getDsGabIn() {
		return dsGabIn;
	}


	public void setDsGabIn(String dsGabIn) {
		this.dsGabIn = dsGabIn;
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
		TbModelo other = (TbModelo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}