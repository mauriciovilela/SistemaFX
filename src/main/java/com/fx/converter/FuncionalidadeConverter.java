package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.FuncionalidadeBLL;
import com.fx.model.TbFuncionalidade;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbFuncionalidade.class)
public class FuncionalidadeConverter implements Converter {

	private FuncionalidadeBLL bll;
	
	public FuncionalidadeConverter() {
		bll = CDIServiceLocator.getBean(FuncionalidadeBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbFuncionalidade retorno = null;
		
		if (value != null) {
			Short id = new Short(value);
			retorno = bll.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Short id = (( TbFuncionalidade) value).getId();
            return String.valueOf(id);
        }
		return "";
	}

}
