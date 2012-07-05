package org.eweb4j.solidbase.permission.web;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eweb4j.solidbase.permission.model.PermissionCons;
import org.eweb4j.solidbase.permission.util.PermUtil;
import org.eweb4j.solidbase.resource.model.Resource;

@Path("${PermissionConstant.MODEL_NAME}")
public class AddPermissionAction extends PermissionBaseAction {

	@Path("/")
	@POST
	public String doAddPermission(@QueryParam("resources.dwz_resource.resId") long resId,
			@QueryParam("resources.dwz_resource.uri") String uri) {
		try {
			Resource resource = new Resource();
			if (resId > 0)
				resource.setResId(resId);
			
			resource.setUri(uri);
			permission.setResource(resource);
			PermUtil.setHttpMethods(httpMethods, permission);
			permService.addPermission(permission);

			return PermissionCons.DWZ_SUCCESS_JSON("添加权限定义成功");
		} catch (Exception e) {

			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

}
