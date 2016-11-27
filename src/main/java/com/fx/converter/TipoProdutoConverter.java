package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.TipoProdutoBLL;
import com.fx.model.TbTipoProduto;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbTipoProduto.class)
public class TipoProdutoConverter implements Converter {

	private TipoProdutoBLL bll;
	
	public TipoProdutoConverter() {
		bll = CDIServiceLocator.getBean(TipoProdutoBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbTipoProduto retorno = null;
		
		if (value != null) {
			Integer id = new Integer(value);
			retorno = bll.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Integer id = (( TbTipoProduto) value).getId();
            return String.valueOf(id);
        }
		return "";
	}

}
