package com.fx.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.BLL.CarroBLL;
import com.fx.model.TbCarro;
import com.fx.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = TbCarro.class)
public class CarroConverter implements Converter {

	private CarroBLL bll;
	
	public CarroConverter() {
		bll = CDIServiceLocator.getBean(CarroBLL.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TbCarro retorno = null;
		
		if (value != null) {
			Integer id = new Integer(value);
			retorno = bll.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && !value.equals("")) {
            Integer id = (( TbCarro) value).getPk();
            return String.valueOf(id);
        }
		return "";
	}

}
