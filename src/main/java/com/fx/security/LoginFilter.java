package com.fx.security;


import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fx.dto.AcessosOUT;
import com.fx.model.TbUsuario;

public class LoginFilter implements Filter {

	/**
	 * A cada requisição entra neste filtro
	 */
    public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
        TbUsuario user = null;
        HttpServletRequest reqServ = ((HttpServletRequest) request);
        HttpSession sess = reqServ.getSession(false);
        
		if (sess != null){
			user = (TbUsuario) sess.getAttribute("usuarioLogado");
		}      
		
		HttpServletResponse respServ = ((HttpServletResponse) response);
		// Caso a sessão expirou
		if (user == null) {
			if (isAjaxRequest(reqServ)) {
				respServ.getWriter().print(xmlPartialRedirectToPage(reqServ, "/error"));  
				respServ.flushBuffer();
			} 
			else {
				respServ.sendRedirect(reqServ.getContextPath() + "/aberta/Login.xhtml");				
			}
		} else {
			// Valida o acesso do usuário na página
			if (acessoPermitido(reqServ.getServletPath(), sess)) {
				chain.doFilter(request, response);				
			}
			else {
				respServ.sendRedirect(reqServ.getContextPath() + "/aberta/AcessoNegado.xhtml");
			}
		}

    }
    
    /**
     * Valida o acesso do usuário na página
     * @param pagina = Nome pagina
     * @param sess = Sessão
     */
    @SuppressWarnings("unchecked")
	private boolean acessoPermitido(String pagina, HttpSession sess) {
    	int posPagina = pagina.indexOf(".xhtml");
    	pagina = pagina.substring(0, posPagina);
    	List<AcessosOUT> funcionalidades = (List<AcessosOUT>) sess.getAttribute("funcionalidades");
    	boolean acessoOK = false;
    	for (AcessosOUT item : funcionalidades) {
    		if (item.getDsPagina().contains(pagina) || (item.getDsPaginaEdicao() != null && item.getDsPaginaEdicao().equals(pagina) )) {
    			acessoOK = true;
    			break;
    		}
    	}
    	return acessoOK;
    }
    
    /**
     * Verifica se é requisição Ajax
     * @param reqServ = Requisição
     */
    private boolean isAjaxRequest(HttpServletRequest reqServ) {
    	boolean ajaxRequest = reqServ.getHeader("faces-request") != null
                && reqServ.getHeader("faces-request").toLowerCase().indexOf("ajax") > -1;
        return ajaxRequest;
    }
    
    private String xmlPartialRedirectToPage(HttpServletRequest request, String page) {  
    	StringBuilder sb = new StringBuilder();  
    	sb.append("<?xml version='1.0' encoding='UTF-8'?>");  
    	sb.append("<partial-response><redirect url=\"").append(request.getContextPath()).append(request.getServletPath()).append(page).append("\"/></partial-response>");  
    	return sb.toString();  
    }
    
    public void init(FilterConfig arg0) throws ServletException {
    	// TODO Auto-generated method stub
    }

    public void destroy() {
    	// TODO Auto-generated method stub
    }

}