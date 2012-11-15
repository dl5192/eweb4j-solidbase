package org.eweb4j.solidbase.department.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eweb4j.orm.dao.DAOException;
import org.eweb4j.orm.dao.DAOFactory;
import org.eweb4j.orm.dao.cascade.CascadeDAO;
import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.department.model.Department;
import org.eweb4j.solidbase.department.model.DepartmentCons;
import org.eweb4j.solidbase.department.model.DepartmentException;

public class DepartmentDAOImpl implements DepartmentDAO {

	private String dsName ;
	private final static Class<Department> clazz = Department.class;

	public void setDsName(String dsName) {
		this.dsName = dsName;
	}

	public long insert(Department department) throws DepartmentException {
		long id = -1;
		try {
			id = Long.parseLong(String.valueOf(DAOFactory.getInsertDAO(dsName).insert(department)));
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}

		return id;
	}

	public void update(Department department) throws DepartmentException {
		try {
			DAOFactory.getUpdateDAO(dsName).update(department);
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
	}

	public List<Department> divPage(int pageNum, int numPerPage) throws DepartmentException {
		Collection<Department> pojos = null;
		try {
			pojos = DAOFactory.getDAO(Department.class, dsName).selectAll().query(pageNum, numPerPage);
			
			if (pojos == null) return null;
			
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
		
		return new ArrayList<Department>(pojos);
	}

	public long countAll() throws DepartmentException {
		long count;

		try {
			count = DAOFactory.getDAO(clazz, dsName).count();
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}

		return count;
	}

	public void delete(long departId) throws DepartmentException {
		try {
			DAOFactory.getDeleteDAO(dsName).deleteById(Department.class, departId);
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Department selectOneByDepartId(long departId) throws DepartmentException {
		Department department = null;
		try {
			department = DAOFactory.getDAO(clazz, dsName).selectAll().where().field("departId").equal(departId).queryOne();
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}

		return department;
	}

	public void cascadeSelect(Department... departments) throws DepartmentException {
		try {
			CascadeDAO cascadeDAO = DAOFactory.getCascadeDAO(dsName);
			for (Department department : departments) {
				cascadeDAO.select(department);
				cascadeDAO.select(department.getCode());
			}
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
	}

	public List<Code> joinCodeSelectByCodeTypeId(long codeTypeId) throws DepartmentException {
		// select * from t_code c, t_department d where c.type_id = {} and d.code.id = c.id order by {} asc ; 
		Collection<Code> codes = null;
		try {
			codes = DAOFactory
					.getDAO(Department.class, dsName)
					.alias("d")
					.join("code", "c")
					.select(Code.class)
					.where()
						.field("c.type").equal(codeTypeId)
						.enableExpress(true)
						.and("d.code").equal("c.codeId")
						.enableExpress(false)
					.asc("c.codeValue")
					.query();
			
			if (codes == null) return null;
			
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}

		return new ArrayList<Code>(codes);
	}

	public List<Department> selectDepartmentByParentId(long parentId, final long departTypeId) throws DepartmentException {
		Collection<Department> pojos = null;
		// select * from t_dept d where d.code.id in (select c.id from t_code c where c.parent.id = {} and c.type.id = {}) 
		try {
			String sql = 
				DAOFactory
				.getDAO(Code.class, dsName)
				.select("codeId")
				.where()
					.field("parent").equal(parentId)
					.and("type").equal(departTypeId)
				.toSql();
			
			pojos = 
				DAOFactory
				.getDAO(Department.class, dsName)
				.selectAll()
				.where()
					.field("code").inSql(sql)
				.query();
			
			if (pojos == null) return null;
			
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
		
		return new ArrayList<Department>(pojos);
	}

}
