package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.PerfilBLL;
import com.fx.model.TbPerfil;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbPerfil.class)
public class PerfilConverter implements Converter {

	private PerfilBLL bll;
	
	public PerfilConverter() {
		bll = CDIServiceLocator.getBean(PerfilBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbPerfil retorno = null;
		
		if (value != null) {
			Integer id = new Integer(value);
			retorno = bll.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Integer id = (( TbPerfil) value).getId();
            return String.valueOf(id);
        }
		return "";
	}

}
