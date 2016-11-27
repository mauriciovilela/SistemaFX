package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.AgenciaBLL;
import com.fx.model.TbAgencia;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbAgencia.class)
public class AgenciaConverter implements Converter {

	private AgenciaBLL bll;
	
	public AgenciaConverter() {
		bll = CDIServiceLocator.getBean(AgenciaBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbAgencia retorno = null;
		
		if (value != null) {
			Integer id = new Integer(value);
			retorno = bll.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Integer id = (( TbAgencia) value).getId();
            return String.valueOf(id);
        }
		return "";
	}

}
