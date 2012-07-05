package org.eweb4j.solidbase.role.web;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.role.model.RoleException;
import org.eweb4j.util.JsonConverter;

@Path("${RoleConstant.MODEL_NAME}")
public class SuggestAction extends BaseAction {
	@POST
	@GET
	@Path("/suggest.json")
	public String doSuggest() {
		try {
			return JsonConverter.convert(this.service.getAll());
		} catch (RoleException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
}
