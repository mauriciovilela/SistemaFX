package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.ModeloBLL;
import com.fx.model.TbModelo;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbModelo.class)
public class ModeloConverter implements Converter {

	private ModeloBLL bll;
	
	public ModeloConverter() {
		bll = CDIServiceLocator.getBean(ModeloBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbModelo retorno = null;
		
		if (value != null) {
			Integer id = new Integer(value);
			retorno = bll.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Integer id = (( TbModelo) value).getId();
            return String.valueOf(id);
        }
		return "";
	}

}
