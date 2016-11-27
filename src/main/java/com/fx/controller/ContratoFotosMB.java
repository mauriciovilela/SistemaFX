package com.fx.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.imgscalr.Scalr;
import org.primefaces.event.FileUploadEvent;

import com.fx.BLL.ContratoBLL;
import com.fx.constants.Msgs;
import com.fx.model.TbContrato;
import com.fx.security.SessionContext;
import com.fx.util.jsf.FacesUtil;
import com.google.common.io.Files;

@Named
@ViewScoped
public class ContratoFotosMB implements Serializable {

	private static Logger logger = Logger.getLogger(ContratoFotosMB.class);
	
	private static final long serialVersionUID = 1L;
	
	private TbContrato contrato;
	
	private List<String> imagens;
	
	String idContrato;

	@Inject
	private ContratoBLL contratoBLL;
	
	private boolean acessoUpload;
	
	@PostConstruct
	public void pageLoad() {
		if (FacesUtil.isNotPostback()) {
			limpar();
			carregaImagensPasta();
		}
	}
	
	private void verificaAcesso() {
		setAcessoUpload((SessionContext.getInstance().getUsuarioLogado().getTbCliente() == null));
	}
	
	private void limpar() {
		contrato = new TbContrato();
		imagens = new ArrayList<String>();
	}
	
	public void pesquisar() {
		contrato = contratoBLL.porCodigo(contrato.getId());
		if (contrato == null) {
			limpar();
			FacesUtil.addWarningMessage(Msgs.MSG_04);
		}
		else {
			carregaImagensPasta();
		}
	}
	
	private void carregaImagensPasta() {

		File folder = new File(getCaminhoContrato());
		File[] listOfFiles = folder.listFiles();
		
		imagens = new ArrayList<String>();
		
		if (listOfFiles != null) {
			for (File item : listOfFiles) {
				if (item.isFile()) {
					imagens.add("/upload/" + idContrato + "/" + FilenameUtils.getName(item.getPath()));
				}
		    }					
		}
	}
	
	/*
	 * Upload dos fotos do Contrato 
	 */
	public void upload(FileUploadEvent event) throws IOException {
     	
     	// Cria o diretorio caso não exista
        File file = new File(getCaminhoContrato());
        file.mkdirs();

        byte[] arquivo = event.getFile().getContents();
        String caminho = getCaminhoContrato() + getNomeArquivo(event);   
        
        try {
			logger.info("[LOG] Iniciando upload do arquivo resize = " + caminho.replace(".jpg", "_resize.jpg"));
			InputStream in = new ByteArrayInputStream(arquivo);
	    	BufferedImage image = ImageIO.read(in);
			BufferedImage resizedImage = Scalr.resize(image, 800, 600);
		    ImageIO.write(resizedImage, "jpg", new File(caminho.replace(".jpg", "_resize.jpg")));
		} catch (IOException e) {
			logger.error("[ERRO AO SALVAR IMAGEM] = " + e.getMessage(), e);
			e.printStackTrace();
		}
				
		FacesUtil.addInfoMessage(Msgs.MSG_00);
		
		carregaImagensPasta();
    }
	
	private String getNomeArquivo(FileUploadEvent event) {
		String ext = Files.getFileExtension(event.getFile().getFileName());
		return getRandomico() + "." + ext;
	}
	
	private String getCaminhoContrato() {
		
        String realPath = getCaminhoFisico();		
     	
     	StringBuilder caminho = new StringBuilder();
     	caminho.append(realPath);
     	caminho.append("/");
     	caminho.append(getCodigoContrato());
     	caminho.append("/");
     	
     	return caminho.toString();
	}
	
	private String getCaminhoFisico() {
		FacesContext aFacesContext = FacesContext.getCurrentInstance();
        ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();
        String caminho = context.getRealPath("/");
        //logger.info(caminho);
        caminho = caminho.replace("SistemaFX\\", "upload");
        caminho = caminho.replace("SistemaFX//", "upload");
        caminho = caminho.replace("SistemaFX/", "upload");
        return caminho;
	}

	private String getCodigoContrato() {
 		if (contrato != null && contrato.getPk() != null)
 			idContrato = contrato.getPk().toString();
 		else {
 			idContrato = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("contrato");
 	     	if (StringUtils.isEmpty(idContrato)) {
 	     		idContrato = "0";
 	     	}
 		}
     	return idContrato;
	}
	
	private String getRandomico() {
		return UUID.randomUUID().toString();
	}

	public TbContrato getContrato() {
		verificaAcesso();
		return contrato;
	}

	public void setContrato(TbContrato contrato) {
		this.contrato = contrato;
	}

	public List<String> getImagens() {
		return imagens;
	}

	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
	}

	public boolean isAcessoUpload() {
		return acessoUpload;
	}

	public void setAcessoUpload(boolean acessoUpload) {
		this.acessoUpload = acessoUpload;
	}

}