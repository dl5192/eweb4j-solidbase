package org.eweb4j.solidbase.code.web;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.mvc.validator.annotation.Int;
import org.eweb4j.mvc.validator.annotation.Required;
import org.eweb4j.mvc.validator.annotation.Size;
import org.eweb4j.solidbase.code.model.CodeCons;
import org.eweb4j.solidbase.code.model.CodeException;

@Path("${CodeConstant.MODEL_NAME}")
public class EditCodeAction extends CodeBaseAction {
	@Required(mess = "请选择要编辑的记录")
	@Int()
	@Size(min = 1)
	private long codeId;

	@GET
	@POST
	@Path("/{codeId}/edit")
	public String doEdit(Map<String, Object> model) {
		try {
			model.put("openType", CodeCons.OPEN_TYPE());
			model.put("editPage", service.getEditPage(codeId));
		} catch (CodeException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return CodeCons.EDIT_ACTION_RESULT();
	}

	public void setCodeId(long codeId) {
		this.codeId = codeId;
	}

}
