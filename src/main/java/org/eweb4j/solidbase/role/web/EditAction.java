package org.eweb4j.solidbase.role.web;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.eweb4j.solidbase.role.model.Role;
import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.role.model.RoleException;

@Path("${RoleConstant.MODEL_NAME}")
public class EditAction extends BaseAction {
	@GET
	@POST
	@Path("/{roleId}/edit")
	public String doEdit(@PathParam("roleId") long id, Map<String, Object> model) {
		try {
			model.put("editPage", this.service.getEditPage(id));
			model.put("openType", RoleCons.OPEN_TYPE());
		} catch (RoleException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return RoleCons.EDIT_ACTION_RESULT();
	}

	@PUT
	@Path("/{roleId}")
	public String doUpdate(@PathParam("roleId") long id,
			@QueryParam("role") Role role) {
		try {
			role.setRoleId(id);
			this.service.editRole(role);
			return RoleCons.DWZ_SUCCESS_JSON("更改角色信息成功");
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
}
