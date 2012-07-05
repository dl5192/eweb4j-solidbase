package org.eweb4j.solidbase.department.web;

import java.util.List;
import java.util.Map;

import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.ioc.IOC;
import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.code.model.CodeCons;
import org.eweb4j.solidbase.code.model.CodeService;
import org.eweb4j.solidbase.department.model.DepartmentCons;
import org.eweb4j.solidbase.department.model.DepartmentService;


public abstract class DepartCodeSelecter {
	protected DepartmentService departService = IOC.getBean(DepartmentCons.IOC_SERVICE_BEAN_ID());
	protected DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());

	protected CodeService codeService = IOC.getBean(CodeCons.IOC_SERVICE_BEAN_ID());

	protected List<Code> levels; // 部门级别
	protected List<Code> parents; // 上级部门
	protected List<Code> cates; // 部门类型
	protected List<Code> policeKinds; // 警种

	protected void doSetDateModel(Map model) throws Exception {
		Code levelType = codeService.getAndCreateCodeByCodeValue(DepartmentCons.LEVEL_TYPE_CODE_VALUE());

		levels = codeService.queryByCodeTypeIdAndParentId(levelType.getCodeId(), -1, -1, -1).getPojos();
		model.put("levels", levels);

		parents = departService.queryParentDeparts();
		model.put("parents", parents);

		Code cateType = codeService.getAndCreateCodeByCodeValue(DepartmentCons.DEPARTMENT_CATE_TYPE_CODE_VALUE());
		cates = codeService.queryByCodeTypeIdAndParentId(cateType.getCodeId(), -1, -1, -1).getPojos();

		model.put("cates", cates);
	}

}
