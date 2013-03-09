package org.eweb4j.solidbase.user.web;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.mvc.MVC;
import org.eweb4j.mvc.action.annotation.Result;
import org.eweb4j.mvc.action.annotation.ShowValMess;
import org.eweb4j.solidbase.user.model.User;
import org.eweb4j.solidbase.user.model.UserCons;
import org.eweb4j.solidbase.user.util.UserUtil;
import org.eweb4j.util.CommonUtil;

@Path("${UserConstant.MODEL_NAME}")
public class EditAction extends BaseAction {

	private long[] roleIds;
	private long[] departIds;

	@GET
	@POST
	@Path("{id}/edit")
	@Result("${UserConstant.EDIT_ACTION_RESULT}")
	@ShowValMess("dwzJson")
	public String doEdit(Map<String, Object> model) {
		try {
			model.put("openType", UserCons.OPEN_TYPE());
			model.put("editPage", userService.getEditPage(id));
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return "success";
	}

	@GET
	@POST
	@Path("profile")
	@Result("${UserConstant.PROFILE_ACTION_RESULT}")
	public String doProfile(Map<String, Object> model) {
		try {
			User user = (User) MVC.ctx().getSession().getAttribute(UserCons.LOGIN_USER_ATTR_NAME());
			this.id = user.getId();
		} catch (Exception e) {
			model.put(DWZCons.ERROR_ATTR_NAME(),CommonUtil.getExceptionString(e));
			return DWZCons.ERROR_PAGE();
		}

		return doEdit(model);
	}

	@PUT
	@Path("profile")
	@ShowValMess("dwzJson")
	public String doProfileAtPut(@QueryParam("id") long id){
		return this.doUpdate(id);
	}
	
	@PUT
	@Path("{id}")
	@ShowValMess("dwzJson")
	public String doUpdate(@PathParam("id") long id) {
		try {
			user.setId(id);
			UserUtil.setRole(roleIds, user);
			UserUtil.setDepart(departIds, user);

			userService.update(user);
			return UserCons.DWZ_SUCCESS_JSON("更新用户信息成功");
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
