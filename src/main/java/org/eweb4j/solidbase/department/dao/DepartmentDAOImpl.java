package org.eweb4j.solidbase.department.dao;

import java.util.Collection;

import org.eweb4j.orm.Db;
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
		try {
			Db.ar(department, dsName).create();
			return department.getDepartId();
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void update(Department department) throws DepartmentException {
		try {
			Db.ar(department,dsName).save();
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Collection<Department> divPage(int pageNum, int numPerPage) throws DepartmentException {
		try {
			return Db.ar(clazz,dsName).find().fetch(pageNum, numPerPage);
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
	}

	public long countAll() throws DepartmentException {
		try {
			return Db.ar(clazz, dsName).count();
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void batchDelete(Department... departments) throws DepartmentException {
		try {
			Db.batchDelete(departments, dsName);
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
	}
	
	public void delete(long departId) throws DepartmentException {
		try {
			Db.ar(clazz, dsName).delete("byDepartId", departId);
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Department selectOneByDepartId(long departId) throws DepartmentException {
		try {
			return Db.ar(clazz, dsName).findById(departId);
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
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

	public Collection<Code> joinCodeSelectByCodeTypeId(long codeTypeId) throws DepartmentException {
		// select * from t_code c, t_department d where c.type_id = {} and d.code.id = c.id order by {} asc ; 
		try {
			return DAOFactory.getDAO(Department.class, dsName)
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
			
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Collection<Department> selectDepartmentByParentId(long parentId, final long departTypeId) throws DepartmentException {
		// select * from t_dept d where d.code.id in (select c.id from t_code c where c.parent.id = {} and c.type.id = {}) 
		try {
			String sql = DAOFactory.getDAO(Code.class, dsName)
				.select("codeId")
				.where()
					.field("parent").equal(parentId)
					.and("type").equal(departTypeId)
				.toSql();
			
			return DAOFactory.getDAO(Department.class, dsName)
				.selectAll()
				.where()
					.field("code").inSql(sql)
				.query();
			
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
	}

}
