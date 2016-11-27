package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.EmpresaBLL;
import com.fx.model.TbEmpresa;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbEmpresa.class)
public class EmpresaConverter implements Converter {

	private EmpresaBLL bll;
	
	public EmpresaConverter() {
		bll = CDIServiceLocator.getBean(EmpresaBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbEmpresa retorno = null;
		
		if (value != null) {
			Integer id = new Integer(value);
			retorno = bll.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Integer id = (( TbEmpresa) value).getId();
            return String.valueOf(id);
        }
		return "";
	}

}
