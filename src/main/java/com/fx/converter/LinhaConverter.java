package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.LinhaBLL;
import com.fx.model.TbLinha;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbLinha.class)
public class LinhaConverter implements Converter {

	private LinhaBLL bll;
	
	public LinhaConverter() {
		bll = CDIServiceLocator.getBean(LinhaBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbLinha retorno = null;
		
		if (value != null) {
			Integer id = new Integer(value);
			retorno = bll.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Integer id = (( TbLinha) value).getId();
            return String.valueOf(id);
        }
		return "";
	}

}
