package com.fx.rest;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fx.BLL.LinhaBLL;
import com.fx.dto.AcessosOUT;

@Path("/linha")
public class LinhaRest {

	@Inject
	private LinhaBLL bll;
	
	@GET
	@Path("/todos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AcessosOUT> todos() throws Exception {
		List<AcessosOUT> out = new ArrayList<AcessosOUT>();
		AcessosOUT item = new AcessosOUT();
		item.setDsFuncionalidade("TESTE");
		out.add(item);
		return out;
	}
	
}
