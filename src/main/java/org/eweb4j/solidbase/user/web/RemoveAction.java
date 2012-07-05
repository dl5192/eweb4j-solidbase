package org.eweb4j.solidbase.user.web;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

import org.eweb4j.mvc.action.annotation.ShowValMess;
import org.eweb4j.solidbase.user.model.UserCons;

@Path("${UserConstant.MODEL_NAME}")
public class RemoveAction extends BaseAction {
	@DELETE
	@Path("batchRemove")
	@ShowValMess("dwzJson")
	public String doRemoveMulti() {
		try {
			userService.batchRemove(ids);

			return UserCons.DWZ_SUCCESS_JSON("批量删除用户成功");
		} catch (Exception e) {

			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	@DELETE
	@Path("/{id}")
	@ShowValMess("dwzJson")
	public String doRemoveOne() {
		try {
			userService.removeOne(id);

			return UserCons.DWZ_SUCCESS_JSON("删除用户成功");
		} catch (Exception e) {

			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
}
