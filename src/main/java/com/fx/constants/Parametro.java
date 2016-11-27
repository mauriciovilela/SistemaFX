package com.fx.constants;


public enum Parametro {
	
	TermosContrato(1), 
	TermosContratoPIPMU(2);
	
	private final Integer value;
	
	private Parametro(Integer valor) {
	    value = valor;
	}

	public Integer getValue() {
		return value;
	}
	
}
