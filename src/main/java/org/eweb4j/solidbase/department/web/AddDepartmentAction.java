package org.eweb4j.solidbase.department.web;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.department.model.Department;
import org.eweb4j.solidbase.department.model.DepartmentCons;
import org.eweb4j.solidbase.department.model.DepartmentException;

@Path("${DepartmentConstant.MODEL_NAME}")
public class AddDepartmentAction extends DepartmentBaseAction {

	private Department department;

	@Path("/")
	@POST
	public String doPost() {
		try {
			departService.createDepartInfo(department);
			return DepartmentCons.DWZ_SUCCESS_JSON("添加部门信息成功");
		} catch (DepartmentException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
