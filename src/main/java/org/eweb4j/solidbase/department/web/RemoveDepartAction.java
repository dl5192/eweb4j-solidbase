package org.eweb4j.solidbase.department.web;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.department.model.DepartmentCons;
import org.eweb4j.solidbase.department.model.DepartmentException;

@Path("${DepartmentConstant.MODEL_NAME}")
public class RemoveDepartAction extends DepartmentBaseAction {

	private long departId;

	@Path("/{departId}")
	@DELETE
	public String doDelete() {
		try {
			departService.removeDepartInfo(departId);
			return DepartmentCons.DWZ_SUCCESS_JSON("删除部门信息成功");
		} catch (DepartmentException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	public void setDepartId(long departId) {
		this.departId = departId;
	}

}
