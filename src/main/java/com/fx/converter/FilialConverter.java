package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.FilialBLL;
import com.fx.model.TbFilial;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbFilial.class)
public class FilialConverter implements Converter {

	private FilialBLL bll;
	
	public FilialConverter() {
		bll = CDIServiceLocator.getBean(FilialBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbFilial retorno = null;
		
		if (value != null) {
			Integer id = new Integer(value);
			retorno = bll.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Integer id = (( TbFilial) value).getId();
            return String.valueOf(id);
        }
		return "";
	}

}
