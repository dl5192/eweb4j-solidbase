package org.eweb4j.solidbase.permission.web;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.permission.model.PermissionCons;

@Path("${PermissionConstant.MODEL_NAME}")
public class RemovePermissionAction extends PermissionBaseAction {

	@Path("/{permId}")
	@DELETE
	public String doRemove() {
		try {
			permService.remove(permId);
			return PermissionCons.DWZ_SUCCESS_JSON("删除权限定义信息成功");
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

}
