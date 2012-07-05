package org.eweb4j.solidbase.permission.web;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.mvc.MVC;
import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.permission.model.Permission;
import org.eweb4j.solidbase.permission.model.PermissionCons;
import org.eweb4j.solidbase.permission.util.PermUtil;

@Path("${PermissionConstant.MODEL_NAME}")
public class EditPermissionAction extends PermissionBaseAction {

	@Path("/{permId}/edit")
	@GET
	@POST
	public String doEdit(Map model) {
		try {
			EditPage<Permission> editPage = permService.getEditPage(permId);
			model.put("editPage", editPage);
			Code httpType = codeService.getAndCreateCodeByCodeValue(PermissionCons.HTTP_METHOD_CODE_VALUE());
			model.put("httpMethodList",codeService.queryByCodeTypeIdAndParentId(httpType.getCodeId(), 0, -1, -1).getPojos());
			Code permType = codeService.getAndCreateCodeByCodeValue(PermissionCons.PERMISSION_TYPE_CODE_VALUE());
			model.put("permTypes",codeService.queryByCodeTypeIdAndParentId(permType.getCodeId(), 0, -1, -1).getPojos());

			model.put("openType", PermissionCons.OPEN_TYPE());
			
			PermUtil.setActions(permService, codeService, MVC.ctx().getRequest(),editPage.getPojo());
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return PermissionCons.EDIT_ACTION_RESULT();
	}

}
