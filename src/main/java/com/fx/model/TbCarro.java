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

@Entity
@Table(name="tb_carro" , uniqueConstraints=@UniqueConstraint(columnNames={"id", "ID_FILIAL"}) )
public class TbCarro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer pk;
	
	@NotNull
	@Column(name="id", length=10)
	private String id;

	@Column(name="DS_PLACA", length=10)
	private String dsPlaca;

	@ManyToOne
	@JoinColumn(name="ID_LINHA")
	@NotNull
	private TbLinha tbLinha;

	@ManyToOne
	@JoinColumn(name="ID_EMPRESA")
	@NotNull
	private TbEmpresa tbEmpresa;

	@ManyToOne
	@JoinColumn(name="ID_REGIAO")
	@NotNull
	private TbRegiao tbRegiao;

	@ManyToOne
	@JoinColumn(name="ID_MODELO")
	@NotNull
	private TbModelo tbModelo;

	@Column(name="DS_OBS", length=200)
	private String dsObservacao;

	@ManyToOne
	@JoinColumn(name="ID_FILIAL")
	@NotNull
	private TbFilial tbFilial;
	
	public TbCarro() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public String getDsPlaca() {
		return dsPlaca;
	}

	public void setDsPlaca(String dsPlaca) {
		this.dsPlaca = dsPlaca;
	}

	public TbEmpresa getTbEmpresa() {
		return tbEmpresa;
	}

	public void setTbEmpresa(TbEmpresa tbEmpresa) {
		this.tbEmpresa = tbEmpresa;
	}

	public TbModelo getTbModelo() {
		return tbModelo;
	}

	public void setTbModelo(TbModelo tbModelo) {
		this.tbModelo = tbModelo;
	}

	public TbLinha getTbLinha() {
		return tbLinha;
	}

	public void setTbLinha(TbLinha tbLinha) {
		this.tbLinha = tbLinha;
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

	public TbRegiao getTbRegiao() {
		return tbRegiao;
	}

	public void setTbRegiao(TbRegiao tbRegiao) {
		this.tbRegiao = tbRegiao;
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
		TbCarro other = (TbCarro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}