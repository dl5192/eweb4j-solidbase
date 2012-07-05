package org.eweb4j.solidbase.permission.web;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.permission.model.PermissionCons;

@Path("${PermissionConstant.MODEL_NAME}")
public class BatchRemovePermissionAction extends PermissionBaseAction {

	private long[] ids;

	@Path("/batchRemove")
	@DELETE
	public String doBatchRemove() {
		try {
			permService.batchRemove(ids);

			return PermissionCons.DWZ_SUCCESS_JSON("批量删除权限定义成功");
		} catch (Exception e) {

			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	public void setIds(long[] ids) {
		this.ids = ids;
	}

}
