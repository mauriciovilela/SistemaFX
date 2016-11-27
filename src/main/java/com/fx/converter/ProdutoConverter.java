package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.ProdutoBLL;
import com.fx.model.TbProduto;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbProduto.class)
public class ProdutoConverter implements Converter {

	private ProdutoBLL bll;
	
	public ProdutoConverter() {
		bll = CDIServiceLocator.getBean(ProdutoBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbProduto retorno = null;
		
		if (value != null) {
			Integer id = new Integer(value);
			retorno = bll.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Integer id = (( TbProduto) value).getId();
            return String.valueOf(id);
        }
		return "";
	}

}
