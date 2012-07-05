package org.eweb4j.solidbase.role.web;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.role.model.RoleException;

@Path("${RoleConstant.MODEL_NAME}")
public class AddRolePermissionRelationAction extends BaseAction {
	private long[] roleIds;
	private long[] permissionIds;

	@Path("/role-permission")
	@POST
	public String doAddRolePermissionRelation() {
		try {
			service.addRolePermissionRelation(roleIds, permissionIds);
			return RoleCons.DWZ_SUCCESS_JSON_FOR_ALLOC_PERM("分配权限成功");
		} catch (RoleException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
	public void setRoleIds(long[] roleIds) {
		this.roleIds = roleIds;
	}
	
	public void setPermissionIds(long[] permissionIds) {
		this.permissionIds = permissionIds;
	}
}
