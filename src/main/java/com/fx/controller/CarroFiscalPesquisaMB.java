package com.fx.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.fx.BLL.CarroBLL;
import com.fx.constants.Msgs;
import com.fx.dto.ContratoCarroOUT;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CarroFiscalPesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarroBLL carroBLL;
	
	private List<ContratoCarroOUT> filtrados;
	
	private ContratoCarroOUT item;
	
	@NotNull
	private String cdCarro;
	
	public CarroFiscalPesquisaMB() {
		
	}
		
	public void pesquisar() {
		filtrados = carroBLL.listarCarrosAlugados(cdCarro);
		if (filtrados.size() == 0) {
			item = null;
			if (carroBLL.porCodigo(cdCarro) != null) {
				FacesUtil.addWarningMessage(Msgs.MSG_06);
				//throw new NegocioException(Msgs.MSG_06);
			}
			else {
				FacesUtil.addErrorMessage(Msgs.MSG_11);
			}
		}
		else {
			item = filtrados.get(0);
		}
		setCdCarro(null);			
	}

	public List<ContratoCarroOUT> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(List<ContratoCarroOUT> filtrados) {
		this.filtrados = filtrados;
	}

	public String getCdCarro() {
		return cdCarro;
	}

	public void setCdCarro(String cdCarro) {
		this.cdCarro = cdCarro;
	}

	public ContratoCarroOUT getItem() {
		return item;
	}

	public void setItem(ContratoCarroOUT item) {
		this.item = item;
	}

	
}