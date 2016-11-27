package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.VendedorBLL;
import com.fx.model.TbVendedor;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbVendedor.class)
public class VendedorConverter implements Converter {

	private VendedorBLL bll;
	
	public VendedorConverter() {
		bll = CDIServiceLocator.getBean(VendedorBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbVendedor retorno = null;
		
		if (value != null) {
			Integer id = new Integer(value);
			retorno = bll.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Integer id = (( TbVendedor) value).getId();
            return String.valueOf(id);
        }
		return "";
	}

}
