package com.fx.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fx.validation.EMAIL;

@Entity
@Table(name="tb_cliente" , uniqueConstraints=@UniqueConstraint(columnNames={"DS_RAZAO_SOCIAL"}) )
public class TbCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@NotBlank
	@NotNull
	@Column(name="DS_RAZAO_SOCIAL", length=100)
	private String dsRazaoSocial;

	@Column(name="DS_NOME_FANTASIA", length=100)
	private String dsNomeFantasia;

	@Column(name="DS_ENDERECO", length=100)
	private String dsEndereco;

	@NotBlank
	@NotNull
	@Column(name="DS_RESPONSAVEL", length=40)
	private String dsResponsavel;

	@Column(name="DS_IE", length=18)
	private String dsIncricaoEstadual;
	
	@NotBlank
	@NotNull
	@Column(name="NR_CNPJ", length=18)
	private String nrCnpj;
	
	@Column(name="DS_FONE_RESIDENCIAL", length=15)
	private String dsFoneResidencial;
	
	@Column(name="DS_FONE_CELULAR", length=15)
	private String dsFoneCelular;

	@EMAIL
	@Column(name="DS_EMAIL", length=100)
	private String dsEmail;

	@Column(name="DS_OBS", length=200)
	private String dsObservacao;
	
	public TbCliente() {
		
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDsEndereco() {
		return dsEndereco;
	}

	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public String getDsObservacao() {
		return dsObservacao;
	}

	public void setDsObservacao(String dsObservacao) {
		this.dsObservacao = dsObservacao;
	}

	public String getDsRazaoSocial() {
		return dsRazaoSocial;
	}

	public void setDsRazaoSocial(String dsRazaoSocial) {
		this.dsRazaoSocial = dsRazaoSocial;
	}

	public String getDsNomeFantasia() {
		return dsNomeFantasia;
	}

	public void setDsNomeFantasia(String dsNomeFantasia) {
		this.dsNomeFantasia = dsNomeFantasia;
	}

	public String getDsResponsavel() {
		return dsResponsavel;
	}

	public void setDsResponsavel(String dsResponsavel) {
		this.dsResponsavel = dsResponsavel;
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

	public void setDsFoneCelular(String dsFoneCelular) {
		this.dsFoneCelular = dsFoneCelular;
	}

	public String getNrCnpj() {
		return nrCnpj;
	}

	public void setNrCnpj(String nrCnpj) {
		this.nrCnpj = nrCnpj;
	}

	public String getDsIncricaoEstadual() {
		return dsIncricaoEstadual;
	}

	public void setDsIncricaoEstadual(String dsIncricaoEstadual) {
		this.dsIncricaoEstadual = dsIncricaoEstadual;
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
		TbCliente other = (TbCliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}