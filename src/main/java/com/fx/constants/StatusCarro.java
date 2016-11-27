package com.fx.constants;


public enum StatusCarro {
	
	Disponivel("D"), Alugado("A"), Todos("T");
	
	private final String value;
	
	private StatusCarro(String valor) {
	    value = valor;
	}

	public static String getValue(String cdTipo) {
		for (StatusCarro item : StatusCarro.values()) {
			if (item.name().equals(cdTipo)) {
				return item.value;
			}
		}
		return "";
	}
	
}
