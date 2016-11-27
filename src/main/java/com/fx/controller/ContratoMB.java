package com.fx.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.CollectionUtils;

import com.fx.BLL.AgenciaBLL;
import com.fx.BLL.ClienteBLL;
import com.fx.BLL.ContratoBLL;
import com.fx.BLL.ContratoProdutoBLL;
import com.fx.BLL.ProdutoBLL;
import com.fx.BLL.VendedorBLL;
import com.fx.constants.Msgs;
import com.fx.model.TbAgencia;
import com.fx.model.TbCliente;
import com.fx.model.TbContrato;
import com.fx.model.TbContratoProduto;
import com.fx.model.TbProduto;
import com.fx.model.TbVendedor;
import com.fx.service.ContratoService;
import com.fx.service.NegocioException;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ContratoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ContratoService service;

	private TbContrato contrato;

	/* VENDEDOR */
	@Inject
	private ContratoProdutoBLL contratoProdutoBLL;

	/* CLIENTE */
	@Inject
	private ClienteBLL clienteBLL;
	@NotNull
	private List<TbCliente> clientes;

	/* AGENCIA */
	@Inject
	private AgenciaBLL agenciaBLL;
	@NotNull
	private List<TbAgencia> agencias;

	/* VENDEDOR */
	@Inject
	private VendedorBLL vendedorBLL;
	@NotNull
	private List<TbVendedor> vendedores;

	/* PRODUTO */
	@Inject
	private ProdutoBLL produtoBLL;
	@NotNull
	private List<TbProduto> produtos;

	/* CONTRATO */
	@Inject
	private ContratoBLL contratoBLL;
	
	TbContratoProduto itemContrato;
	private List<TbContratoProduto> lstContratoProduto;

	private Integer codigo;

	public ContratoMB() {
		limpar();
	}

	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			limpar();
			contrato.setVlTotal(new BigDecimal(0));
			contrato.setVlSubTotal(new BigDecimal(0));
			contrato.setVlDesconto(new BigDecimal(0));
			contrato.setVlProducao(new BigDecimal(0));
			carregaCodigoContrato();
			// Carrega a lista dos COMBOS
			setClientes(clienteBLL.listar());
			setAgencias(agenciaBLL.listar());
			setVendedores(vendedorBLL.listar());
			setProdutos(produtoBLL.listar());
		}
	}
	
	private void carregaCodigoContrato() {
		HttpServletRequest hsr = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		// Chave do contrato
		if (hsr.getParameterMap().get("contrato") != null) {
			Integer cdContrato = Integer.parseInt(hsr.getParameterMap().get("contrato")[0].toString());
			contrato = contratoBLL.porId(cdContrato);
			setContratoProdutos(contratoProdutoBLL.listarPorContrato(contrato.getId()));			
			setCodigo(contrato.getId());
		}
		else {				
			setCodigo(contratoBLL.getContratoGerado());				
		}
	}

	private void limpar() {
		limparItem();
		contrato = new TbContrato();
		// Data atual
		contrato.setDtContrato(new Date());
		codigo = null;
		lstContratoProduto = new ArrayList<TbContratoProduto>();
	}

	public String limparItem() {
		itemContrato = new TbContratoProduto();
		itemContrato.setNrQuantidade((short) 0);
		itemContrato.setNrMeses((short) 0);
		return null;
	}

	public void adicionarProduto() {
		if (!itemExistente()) {
			lstContratoProduto.add(itemContrato);
			atualizaTotalContrato();
			itemContrato = new TbContratoProduto();
		}
	}

	public void removerProduto(TbContratoProduto item) {
		lstContratoProduto.remove(item);
		atualizaTotalContrato();
	}

	public void atualizaTotalContrato() {

		contrato.setVlTotal(new BigDecimal(0));
		contrato.setVlSubTotal(new BigDecimal(0));

		if (lstContratoProduto.size() > 0) {
			for (TbContratoProduto item : lstContratoProduto) {
				contrato.setVlSubTotal(contrato.getVlSubTotal().add(
						item.getVlTotal()));
			}
			contrato.setVlTotal(contrato.getVlSubTotal().subtract(
					contrato.getVlDesconto()));
		}
	}

	public void consultaProduto() {
		itemContrato.setVlUnitario(itemContrato.getTbProduto().getVlTabela());
		itemContrato.setNrQuantidade(null);
		itemContrato.setVlTotal(null);
		itemContrato.setNrMeses(null);
	}

	public void atualizaTotal() {
		BigDecimal total = null;
		if (itemContrato.getVlUnitario() != null && itemContrato.getNrQuantidade() != null) {
			total = new BigDecimal(itemContrato.getNrQuantidade()).multiply(itemContrato.getVlUnitario());
			itemContrato.setVlTotal(total);
		}
		if (itemContrato.getNrMeses() != null && itemContrato.getVlTotal() != null) {
			total = itemContrato.getVlTotal().multiply(new BigDecimal(itemContrato.getNrMeses()));
			itemContrato.setVlTotal(total);
		}
	}

	private boolean itemExistente() {
		for (TbContratoProduto item : lstContratoProduto) {
			if (item.getTbProduto().equals(itemContrato.getTbProduto())) {
				// Mensagem
				FacesUtil.addWarningMessage("Produto já existe na lista");
				return true;
			}
		}
		return false;
	}

	public void salvar() {
		validar();
		// Código do contrato
		contrato.setId(codigo);
		// Calcula o valor que irá ser repassado ao FMT e Empresa
		calculaTotalRepasse();
		// Salva
		contrato = service.salvarContratoProdutos(contrato, lstContratoProduto);
		// Limpa a tela e objetos
		limpar();
		// Mensagem de sucesso
		FacesUtil.addInfoMessage(Msgs.MSG_00);
	}
	
	/*
	 * Calcula o valor que irá ser repassado ao FMT e Empresa,
	 * de acordo com a taxa de seu produto.
	 *     Valor repasse FMT do produto * Quantidade * Total de meses
	 *     Valor repasse Empresa do produto * Quantidade * Total de meses
	 */
	private void calculaTotalRepasse() {
		BigDecimal vlFmt = new BigDecimal(0);
		BigDecimal vlEmpresa = new BigDecimal(0);
		if (CollectionUtils.isNotEmpty(lstContratoProduto)) {
			for (TbContratoProduto item : lstContratoProduto) {
				// Valor repasse FMT do produto * Quantidade * Total de meses
				vlFmt =	vlFmt.add(item.getTbProduto().getVlFmt().multiply(
							new BigDecimal(item.getNrQuantidade())).multiply(
								new BigDecimal(item.getNrMeses())));
				// Valor repasse Empresa do produto * Quantidade * Total de meses
				vlEmpresa = vlEmpresa.add(item.getTbProduto().getVlEmpresa().multiply(
								new BigDecimal(item.getNrQuantidade())).multiply(
									new BigDecimal(item.getNrMeses())));
			}			
		}
		contrato.setVlFmt(vlFmt);
		contrato.setVlEmpresa(vlEmpresa);
	}

	private void validar() {
		if (contrato.getDtInicio().after(contrato.getDtFim())) {
			throw new NegocioException(Msgs.MSG_05);
		}
		if (contratoBLL.porCodigo(codigo) != null) {
			throw new NegocioException(Msgs.MSG_10);
		}
		// Valida se o usuário selecionou menos de 30 dias de intervalo 
        if (!intervaloMesOK(contrato.getDtInicio(), contrato.getDtFim())) {
        	throw new NegocioException(Msgs.MSG_09);
        }
	}
	
	/*
	 * Valida se o usuário selecionou menos de 30 dias de intervalo
	 */
	public boolean intervaloMesOK(Date dinicial, Date dfinal) {
		
		Calendar cInicial = Calendar.getInstance();  
		Calendar cFinal = Calendar.getInstance();
		cInicial.setTime(dinicial);
		cFinal.setTime(dfinal);
		
		int FEVEREIRO = 1;
		long dias = daysBetween(cInicial, cFinal);
		
		// Mês de Fevereiro
		if (cFinal.get(Calendar.MONTH) == FEVEREIRO) {
			return (dias >= 27);
		}
		else {
			return (dias >= 29);
		}
	}
	
	/*
	 * Calcula dias entre duas datas
	 */
	private long daysBetween(Calendar startDate, Calendar endDate) {  
	  Calendar date = (Calendar) startDate.clone();  
	  long daysBetween = 0;  
	  while (date.before(endDate)) {  
	    date.add(Calendar.DAY_OF_MONTH, 1);  
	    daysBetween++;  
	  }  
	  return daysBetween;  
	}
	
	public TbContrato getContrato() {
		return contrato;
	}

	public void setContrato(TbContrato Contrato) {
		contrato = Contrato;
	}

	public boolean isEditando() {
		return contrato.getPk() != null;
	}

	public List<TbCliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<TbCliente> clientes) {
		this.clientes = clientes;
	}

	public List<TbAgencia> getAgencias() {
		return agencias;
	}

	public void setAgencias(List<TbAgencia> agencias) {
		this.agencias = agencias;
	}

	public List<TbVendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<TbVendedor> vendedores) {
		this.vendedores = vendedores;
	}

	public List<TbContratoProduto> getContratoProdutos() {
//		if (isEditando()) {
//			setContratoProdutos(contratoProdutoBLL.listarPorContrato(contrato
//					.getId()));
//		}
		return lstContratoProduto;
	}

	public void setContratoProdutos(List<TbContratoProduto> itens) {
		this.lstContratoProduto = itens;
	}

	public TbContratoProduto getItemContrato() {
		return itemContrato;
	}

	public void setItemContrato(TbContratoProduto itemContrato) {
		this.itemContrato = itemContrato;
	}

	public List<TbProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<TbProduto> produtos) {
		this.produtos = produtos;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

}
