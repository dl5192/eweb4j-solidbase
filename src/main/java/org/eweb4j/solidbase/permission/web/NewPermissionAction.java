package org.eweb4j.solidbase.permission.web;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.mvc.MVC;
import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.permission.model.PermissionCons;
import org.eweb4j.solidbase.permission.util.PermUtil;

@Path("${PermissionConstant.MODEL_NAME}")
public class NewPermissionAction extends PermissionBaseAction {

	@Path("/new")
	@GET
	@POST
	public String doNewPermission(Map model) {

		try {
			model.put("model", PermissionCons.MODEL_NAME());
			Code httpType = codeService.getAndCreateCodeByCodeValue(PermissionCons.HTTP_METHOD_CODE_VALUE());
			List<Code> httpMethods = codeService.queryByCodeTypeIdAndParentId(httpType.getCodeId(), 0, -1, -1).getPojos();
			if (httpMethods == null || httpMethods.size() == 0)
				return dwz.getFailedJson("缺少HTTP操作信息，请先到代码管理添加操作信息").toString();

			model.put("httpMethodList", httpMethods);

			Code permType = codeService.getAndCreateCodeByCodeValue(PermissionCons.PERMISSION_TYPE_CODE_VALUE());
			List<Code> permTypes = codeService.queryByCodeTypeIdAndParentId(permType.getCodeId(), 0, -1, -1).getPojos();
			if (permTypes == null || permTypes.size() == 0)
				return dwz.getFailedJson("缺少权限分类信息，请先到代码管理添加权限类别").toString();

			model.put("permTypes", permTypes);

			model.put("openType", PermissionCons.OPEN_TYPE());
			
			PermUtil.setActions(permService, codeService, MVC.ctx().getRequest());

		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return PermissionCons.NEW_ACTION_RESULT();
	}

}
