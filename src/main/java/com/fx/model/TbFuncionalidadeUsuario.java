package com.fx.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_funcionalidade_usuario" , uniqueConstraints=@UniqueConstraint(columnNames={"ID_FUNCIONALIDADE", "ID_USUARIO"}) )
public class TbFuncionalidadeUsuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name="ID_FUNCIONALIDADE")
	@NotNull
	private TbFuncionalidade tbFuncionalidade;

	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	@NotNull
	private TbUsuario tbUsuario;

	public TbFuncionalidadeUsuario() {
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public TbFuncionalidade getTbFuncionalidade() {
		return tbFuncionalidade;
	}

	public void setTbFuncionalidade(TbFuncionalidade tbFuncionalidade) {
		this.tbFuncionalidade = tbFuncionalidade;
	}

	public TbUsuario getTbUsuario() {
		return tbUsuario;
	}

	public void setTbUsuario(TbUsuario tbUsuario) {
		this.tbUsuario = tbUsuario;
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
		TbFuncionalidadeUsuario other = (TbFuncionalidadeUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}