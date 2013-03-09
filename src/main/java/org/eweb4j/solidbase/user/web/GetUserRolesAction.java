package org.eweb4j.solidbase.user.web;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.user.model.User;
import org.eweb4j.solidbase.user.model.UserCons;

@Path("${UserConstant.MODEL_NAME}")
public class GetUserRolesAction extends BaseAction {

	private long userId;

	@Path("/{userId}/roles")
	@GET
	@POST
	public String getUserRoles(Map<String, Object> model) {
		try {

			User user = this.userService.queryUserById(userId);
			model.put("user", user);
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return UserCons.GET_USER_ROLE_ACTION_RESULT();
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
