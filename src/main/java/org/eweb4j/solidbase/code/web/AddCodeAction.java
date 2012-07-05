package org.eweb4j.solidbase.code.web;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.code.model.CodeCons;
import org.eweb4j.solidbase.code.model.CodeException;

@Path("${CodeConstant.MODEL_NAME}")
public class AddCodeAction extends CodeBaseAction {

	private Code code;

	@POST
	@Path("/")
	public String doAdd(@QueryParam("codeTypes.dwz_codeType.codeId") long codeTypeId, @QueryParam("codeParents.dwz_codeParent.codeId") long parentId) {
		try {
			if (parentId <= 0)
				parentId = CodeCons.TOP_CODE_ID();
			
			if (codeTypeId <= 0)
				codeTypeId = CodeCons.META_CODE_ID();

			service.addCodeInfo(codeTypeId, parentId, code);

			return CodeCons.DWZ_SUCCESS_JSON("添加代码信息成功");
		} catch (CodeException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	public void setCode(Code code) {
		this.code = code;
	}

}
