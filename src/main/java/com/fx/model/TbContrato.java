package com.fx.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="tb_contrato" , uniqueConstraints=@UniqueConstraint(columnNames={"id", "ID_FILIAL"}) )
public class TbContrato implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer pk;
	
	@NotNull
	private Integer id;

	@ManyToOne
	@JoinColumn(name="ID_CLIENTE")
	@NotNull
	private TbCliente tbCliente;

	@ManyToOne
	@JoinColumn(name="ID_AGENCIA")
	private TbAgencia tbAgencia;

	@ManyToOne
	@JoinColumn(name="ID_VENDEDOR")
	private TbVendedor tbVendedor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_CONTRATO")
	@NotNull
	private Date dtContrato;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_INICIO")
	@NotNull
	private Date dtInicio;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_FIM")
	@NotNull
	private Date dtFim;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_REPASSE")
	private Date dtRepasse;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_BAIXA_CONTRATO")
	private Date dtBaixaContrato;

	@Column(name="DS_FORMA_PGTO", length=50)
	@NotBlank
	@NotNull
	private String dsFormaPgto;
	
	@Column(name="VL_SUB_TOTAL")
	private BigDecimal vlSubTotal;

	@Column(name="VL_DESCONTO")
	private BigDecimal vlDesconto;

	@Column(name="VL_TOTAL")
	@NotNull
	private BigDecimal vlTotal;
	
	@Column(name="VL_PRODUCAO")
	private BigDecimal vlProducao;

	@Column(name="VL_FMT")
	@NotNull
	private BigDecimal vlFmt;

	@Column(name="VL_EMPRESA")
	@NotNull
	private BigDecimal vlEmpresa;
	
	@Column(name="DS_CAMPANHA", length=100)
	@NotBlank
	@NotNull
	private String dsCampanha;

	@ManyToOne
	@JoinColumn(name="ID_FILIAL")
	@NotNull
	private TbFilial tbFilial;
	
	public TbContrato(Integer id) {
		this.id = id;
	}
	
	public TbContrato() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public Date getDtContrato() {
		return dtContrato;
	}

	public void setDtContrato(Date dtContrato) {
		this.dtContrato = dtContrato;
	}

	public Date getDtInicio() {
		return dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public Date getDtFim() {
		return dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public BigDecimal getVlSubTotal() {
		return vlSubTotal;
	}

	public void setVlSubTotal(BigDecimal vlSubTotal) {
		this.vlSubTotal = vlSubTotal;
	}

	public BigDecimal getVlDesconto() {
		return vlDesconto;
	}

	public void setVlDesconto(BigDecimal vlDesconto) {
		this.vlDesconto = vlDesconto;
	}

	public BigDecimal getVlTotal() {
		return vlTotal;
	}

	public void setVlTotal(BigDecimal vlTotal) {
		this.vlTotal = vlTotal;
	}

	public BigDecimal getVlProducao() {
		return vlProducao;
	}

	public void setVlProducao(BigDecimal vlProducao) {
		this.vlProducao = vlProducao;
	}

	public String getDsCampanha() {
		return dsCampanha;
	}

	public void setDsCampanha(String dsCampanha) {
		this.dsCampanha = dsCampanha;
	}

	public TbCliente getTbCliente() {
		return tbCliente;
	}

	public void setTbCliente(TbCliente tbCliente) {
		this.tbCliente = tbCliente;
	}

	public TbAgencia getTbAgencia() {
		return tbAgencia;
	}

	public void setTbAgencia(TbAgencia tbAgencia) {
		this.tbAgencia = tbAgencia;
	}

	public TbVendedor getTbVendedor() {
		return tbVendedor;
	}

	public void setTbVendedor(TbVendedor tbVendedor) {
		this.tbVendedor = tbVendedor;
	}

	public TbFilial getTbFilial() {
		return tbFilial;
	}

	public void setTbFilial(TbFilial tbFilial) {
		this.tbFilial = tbFilial;
	}

	public String getDsFormaPgto() {
		return dsFormaPgto;
	}

	public void setDsFormaPgto(String dsFormaPgto) {
		this.dsFormaPgto = dsFormaPgto;
	}
	
	public Date getDtRepasse() {
		return dtRepasse;
	}

	public void setDtRepasse(Date dtRepasse) {
		this.dtRepasse = dtRepasse;
	}

	public BigDecimal getVlFmt() {
		return vlFmt;
	}

	public void setVlFmt(BigDecimal vlFmt) {
		this.vlFmt = vlFmt;
	}

	public BigDecimal getVlEmpresa() {
		return vlEmpresa;
	}

	public void setVlEmpresa(BigDecimal vlEmpresa) {
		this.vlEmpresa = vlEmpresa;
	}

	public Date getDtBaixaContrato() {
		return dtBaixaContrato;
	}

	public void setDtBaixaContrato(Date dtBaixaContrato) {
		this.dtBaixaContrato = dtBaixaContrato;
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
		TbContrato other = (TbContrato) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}