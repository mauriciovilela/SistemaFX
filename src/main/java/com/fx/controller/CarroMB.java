package com.fx.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.hibernate.exception.ConstraintViolationException;

import com.fx.BLL.EmpresaBLL;
import com.fx.BLL.LinhaBLL;
import com.fx.BLL.ModeloBLL;
import com.fx.BLL.RegiaoBLL;
import com.fx.constants.Constants;
import com.fx.constants.Msgs;
import com.fx.model.TbCarro;
import com.fx.model.TbEmpresa;
import com.fx.model.TbLinha;
import com.fx.model.TbModelo;
import com.fx.model.TbRegiao;
import com.fx.service.CarroService;
import com.fx.service.NegocioException;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CarroMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CarroService service;
	
	private TbCarro carro;
	
	/* EMPRESA */
	@Inject
	private EmpresaBLL empresaBLL;
	@NotNull
	private List<TbEmpresa> empresas;
	
	/* MODELO */
	@Inject
	private ModeloBLL modeloBLL;
	@NotNull
	private List<TbModelo> modelos;
	
	/* LINHA */
	@Inject
	private LinhaBLL linhaBLL;
	@NotNull
	private List<TbLinha> linhas;

	/* REGIAO */
	@Inject
	private RegiaoBLL regiaoBLL;
	@NotNull
	private List<TbRegiao> regioes;
	
	public CarroMB() {
		limpar();
	}
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			setRegioes(regiaoBLL.listar());
			setEmpresas(empresaBLL.listar());
			setModelos(modeloBLL.listar());
			setLinhas(linhaBLL.listar());
		}
	}
	
	private void limpar() {
		carro = new TbCarro();
	}
	
	public void salvar() {
		// Salva
		carro = service.salvar(carro);
		// Limpa a tela e objetos
		limpar();		
		// Mensagem de sucesso
		FacesUtil.addInfoMessage(Msgs.MSG_00);			
	}

	public TbCarro getCarro() {
		return carro;
	}
	
	public void setCarro(TbCarro Carro) {
		carro = Carro;	
	}

	public boolean isEditando() {
		return carro.getPk() != null;
	}

	public List<TbEmpresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<TbEmpresa> empresas) {
		this.empresas = empresas;
	}

	public List<TbModelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<TbModelo> modelos) {
		this.modelos = modelos;
	}

	public List<TbLinha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<TbLinha> linhas) {
		this.linhas = linhas;
	}

	public List<TbRegiao> getRegioes() {
		return regioes;
	}

	public void setRegioes(List<TbRegiao> regioes) {
		this.regioes = regioes;
	}

}
