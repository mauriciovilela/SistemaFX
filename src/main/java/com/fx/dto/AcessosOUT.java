package com.fx.dto;

import java.io.Serializable;

public class AcessosOUT implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private boolean selecionado;
	private Short idFuncionalidade;
	private String dsModulo;
	private String dsFuncionalidade;
	private String dsPagina;
	private String dsPaginaEdicao;
	private Short visivel;

	public AcessosOUT() {
	}

	public Short getIdFuncionalidade() {
		return idFuncionalidade;
	}

	public void setIdFuncionalidade(Short idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}

	public String getDsModulo() {
		return dsModulo;
	}

	public void setDsModulo(String dsModulo) {
		this.dsModulo = dsModulo;
	}

	public String getDsFuncionalidade() {
		return dsFuncionalidade;
	}

	public void setDsFuncionalidade(String dsFuncionalidade) {
		this.dsFuncionalidade = dsFuncionalidade;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public String getDsPagina() {
		return dsPagina;
	}

	public void setDsPagina(String dsPagina) {
		this.dsPagina = dsPagina;
	}

	public Short getVisivel() {
		return visivel;
	}

	public void setVisivel(Short visivel) {
		this.visivel = visivel;
	}

	public String getDsPaginaEdicao() {
		return dsPaginaEdicao;
	}

	public void setDsPaginaEdicao(String dsPaginaEdicao) {
		this.dsPaginaEdicao = dsPaginaEdicao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFuncionalidade == null) ? 0 : idFuncionalidade.hashCode());
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
		AcessosOUT other = (AcessosOUT) obj;
		if (idFuncionalidade == null) {
			if (other.idFuncionalidade != null)
				return false;
		} else if (!idFuncionalidade.equals(other.idFuncionalidade))
			return false;
		return true;
	}

}