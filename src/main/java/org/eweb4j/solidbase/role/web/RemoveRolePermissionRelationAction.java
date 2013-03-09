package org.eweb4j.solidbase.role.web;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.solidbase.role.model.Role;
import org.eweb4j.solidbase.role.model.RoleException;
import org.eweb4j.util.CommonUtil;
import org.eweb4j.util.JsonConverter;

@Path("${RoleConstant.MODEL_NAME}")
public class RemoveRolePermissionRelationAction extends BaseAction {
	private long roleId;
	private long[] permissionIds;

	@Path("/role-permission/remove")
	@DELETE
	public String doRemoveRolePermissionRelation(Map<String, Object> model) {

		try {
			service.deleteRolePermissionRelation(roleId, permissionIds);
			Role role = service.findPermissionByRoleId(roleId);
			model.put("role", role);
			Map<String, String> success = new HashMap<String, String>();
			success.put("status", "true");
			success.put("message", "删除权限成功");

			return JsonConverter.convert(success);
		} catch (RoleException e) {
			model.put(DWZCons.ERROR_ATTR_NAME(), CommonUtil.getExceptionString(e));

			Map<String, String> fail = new HashMap<String, String>();
			fail.put("status", "false");
			fail.put("message", "删除权限失败！原因：" + e.toString());

			return JsonConverter.convert(fail);
		}
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public void setPermissionIds(long[] permissionIds) {
		this.permissionIds = permissionIds;
	}
}
