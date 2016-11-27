package com.fx.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.fx.BLL.ContratoBLL;
import com.fx.constants.Msgs;
import com.fx.model.TbCliente;
import com.fx.model.TbContrato;
import com.fx.security.SessionContext;
import com.fx.service.NegocioException;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ContratoClienteMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ContratoBLL contratoBLL;

	private List<TbContrato> filtrados;
	
	private TbContrato selecionado;

	public ContratoClienteMB() {
		
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			pesquisar();
		}
	}

	public void excluir() {
		contratoBLL.remover(selecionado);
		filtrados.remove(selecionado);		
		FacesUtil.addInfoMessage("Registro excluído com sucesso.");
	}
	
	public void pesquisar() {
		TbCliente cliente = SessionContext.getInstance().getUsuarioLogado().getTbCliente();
		if (cliente == null)
			throw new NegocioException("O usuário logado não está cadastrado como cliente.");
		filtrados = contratoBLL.filtradosPorCliente(cliente.getId());
		// Caso o registro não seja encontrado
		if (filtrados.size() == 0) {
			FacesUtil.addWarningMessage(Msgs.MSG_04);
		}
	}

	public List<TbContrato> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<TbContrato> filtrados) {
		this.filtrados = filtrados;
	}

	public TbContrato getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbContrato selecionado) {
		this.selecionado = selecionado;
	}

	
}