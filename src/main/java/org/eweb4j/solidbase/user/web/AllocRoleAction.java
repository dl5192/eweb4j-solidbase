package org.eweb4j.solidbase.user.web;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.user.model.UserCons;

@Path("${UserConstant.MODEL_NAME}")
public class AllocRoleAction extends BaseAction {

	@Path("/alloc-role")
	@GET
	@POST
	public String doAllocRole(Map<String, Object> model) {

		try {
			model.put("openType", UserCons.ALLOC_ROLE_OPEN_TYPE());
			model.put("random", Math.random());
			model.put("action", UserCons.ADD_USER_ROLE_ACTOIN());
			model.put("users", userService.getListPage(-1, -1).getPojos());
			model.put("roles", roleService.getAll());
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return UserCons.ALLOC_ROLE_ACTION_RESULT();
	}
}
