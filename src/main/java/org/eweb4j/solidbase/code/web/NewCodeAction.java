package org.eweb4j.solidbase.code.web;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.code.model.CodeCons;

@Path("${CodeConstant.MODEL_NAME}")
public class NewCodeAction extends CodeBaseAction {
	@Path("/new")
	@GET
	@POST
	public String doNew(Map model) {
		model.put("openType", CodeCons.OPEN_TYPE());
		model.put("model", CodeCons.MODEL_NAME());
		return CodeCons.NEW_ACTION_RESULT();
	}
}
