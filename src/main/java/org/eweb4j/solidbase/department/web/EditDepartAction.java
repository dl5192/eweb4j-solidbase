package org.eweb4j.solidbase.department.web;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.department.model.DepartmentCons;

@Path("${DepartmentConstant.MODEL_NAME}")
public class EditDepartAction extends DepartCodeSelecter {

	private long departId;

	@Path("/{departId}/edit")
	@GET
	@POST
	public String doEdit(Map model) {
		try {
			doSetDateModel(model);
			model.put("editPage", departService.getEditPage(departId));
			model.put("openType", DepartmentCons.OPEN_TYPE());
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return DepartmentCons.EDIT_ACTION_RESULT();
	}

	public void setDepartId(long departId) {
		this.departId = departId;
	}

}
