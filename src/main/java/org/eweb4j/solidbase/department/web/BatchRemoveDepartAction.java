package org.eweb4j.solidbase.department.web;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.department.model.DepartmentCons;
import org.eweb4j.solidbase.department.model.DepartmentException;

@Path("${DepartmentConstant.MODEL_NAME}")
public class BatchRemoveDepartAction extends DepartmentBaseAction {

	private long[] ids;

	@Path("/batchRemove")
	@DELETE
	public String doBatchDelete() {
		try {
			departService.batchRemoveDepartInfo(ids);
			return DepartmentCons.DWZ_SUCCESS_JSON("批量删除部门信息成功");
		} catch (DepartmentException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	public void setIds(long[] ids) {
		this.ids = ids;
	}

}
