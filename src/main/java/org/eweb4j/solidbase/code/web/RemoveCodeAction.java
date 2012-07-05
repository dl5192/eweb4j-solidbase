package org.eweb4j.solidbase.code.web;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

import org.eweb4j.mvc.validator.annotation.Int;
import org.eweb4j.mvc.validator.annotation.Required;
import org.eweb4j.mvc.validator.annotation.Size;
import org.eweb4j.solidbase.code.model.CodeCons;
import org.eweb4j.solidbase.code.model.CodeException;

@Path("${CodeConstant.MODEL_NAME}")
public class RemoveCodeAction extends CodeBaseAction {
	@Required(mess = "请选择要删除的记录")
	@Int
	@Size(min = 1)
	private long codeId;

	@DELETE
	@Path("/{codeId}")
	public String doDelete() {
		try {
			service.removeOneCodeInfo(codeId);
			return CodeCons.DWZ_SUCCESS_JSON("删除代码信息成功");
		} catch (CodeException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	public void setCodeId(long codeId) {
		this.codeId = codeId;
	}

}
