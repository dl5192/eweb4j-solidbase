package org.eweb4j.solidbase.user.web;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.user.model.UserCons;

@Path("${UserConstant.MODEL_NAME}")
public class AddUserRoleRelationAction extends BaseAction {

	private long[] userIds;

	private long[] roleIds;

	@Path("/user-role")
	@POST
	public String doAddUserRoleRelation() {
		try {
			userService.addUserRoleRelation(userIds, roleIds);

			return UserCons.DWZ_SUCCESS_JSON_FOR_ALLOC_ROLE("分配角色成功");
		} catch (Exception e) {

			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	public long[] getUserIds() {
		return userIds;
	}

	public void setUserIds(long[] userIds) {
		this.userIds = userIds;
	}

	public long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(long[] roleIds) {
		this.roleIds = roleIds;
	}

}
