package org.eweb4j.solidbase.role.web;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.role.model.Role;
import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.role.model.RoleException;

@Path("${RoleConstant.MODEL_NAME}")
public class GetRoleMenusAction extends BaseAction {

	private long roleId;

	@Path("/{roleId}/menus")
	@GET
	@POST
	public String doGetRoleMenu(Map model) {
		try {

			Role role = service.findTreeMenuByRoleId(roleId);

			model.put("role", role);
		} catch (RoleException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return RoleCons.GET_ROLE_MENU_ACTION_RESULT();
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

}
