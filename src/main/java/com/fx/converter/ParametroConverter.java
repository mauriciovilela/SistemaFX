package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.ParametroBLL;
import com.fx.model.TbParametro;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbParametro.class)
public class ParametroConverter implements Converter {

	private ParametroBLL bll;
	
	public ParametroConverter() {
		bll = CDIServiceLocator.getBean(ParametroBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbParametro retorno = null;
		
		if (value != null) {
			Integer id = new Integer(value);
			retorno = bll.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Integer id = (( TbParametro) value).getId();
            return String.valueOf(id);
        }
		return "";
	}

}
