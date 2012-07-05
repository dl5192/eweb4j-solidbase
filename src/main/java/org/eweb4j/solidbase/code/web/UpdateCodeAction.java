package org.eweb4j.solidbase.code.web;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eweb4j.mvc.validator.annotation.Int;
import org.eweb4j.mvc.validator.annotation.Required;
import org.eweb4j.mvc.validator.annotation.Size;
import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.code.model.CodeCons;
import org.eweb4j.solidbase.code.model.CodeException;

@Path("${CodeConstant.MODEL_NAME}")
public class UpdateCodeAction extends CodeBaseAction {

	private Code code;

	@Required
	@Int
	@Size(min = 1)
	private long editCodeId;

	@PUT
	@Path("/{editCodeId}")
	public String doUpdate(@QueryParam("codeParents.dwz_codeParent.codeId") long parentId) {
		try {
			if (parentId <= 0)
				parentId = CodeCons.TOP_CODE_ID();

			Code parent = new Code();
			parent.setCodeId(parentId);
			code.setParent(parent);
			code.setCodeId(editCodeId);

			service.updateCodeInfo(code);
			return CodeCons.DWZ_SUCCESS_JSON("更新代码信息成功");
		} catch (CodeException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public void setEditCodeId(long editCodeId) {
		this.editCodeId = editCodeId;
	}

}
