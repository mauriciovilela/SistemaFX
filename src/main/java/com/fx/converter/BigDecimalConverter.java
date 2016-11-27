package com.fx.converter;

import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.fx.util.Util;

	@FacesConverter(value = "bigDecimalConverter") 
	public class BigDecimalConverter implements Converter {

	@Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {  

        BigDecimal valor = null;  

        if (arg2 != null) {  
            if (arg2.isEmpty() == false) {  
                valor = Util.toBigDecimal(arg2);  
            }  
        }  

        return valor;  

    }  

	@Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {  
        return arg2.toString();  
    } 

}
