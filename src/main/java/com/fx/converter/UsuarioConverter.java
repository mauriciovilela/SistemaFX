package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.UsuarioBLL;
import com.fx.model.TbUsuario;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbUsuario.class)
public class UsuarioConverter implements Converter {

	private UsuarioBLL bll;
	
	public UsuarioConverter() {
		bll = CDIServiceLocator.getBean(UsuarioBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbUsuario retorno = null;
		
		if (value != null) {
			Integer id = new Integer(value);
			retorno = bll.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Integer id = (( TbUsuario) value).getId();
            return String.valueOf(id);
        }
		return "";
	}

}
