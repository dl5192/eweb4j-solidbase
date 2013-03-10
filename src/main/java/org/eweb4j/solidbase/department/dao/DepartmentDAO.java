package org.eweb4j.solidbase.department.dao;

import java.util.Collection;

import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.department.model.Department;
import org.eweb4j.solidbase.department.model.DepartmentException;

public interface DepartmentDAO {
	
	public long insert(Department department) throws DepartmentException;

	public Collection<Department> divPage(int pageNum, int numPerPage) throws DepartmentException;

	public long countAll() throws DepartmentException;

	public void batchDelete(Department... departments) throws DepartmentException;
	
	public void delete(long departId) throws DepartmentException;

	public void cascadeSelect(Department... department) throws DepartmentException;

	public Department selectOneByDepartId(long departId) throws DepartmentException;

	public Collection<Code> joinCodeSelectByCodeTypeId(long codeTypeId) throws DepartmentException;

	public void update(Department department) throws DepartmentException;

	public Collection<Department> selectDepartmentByParentId(long parentId, final long departTypeId) throws DepartmentException;
}
