package org.eweb4j.solidbase.user.web;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.user.model.User;
import org.eweb4j.util.JsonConverter;

@Path("${UserConstant.MODEL_NAME}")
public class RemoveUserDepartmentRelationAction extends BaseAction {
	private long userId;
	private long[] departmentIds;

	@Path("/user-department/remove")
	@DELETE
	public String doRemoveUserDepartmentRelation(Map<String, Object> model) {

		try {
			userService.deleteUserDepartmentRelation(userId, departmentIds);
			User user = userService.queryUserById(userId);
			model.put("user", user);
			Map<String, String> success = new HashMap<String, String>();
			success.put("status", "true");
			success.put("message", "删除部门成功");

			return JsonConverter.convert(success);
		} catch (Exception e) {
			Map<String, String> fail = new HashMap<String, String>();
			fail.put("status", "false");
			fail.put("message", "删除部门失败！原因：" + e.toString());

			return JsonConverter.convert(fail);
		}
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long[] getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(long[] departmentIds) {
		this.departmentIds = departmentIds;
	}

}
