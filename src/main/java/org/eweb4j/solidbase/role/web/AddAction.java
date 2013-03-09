package org.eweb4j.solidbase.role.web;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eweb4j.mvc.action.annotation.ShowValMess;
import org.eweb4j.solidbase.role.model.Role;
import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.role.model.RoleException;


@Path("${RoleConstant.MODEL_NAME}")
public class AddAction extends BaseAction {

	@GET
	@POST
	@Path("/new")
	public String doNew(Map<String, Object> model) {
		model.put("model", RoleCons.MODEL_NAME());
		model.put("openType", RoleCons.OPEN_TYPE());
		return RoleCons.NEW_ACTION_RESULT();
	}

	@POST
	@Path("/")
	@ShowValMess("dwzJson")
	public String doAdd(@QueryParam("role") Role role) {
		try {
			service.addRole(role);
			return RoleCons.DWZ_SUCCESS_JSON("添加角色信息成功");
		} catch (RoleException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
}
