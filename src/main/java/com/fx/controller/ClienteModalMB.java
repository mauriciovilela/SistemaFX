package com.fx.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.fx.BLL.ClienteBLL;
import com.fx.model.TbCliente;

@Named
@ViewScoped
public class ClienteModalMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ClienteBLL bll;

	private TbCliente paciente;
	 
    @PostConstruct
    public void init() {
    	Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    	Integer idPaciente = Integer.parseInt(params.get("idPaciente"));
    	paciente = bll.porId(idPaciente);
    }
     
    public TbCliente getPaciente() {
        return paciente;
    }
    
    public void fechar() {
        RequestContext.getCurrentInstance().closeDialog(null);
    }
    
    public void editar() throws IOException {
    	RequestContext.getCurrentInstance().closeDialog(paciente.getId());
    }

}
