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
@Table(name="tb_usuario" , uniqueConstraints=@UniqueConstraint(columnNames={"DS_USUARIO", "ID_FILIAL"}) )
public class TbUsuario implements Serializable {
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
	@Column(name="DS_SENHA", length=100)
	private String dsSenha;

	@NotNull
	@NotBlank
	@Column(name="DS_USUARIO", length=60)
	private String dsUsuario;
	
	@ManyToOne
	@JoinColumn(name="ID_CLIENTE")
	private TbCliente tbCliente;

	@ManyToOne
	@JoinColumn(name="ID_FILIAL")
	@NotNull
	private TbFilial tbFilial;

	public TbUsuario() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDsNome() {
		return this.dsNome;
	}

	public void setDsNome(String dsNome) {
		this.dsNome = dsNome;
	}

	public String getDsSenha() {
		return this.dsSenha;
	}

	public void setDsSenha(String dsSenha) {
		this.dsSenha = dsSenha;
	}

	public String getDsUsuario() {
		return this.dsUsuario;
	}

	public void setDsUsuario(String dsUsuario) {
		this.dsUsuario = dsUsuario;
	}

	public TbCliente getTbCliente() {
		return tbCliente;
	}

	public void setTbCliente(TbCliente tbCliente) {
		this.tbCliente = tbCliente;
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
		TbUsuario other = (TbUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}