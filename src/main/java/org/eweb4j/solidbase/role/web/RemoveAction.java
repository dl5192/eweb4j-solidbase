package org.eweb4j.solidbase.role.web;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.role.model.RoleException;

@Path("${RoleConstant.MODEL_NAME}")
public class RemoveAction extends BaseAction {
	@DELETE
	@Path("/batchRemove")
	public String doRemoveMulti(@QueryParam("ids") Long[] ids) {
		try {
			service.batchRemove(ids);

			return RoleCons.DWZ_SUCCESS_JSON("批量删除角色信息成功");
		} catch (Exception e) {

			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	@DELETE
	@Path("/{id}")
	public String doRemove(@PathParam("id") long id) {
		try {
			this.service.removeRole(id);

			return RoleCons.DWZ_SUCCESS_JSON("删除角色信息成功");
		} catch (RoleException e) {

			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
}
