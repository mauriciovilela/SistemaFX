package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.ContratoBLL;
import com.fx.model.TbContrato;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbContrato.class)
public class ContratoConverter implements Converter {

	private ContratoBLL bll;
	
	public ContratoConverter() {
		bll = CDIServiceLocator.getBean(ContratoBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbContrato retorno = null;
		
		if (value != null) {
			Integer id = new Integer(value);
			retorno = bll.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Integer id = (( TbContrato) value).getPk();
            return String.valueOf(id);
        }
		return "";
	}

}
