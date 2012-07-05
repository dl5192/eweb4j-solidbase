package org.eweb4j.solidbase.code.web;

import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.ioc.IOC;
import org.eweb4j.mvc.validator.annotation.Int;
import org.eweb4j.mvc.validator.annotation.Size;
import org.eweb4j.solidbase.code.model.CodeCons;
import org.eweb4j.solidbase.code.model.CodeService;

public abstract class CodeBaseAction {
	protected CodeService service = IOC.getBean(CodeCons.IOC_SERVICE_BEAN_ID());
	protected DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());
	
	@Int
	protected long codeTypeId = -1;

	@Int
	protected long parentId = -1;

	@Int
	@Size(min = -1)
	protected int pageNum = 1;
	@Int
	@Size(min = -1)
	protected int numPerPage = 20;

	public void setCodeTypeId(long codeTypeId) {
		this.codeTypeId = codeTypeId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
}
