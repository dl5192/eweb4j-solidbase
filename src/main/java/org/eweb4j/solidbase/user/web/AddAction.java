package org.eweb4j.solidbase.user.web;

import java.util.Map;

import javax.ws.rs.Path;

import org.eweb4j.solidbase.user.model.UserCons;
import org.eweb4j.solidbase.user.util.UserUtil;

@Path("${UserConstant.MODEL_NAME}")
public class AddAction extends BaseAction {

	private long[] roleIds;

	private long[] departIds;

	public String doNewAtGetOrPost(Map model) {
		try {
			model.put("model", UserCons.MODEL_NAME());
			model.put("openType", UserCons.OPEN_TYPE());

			return UserCons.NEW_ACTION_RESULT();
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	public String doAtPost() {
		try {
			UserUtil.setRole(roleIds, user);
			UserUtil.setDepart(departIds, user);

			userService.add(user);

			return UserCons.DWZ_SUCCESS_JSON("添加用户信息成功");
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	public void setRoleIds(long[] roleIds) {
		this.roleIds = roleIds;
	}

	public void setDepartIds(long[] departIds) {
		this.departIds = departIds;
	}
}
