package org.eweb4j.solidbase.role.web;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.role.model.RoleException;

@Path("${RoleConstant.MODEL_NAME}")
public class AddRoleMenuRelationAction extends BaseAction {
	private long[] roleIds;
	private long[] treeMenuIds;
	private long[] navMenuIds;

	@Path("/role-menu")
	@POST
	public String doAddRoleMenuRelation() {
		try {
			service.addRoleMenuRelation(roleIds, treeMenuIds, navMenuIds);
			return RoleCons.DWZ_SUCCESS_JSON_FOR_ALLOC_MENU("分配菜单成功");
		} catch (RoleException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	public void setRoleIds(long[] roleIds) {
		this.roleIds = roleIds;
	}

	public void setTreeMenuIds(long[] treeMenuIds) {
		this.treeMenuIds = treeMenuIds;
	}

	public void setNavMenuIds(long[] navMenuIds) {
		this.navMenuIds = navMenuIds;
	}
}
