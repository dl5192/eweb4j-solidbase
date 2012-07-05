package org.eweb4j.solidbase.department.web;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.department.model.Department;
import org.eweb4j.solidbase.department.model.DepartmentCons;
import org.eweb4j.solidbase.department.model.DepartmentException;

@Path("${DepartmentConstant.MODEL_NAME}")
public class UpdateDepartmentAction extends DepartmentBaseAction {

	private Department department;
	private long departId;

	@Path("/{departId}")
	@PUT
	public String doPut() {
		try {
			department.setDepartId(departId);
			departService.updateDepartInfo(department);
			return DepartmentCons.DWZ_SUCCESS_JSON("更新部门信息成功");
		} catch (DepartmentException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setDepartId(long departId) {
		this.departId = departId;
	}

}
