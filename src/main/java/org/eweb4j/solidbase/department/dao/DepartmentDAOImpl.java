package org.eweb4j.solidbase.department.dao;

import java.util.List;

import org.eweb4j.orm.dao.DAO;
import org.eweb4j.orm.dao.DAOException;
import org.eweb4j.orm.dao.DAOFactory;
import org.eweb4j.orm.dao.cascade.CascadeDAO;
import org.eweb4j.orm.dao.delete.DeleteDAO;
import org.eweb4j.orm.dao.insert.InsertDAO;
import org.eweb4j.orm.dao.select.DivPageDAO;
import org.eweb4j.orm.dao.select.SelectDAO;
import org.eweb4j.orm.dao.update.UpdateDAO;
import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.department.model.Department;
import org.eweb4j.solidbase.department.model.DepartmentCons;
import org.eweb4j.solidbase.department.model.DepartmentException;

public class DepartmentDAOImpl implements DepartmentDAO {

	private InsertDAO insertDAO;
	private DivPageDAO divPageDAO;
	private SelectDAO selectDAO;
	private DeleteDAO deleteDAO;
	private UpdateDAO updateDAO;
	private DAO departDAO;
	private DAO codeDAO;
	private CascadeDAO cascadeDAO;
	private final static Class<Department> clazz = Department.class;

	public void setDsName(String dsName) {
		insertDAO = DAOFactory.getInsertDAO(dsName);
		divPageDAO = DAOFactory.getDivPageDAO(dsName);
		selectDAO = DAOFactory.getSelectDAO(dsName);
		deleteDAO = DAOFactory.getDeleteDAO(dsName);
		updateDAO = DAOFactory.getUpdateDAO(dsName);
		codeDAO = DAOFactory.getDAO(Code.class, dsName);
		departDAO = DAOFactory.getDAO(clazz, dsName);
		cascadeDAO = DAOFactory.getCascadeDAO(dsName);
	}

	public long insert(Department department) throws DepartmentException {
		long id = -1;
		try {
			id = Long.parseLong(String.valueOf(insertDAO.insert(department)));
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}

		return id;
	}

	public void update(Department department) throws DepartmentException {
		try {
			updateDAO.update(department);
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
	}

	public List<Department> divPage(int pageNum, int numPerPage)
			throws DepartmentException {
		List<Department> pojos = null;
		try {
			pojos = divPageDAO.divPage(clazz, pageNum, numPerPage);

		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
		return pojos;
	}

	public long countAll() throws DepartmentException {
		long count;

		try {
			count = selectDAO.selectCount(clazz);
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}

		return count;
	}

	public void delete(long departId) throws DepartmentException {
		try {
			deleteDAO.deleteById(clazz, departId);
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Department selectOneByDepartId(long departId)
			throws DepartmentException {
		Department department = null;
		try {
			department = selectDAO.selectOneById(clazz, departId);
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}

		return department;
	}

	public void cascadeSelect(Department... departments)
			throws DepartmentException {
		try {
			for (Department department : departments) {
				cascadeDAO.select(department);
				cascadeDAO.select(department.getCode());
			}
		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}
	}

	public List<Code> joinCodeSelectByCodeTypeId(long codeTypeId)
			throws DepartmentException {
		List<Code> pojos = null;
		try {
			codeDAO.clear();
			departDAO.clear();
			pojos = codeDAO
					.select(new String[] { "codeId", "codeValue", "remark" })
					.where().field("type").equal(codeTypeId).and("codeId")
					.inSql(departDAO.select(new String[] { "code" }).toSql())
					.asc("codeValue").query();

		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}

		return pojos;
	}

	public List<Department> selectDepartmentByParentId(long parentId,
			final long departTypeId) throws DepartmentException {
		List<Department> pojos = null;
		try {
			departDAO.clear();
			codeDAO.clear();
			pojos = departDAO
					.selectAll()
					.where()
					.field("code")
					.inSql(codeDAO.select(new String[] { "codeId" }).where()
							.field("parent").equal(parentId).and("type")
							.equal(departTypeId).toSql()).query();

		} catch (DAOException e) {
			throw new DepartmentException(DepartmentCons.DATA_ACCESS_ERR(), e);
		}

		return pojos;
	}

}
