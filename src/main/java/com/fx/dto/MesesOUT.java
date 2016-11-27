package com.fx.dto;

import java.io.Serializable;
import java.util.Calendar;

public class MesesOUT implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Calendar data;
	private String mesAno;
	
	public MesesOUT() {
		
	}

	public MesesOUT(Calendar data, String mesAno) {
		this.data = data;
		this.mesAno = mesAno;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MesesOUT other = (MesesOUT) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}