package org.eweb4j.solidbase.department.model;

import java.util.List;

import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.solidbase.code.model.Code;

public interface DepartmentService {

	void createDepartInfo(Department department) throws DepartmentException;

	PageMod<Department> getPageDepartInfo(int pageNum, int numPerPage)
			throws DepartmentException;

	void removeDepartInfo(long departId) throws DepartmentException;

	void batchRemoveDepartInfo(long[] ids) throws DepartmentException;

	List<Code> queryParentDeparts() throws DepartmentException;

	void updateDepartInfo(Department department) throws DepartmentException;

	EditPage<Department> getEditPage(long departId) throws DepartmentException;

	List<Department> getTopDepartments() throws DepartmentException;

	List<Department> getSubDepartments(long parentId) throws DepartmentException;

	String getDepartmentDWZTree(List<Department> departments, String ulStyle,
			String ulOncheckHandler) throws DepartmentException;
}
