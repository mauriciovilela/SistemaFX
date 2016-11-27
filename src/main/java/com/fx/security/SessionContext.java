package com.fx.security;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.fx.model.TbFilial;
import com.fx.model.TbUsuario;
 
 
public class SessionContext {
    
    private static SessionContext instance;
    
    public static SessionContext getInstance(){
         if (instance == null){
             instance = new SessionContext();
         }
         return instance;
    }
    
    private SessionContext(){
         
    }
    
    private ExternalContext currentExternalContext(){
         if (FacesContext.getCurrentInstance() == null){
             throw new RuntimeException("O FacesContext não pode ser chamado fora de uma requisição HTTP");
         }else{
             return FacesContext.getCurrentInstance().getExternalContext();
         }
    }
    
    public TbUsuario getUsuarioLogado() {
         return (TbUsuario) getAttribute("usuarioLogado");
    }
    
    public Integer getCdContrato() {
         return (Integer) getAttribute("cdContrato");
    }
    
    public TbFilial getFilial(){
        return getUsuarioLogado().getTbFilial();
    }
    
    public Integer getCdFilial(){
        return getUsuarioLogado().getTbFilial().getId();
    }
    
    public void setUsuarioLogado(TbUsuario usuario){
         setAttribute("usuarioLogado", usuario);
    }
    
    public void setCdContrato(Integer cdContrato){
         setAttribute("cdContrato", cdContrato);
    }
    
    public void encerrarSessao(){   
         currentExternalContext().invalidateSession();
    }
    
    public Object getAttribute(String nome){
         return currentExternalContext().getSessionMap().get(nome);
    }
    
    public void setAttribute(String nome, Object valor){
         currentExternalContext().getSessionMap().put(nome, valor);
    }
    
}