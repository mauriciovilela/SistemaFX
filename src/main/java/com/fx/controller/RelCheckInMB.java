package com.fx.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import com.fx.BLL.CarroBLL;
import com.fx.BLL.EmpresaBLL;
import com.fx.BLL.LinhaBLL;
import com.fx.constants.Constants.TbTipoProduto;
import com.fx.dto.CheckIn;
import com.fx.dto.RelCheckInOUT;
import com.fx.model.TbEmpresa;
import com.fx.model.TbLinha;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class RelCheckInMB implements Serializable {

	private static final long serialVersionUID = 1L;

	/* EMPRESA */
	@Inject
	private EmpresaBLL empresaBLL;
	@NotNull
	private List<TbEmpresa> empresas;
	@NotNull
	private TbEmpresa empresa;

	/* LINH */
	@Inject
	private LinhaBLL linhaBLL;
	@NotNull
	private List<TbLinha> linhas;
	private TbLinha linha;
	
	@NotNull
	private Integer idTipoProduto;
	private String tipoProduto;

	private String dsCampanha;
	private String filtroLinhaCampanha;
	
	/* CARROS */
	@Inject
	private CarroBLL carroBLL;
	private List<RelCheckInOUT> relatorio;
	
	boolean encontrou;
	
	public RelCheckInMB() {
		
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			empresa = new TbEmpresa();
			linha = new TbLinha();
			empresas = empresaBLL.listar();
			linhas = linhaBLL.listar();
			filtroLinhaCampanha = "L";
		}
	}
	
	public void pesquisar() {
		// Campo será exibido no cabeçalho do relatório
		tipoProduto = idTipoProduto.equals(TbTipoProduto.INTERNO) ? "Interno" : "Externo";
		// Filtros
		CheckIn filtro = new CheckIn();
		filtro.setEmpresa(empresa);
		filtro.setTipoProduto(idTipoProduto);
		filtro.setCampanha(dsCampanha);
		if (linha != null)
			filtro.setLinha(linha.getId());
		relatorio = carroBLL.relatorioCheckIN(filtro);
	}
	
	public void mudaEstadoFiltros() {
		if (filtroLinhaCampanha.equals("L")) {
			dsCampanha = StringUtils.EMPTY;
		}
		else {
			linha = null;
		}
	}
	
	public List<TbEmpresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<TbEmpresa> empresas) {
		this.empresas = empresas;
	}

	public TbEmpresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(TbEmpresa empresa) {
		this.empresa = empresa;
	}

	public List<RelCheckInOUT> getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(List<RelCheckInOUT> relatorio) {
		this.relatorio = relatorio;
	}

	public Integer getIdTipoProduto() {
		return idTipoProduto;
	}

	public void setIdTipoProduto(Integer idTipoProduto) {
		this.idTipoProduto = idTipoProduto;
	}

	public String getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public List<TbLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<TbLinha> linhas) {
		this.linhas = linhas;
	}

	public TbLinha getLinha() {
		return linha;
	}

	public void setLinha(TbLinha linha) {
		this.linha = linha;
	}

	public String getDsCampanha() {
		return dsCampanha;
	}

	public void setDsCampanha(String dsCampanha) {
		this.dsCampanha = dsCampanha;
	}

	public String getFiltroLinhaCampanha() {
		return filtroLinhaCampanha;
	}

	public void setFiltroLinhaCampanha(String filtroLinhaCampanha) {
		this.filtroLinhaCampanha = filtroLinhaCampanha;
	}

}
