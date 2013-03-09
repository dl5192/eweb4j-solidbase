package org.eweb4j.solidbase.department.web;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.department.model.DepartmentCons;

@Path("${DepartmentConstant.MODEL_NAME}")
public class NewDepartmentAction extends DepartCodeSelecter {

	@Path("/new")
	@GET
	@POST
	public String doGet(Map<String, Object> model) {
		try {
			this.doSetDateModel(model);
			model.put("model", DepartmentCons.MODEL_NAME());
			model.put("openType", DepartmentCons.OPEN_TYPE());
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return DepartmentCons.NEW_ACTION_RESULT();
	}
}
