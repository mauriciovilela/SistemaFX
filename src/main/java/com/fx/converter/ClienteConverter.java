package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.ClienteBLL;
import com.fx.model.TbCliente;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbCliente.class)
public class ClienteConverter implements Converter {

	private ClienteBLL clienteBLL;
	
	public ClienteConverter() {
		clienteBLL = CDIServiceLocator.getBean(ClienteBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbCliente retorno = null;
		
		if (value != null) {
			Integer id = new Integer(value);
			retorno = clienteBLL.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Integer id = (( TbCliente) value).getId();
            return String.valueOf(id);
        }
		return "";
	}

}
