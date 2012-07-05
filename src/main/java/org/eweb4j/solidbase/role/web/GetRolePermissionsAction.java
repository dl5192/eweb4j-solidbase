package org.eweb4j.solidbase.role.web;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.role.model.Role;
import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.role.model.RoleException;

@Path("${RoleConstant.MODEL_NAME}")
public class GetRolePermissionsAction extends BaseAction {

	private long roleId;

	@Path("/{roleId}/permissions")
	@GET
	@POST
	public String doGetRolePermission(Map model) {
		try {

			Role role = service.findPermissionByRoleId(roleId);
			model.put("role", role);
		} catch (RoleException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return RoleCons.GET_ROLE_PERM_ACTION_RESULT();
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

}
