package com.fx.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.primefaces.component.export.Exporter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.fx.BLL.CarroBLL;
import com.fx.BLL.EmpresaBLL;
import com.fx.dto.FiltroCarroIN;
import com.fx.model.TbCarro;
import com.fx.model.TbEmpresa;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CarroPesquisaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CarroBLL carroBLL;
	
	private FiltroCarroIN filtro;
	private LazyDataModel<TbCarro> filtrados;
	
	private TbCarro selecionado;

	/* EMPRESA */
	@Inject
	private EmpresaBLL empresaBLL;
	
	@NotNull
	private List<TbEmpresa> empresas;
	
	@NotNull
	private String stsCarro;
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			empresas = empresaBLL.listar();
		}
	}
	
	public CarroPesquisaMB() {
		filtro = new FiltroCarroIN();

		setFiltrados(new LazyDataModel<TbCarro>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<TbCarro> load(int first, int pageSize, String sortField, 
					SortOrder sortOrder, Map<String, Object> filters) {
				filtro.setFirst(first);
				filtro.setPageSize(pageSize);
				setRowCount(carroBLL.filtradosQTD(filtro));
				return carroBLL.filtrados(filtro);
			}
		});

	}
	
	public void preProcessPDF(Object document, Exporter exporter){
//		 PDFExporter pdfExporter = (PDFExporter)document;
//		 pdfExporter.setFontType(com.lowagie.text.FontFactory.COURIER);
//		 pdfExporter.setFontSize(5);
//		 pdfExporter.setBorderColor(....);
//		 pdfExporter.setBorderWidth(1);
	}
	
	public void excluir() {
		carroBLL.remover(selecionado);
		FacesUtil.addInfoMessage("Registro excluído com sucesso.");
	}
	
	public FiltroCarroIN getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroCarroIN filtro) {
		this.filtro = filtro;
	}
	
	public LazyDataModel<TbCarro> getFiltrados() {
		return filtrados;
	}

	public void setFiltrados(LazyDataModel<TbCarro> filtrados) {
		this.filtrados = filtrados;
	}

	public TbCarro getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(TbCarro selecionado) {
		this.selecionado = selecionado;
	}

	public String getStsCarro() {
		return stsCarro;
	}

	public void setStsCarro(String stsCarro) {
		this.stsCarro = stsCarro;
	}
	public List<TbEmpresa> getEmpresas() {
		return empresas;
	}
	public void setEmpresas(List<TbEmpresa> empresas) {
		this.empresas = empresas;
	}
	
}