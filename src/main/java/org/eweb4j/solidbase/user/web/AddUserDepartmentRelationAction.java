package org.eweb4j.solidbase.user.web;

import javax.ws.rs.Path;

import org.eweb4j.solidbase.user.model.UserCons;

@Path("${UserConstant.MODEL_NAME}")
public class AddUserDepartmentRelationAction extends BaseAction {

	private long[] userIds;

	private long[] departmentIds;

	public String doUserDepartmentAtPOST() {
		try {
			System.out.println(userIds + " ||||| " + departmentIds);
			userService.addUserDepartmentRelation(userIds, departmentIds);

			return UserCons.DWZ_SUCCESS_JSON_FOR_ALLOC_DEPART("分配部门成功");
		} catch (Exception e) {
			e.printStackTrace();
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	public void setUserIds(long[] userIds) {
		this.userIds = userIds;
	}

	public void setDepartmentIds(long[] departmentIds) {
		this.departmentIds = departmentIds;
	}
}
