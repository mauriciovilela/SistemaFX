package com.fx.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.fx.BLL.ContratoBLL;
import com.fx.constants.Msgs;
import com.fx.dto.FiltroIN;
import com.fx.model.TbContrato;
import com.fx.security.SessionContext;
import com.fx.service.ContratoService;
import com.fx.util.Util;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ContratoPesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private ContratoBLL contratoBLL;
	
	@Inject
	private ContratoService contratoService;
	
	private FiltroIN filtro;
	private LazyDataModel<TbContrato> filtrados;
	
	private TbContrato selecionado;
	
	private Integer codigoBaixa;
	private Date dtBaixaContrato;

	@PostConstruct
    public void init() {
		
		filtro = new FiltroIN();
		
		setFiltrados(new LazyDataModel<TbContrato>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<TbContrato> load(int first, int pageSize, String sortField, 
					SortOrder sortOrder, Map<String, Object> filters) {
				filtro.setCodigo(Util.fillIdContrato(filtro.getCodigo()));
				filtro.setFirst(first);
				filtro.setPageSize(pageSize);
				setRowCount(contratoBLL.filtradosQTD(filtro));
				List<TbContrato> resultado = contratoBLL.filtrados(filtro);
				return resultado;
			}
		});
	}
	
	public void modalContratoProdutos(TbContrato tbContrato) {
		SessionContext.getInstance().setCdContrato(tbContrato.getId());
		RequestContext.getCurrentInstance().openDialog("/restrita/modal/ContratoProdutoMD"); 
	}

	public void registrarBaixa() {
		TbContrato contrato = contratoBLL.porId(codigoBaixa);
		contrato.setDtBaixaContrato(dtBaixaContrato);
		contratoService.salvar(contrato);		
		FacesUtil.addInfoMessage(Msgs.MSG_00);
	}
	
	public FiltroIN getFiltro() {
		return filtro;
	}

	public LazyDataModel<TbContrato> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(LazyDataModel<TbContrato> filtrados) {
		this.filtrados = filtrados;
	}

	public TbContrato getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbContrato selecionado) {
		this.selecionado = selecionado;
	}

	public void setFiltro(FiltroIN filtro) {
		this.filtro = filtro;
	}

	public Integer getCodigoBaixa() {
		return codigoBaixa;
	}

	public void setCodigoBaixa(Integer codigoBaixa) {
		this.codigoBaixa = codigoBaixa;
	}

	public Date getDtBaixaContrato() {
		return dtBaixaContrato;
	}

	public void setDtBaixaContrato(Date dtBaixaContrato) {
		this.dtBaixaContrato = dtBaixaContrato;
	}

	
}