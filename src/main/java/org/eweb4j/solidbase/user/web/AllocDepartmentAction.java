package org.eweb4j.solidbase.user.web;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.user.model.UserCons;

@Path("${UserConstant.MODEL_NAME}")
public class AllocDepartmentAction extends BaseAction {

	private static final String ulStyle = "";
	private static final String ulOncheckHandler = "";

	@Path("/alloc-department")
	@GET
	@POST
	public String doAllocDepartment(Map model) {

		try {
			model.put("openType", UserCons.ALLOC_DEPART_OPEN_TYPE());
			model.put("random", Math.random());
			model.put("action", UserCons.ADD_USER_DEPART_ACTOIN());
			model.put("users", userService.getListPage(-1, -1).getPojos());
			model.put("departmentTree", departService.getDepartmentDWZTree(null, ulStyle, ulOncheckHandler));

		} catch (Exception e) {
			e.printStackTrace();
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return UserCons.ALLOC_DEPART_ACTION_RESULT();
	}
}