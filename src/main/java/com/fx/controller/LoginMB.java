package com.fx.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import com.fx.BLL.FuncionalidadeUsuarioBLL;
import com.fx.BLL.UsuarioBLL;
import com.fx.dto.AcessosOUT;
import com.fx.model.TbUsuario;
import com.fx.security.SessionContext;
import com.fx.util.Util;
import com.fx.util.jsf.FacesUtil;

@Named
@ViewScoped
public class LoginMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(LoginMB.class);

	private String email;
	
	@NotNull
	private String login;
	
	@NotNull
	private String senha;
	
	@Inject
	private UsuarioBLL usuarioBLL;
	
	@Inject
	private FuncionalidadeUsuarioBLL funcionalidadeUsuarioBLL;
	
	private TbUsuario usuarioLogado;
	
	public String realizarLogin() {
		try {
			if (StringUtils.isBlank(login) || StringUtils.isBlank(senha)) {
				FacesUtil.addErrorMessage("Login e Senha devem ser informados.");
				return StringUtils.EMPTY;
			}
			logger.info("[LOG] Tentando logar com usuário " + login);
			
			senha = Util.md5Java(senha);
			usuarioLogado = usuarioBLL.porLogin(login, senha);
			
			if (usuarioLogado == null) {
				FacesUtil.addErrorMessage("Login ou Senha inválidos, tente novamente.");
				FacesContext.getCurrentInstance().validationFailed();
				return StringUtils.EMPTY;
			}
			else {
				FacesUtil.addInfoMessage("Logout realizado com sucesso !");
				// Adiciona os dados do usuario logado na sessao
				SessionContext.getInstance().setAttribute("usuarioLogado", usuarioLogado);
				// Carrega os dados do Menu
				consultarMenu();
				return "/aberta/Home.xhtml?faces-redirect=true";
			}
			
		} catch (Exception e) {
			logger.error(e);
			FacesUtil.addErrorMessage("Ocorreu um ERRO: " + e.getMessage());
			FacesContext.getCurrentInstance().validationFailed();
			e.printStackTrace();
			return StringUtils.EMPTY;
		}

	}
	
	/*
	 * Carrega o menu dinamicamente (Banco de dados)
	 */
    private void consultarMenu() {

    	MenuModel menuModel = new DefaultMenuModel();
    	
    	DefaultSubMenu menuModulo = null;
    	DefaultMenuItem menuFuncinalidade;
    	
    	List<AcessosOUT> funcionalidades = null;
    	Integer idUsuario = SessionContext.getInstance().getUsuarioLogado().getId();
    	String moduloTemp = "";

	    funcionalidades = funcionalidadeUsuarioBLL.listarFuncionalidadesUsuario(idUsuario);
	    if (funcionalidades.size() > 0) {
	    	for (AcessosOUT funcionalidade : funcionalidades) {
	    		if (funcionalidade.getVisivel() == 1) {
		    		if ( !moduloTemp.equals(funcionalidade.getDsModulo())) {
			    	    menuModulo = new DefaultSubMenu(funcionalidade.getDsModulo());    	    		    			
					    menuModel.addElement(menuModulo);
		    		}
	        	    menuFuncinalidade = new DefaultMenuItem(funcionalidade.getDsFuncionalidade());
	        	    menuFuncinalidade.setOutcome(funcionalidade.getDsPagina());
	        	    menuModulo.addElement(menuFuncinalidade);    	  
	        	    moduloTemp = funcionalidade.getDsModulo();	    				    			
	    		}
	    	}
		    menuModel.generateUniqueIds();
		    SessionContext.getInstance().setAttribute("funcionalidades", funcionalidades);
		    SessionContext.getInstance().setAttribute("menuModel", menuModel);
	    }
    }	
    
    public MenuModel getMenuModel() {
    	return (MenuModel) SessionContext.getInstance().getAttribute("menuModel");
    }
    
	public String doLogout() {
		//logger.info("Fazendo logout com usuário " + SessionContext.getInstance().getUsuarioLogado().getDsUsuario());
		SessionContext.getInstance().encerrarSessao();
		return "/aberta/Login.xhtml?faces-redirect=true";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TbUsuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(TbUsuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}