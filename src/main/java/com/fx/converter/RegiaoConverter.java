package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.RegiaoBLL;
import com.fx.model.TbRegiao;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbRegiao.class)
public class RegiaoConverter implements Converter {

	private RegiaoBLL bll;
	
	public RegiaoConverter() {
		bll = CDIServiceLocator.getBean(RegiaoBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbRegiao retorno = null;
		
		if (value != null) {
			Integer id = new Integer(value);
			retorno = bll.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Integer id = (( TbRegiao) value).getId();
            return String.valueOf(id);
        }
		return "";
	}

}
