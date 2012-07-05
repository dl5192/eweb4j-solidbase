package org.eweb4j.solidbase.permission.web;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eweb4j.solidbase.permission.model.PermissionCons;
import org.eweb4j.solidbase.permission.util.PermUtil;
import org.eweb4j.solidbase.resource.model.Resource;

@Path("${PermissionConstant.MODEL_NAME}")
public class UpdatePermissionAction extends PermissionBaseAction {

	@Path("/{permId}")
	@PUT
	public String doUpdate(@QueryParam("resources.dwz_resource.resId") long resId, @QueryParam("resources.dwz_resource.uri") String uri) {
		try {
			Resource resource = new Resource();
			if (resId > 0)
				resource.setResId(resId);
			
			resource.setUri(uri);
			permission.setResource(resource);
			if (httpMethods == null || httpMethods.length == 0)
				return dwz.getFailedJson("缺少HTTP方法").toString();

			PermUtil.setHttpMethods(httpMethods, permission);

			permission.setPermId(permId);
			permService.update(permission);

			return PermissionCons.DWZ_SUCCESS_JSON("更新权限定义成功");
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
}
