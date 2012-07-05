package org.eweb4j.solidbase.permission.dao;

import java.util.List;
import java.util.Map;

import org.eweb4j.orm.dao.DAO;
import org.eweb4j.orm.dao.DAOException;
import org.eweb4j.orm.dao.DAOFactory;
import org.eweb4j.orm.dao.cascade.CascadeDAO;
import org.eweb4j.orm.dao.delete.DeleteDAO;
import org.eweb4j.orm.dao.insert.InsertDAO;
import org.eweb4j.orm.dao.select.DivPageDAO;
import org.eweb4j.orm.dao.select.SearchDAO;
import org.eweb4j.orm.dao.select.SelectDAO;
import org.eweb4j.orm.dao.update.UpdateDAO;
import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.permission.model.Permission;
import org.eweb4j.solidbase.permission.model.PermissionCons;

public class PermissionDAOImpl implements PermissionDAO {

	private final static Class<Permission> clazz = Permission.class;

	private DivPageDAO divPageDAO = null;
	private SelectDAO selectDAO = null;
	private DeleteDAO deleteDAO = null;
	private InsertDAO insertDAO = null;
	private UpdateDAO updateDAO = null;
	private CascadeDAO cascadeDAO = null;
	private SearchDAO searchDAO = null;
	private DAO dao = null;
	private DAO mapDAO = null;

	public void setDsName(String dsName) {
		divPageDAO = DAOFactory.getDivPageDAO(dsName);
		selectDAO = DAOFactory.getSelectDAO(dsName);
		deleteDAO = DAOFactory.getDeleteDAO(dsName);
		insertDAO = DAOFactory.getInsertDAO(dsName);
		updateDAO = DAOFactory.getUpdateDAO(dsName);
		cascadeDAO = DAOFactory.getCascadeDAO(dsName);
		searchDAO = DAOFactory.getSearchDAO(dsName);
		dao = DAOFactory.getDAO(clazz, dsName);
		mapDAO = DAOFactory.getDAO(Map.class, dsName);
		mapDAO.setTable("t_perm_http_method");
	}

	public long insert(Permission permission) throws Exception {
		long id = -1;
		try {
			id = Long.parseLong(String.valueOf(insertDAO.insert(permission)));
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}

		return id;
	}

	public Permission selectOneById(long permId) throws Exception {
		Permission permission = null;
		try {
			permission = selectDAO.selectOneById(clazz, permId);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}

		return permission;
	}

	public void cascadeSelect(Permission[] permissions, String... fields)
			throws Exception {
		try {
			cascadeDAO.select(permissions, fields);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public List<Permission> divPage(int pageNum, int numPerPage)
			throws Exception {
		List<Permission> pojos = null;
		try {
			pojos = divPageDAO.divPage(clazz, pageNum, numPerPage);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}

		return pojos;
	}

	public long countAll() throws Exception {
		long count = 0;
		try {
			count = selectDAO.selectCount(clazz);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
		return count;
	}

	public void update(Permission permission) throws Exception {
		try {
			updateDAO.update(permission);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void delete(long permId) throws Exception {
		try {
			deleteDAO.deleteById(clazz, permId);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Permission selectOneByName(String name) throws Exception {
		Permission pojo = null;
		try {
			Permission perm = new Permission();
			perm.setName(name);
			pojo = selectDAO.selectOne(perm, "name");
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}

		return pojo;
	}

	public Permission selectOneByResourceAndHttpMethod(long resId, long[] httpMethods)
			throws Exception {
		Permission pojo = null;
		try {

			/**
			 * select * from t_permission where id in (SELECT perm_id FROM
			 * t_perm_http_method WHERE perm_id in (select id from t_permission
			 * where resource_id = {resId} ) and http_method in('529', '530'))
			 */
			dao.clear();
			String selectPermByUriSql = dao.select(new String[] { "permId" })
					.where().field("resource").equal(resId).toSql();

			mapDAO.clear();
			Long[] httpMethodIds = new Long[httpMethods.length];
			for (int i = 0; i < httpMethods.length; i++)
				httpMethodIds[i] = httpMethods[i];

			String selectPermByIdAndHttpMethodSql = mapDAO
					.select(new String[] { "perm_id" }).where()
					.field("perm_id").inSql(selectPermByUriSql)
					.and("http_method").in(httpMethodIds).toSql();

			dao.clear();
			pojo = dao.selectAll().where().field("permId")
					.inSql(selectPermByIdAndHttpMethodSql).queryOne();

		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}

		return pojo;
	}

	public List<Permission> selectByTypeId(long permTypeId) throws Exception {
		List<Permission> pojos = null;
		try {
			Code type = new Code();
			type.setCodeId(permTypeId);
			Permission perm = new Permission();
			perm.setType(type);
			String[] fields = { "type" };
			boolean isOR = false;
			pojos = searchDAO.searchByExactAndOrderByIdFieldDESC(perm, fields,
					isOR);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}

		return pojos;
	}

	public List<Permission> selectByResource(long resId) throws Exception {
		try {
			String[] fields = { "resource" };
			String[] values = { String.valueOf(resId) };
			boolean isOR = false;
			return searchDAO.searchByExactAndOrderByIdFieldDESC(clazz, fields,
					values, isOR);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Map<String, Object> selectRelTableData(long permId, long codeId)
			throws Exception {
		try {
			mapDAO.clear();
			Map<String, Object> map = mapDAO.selectAll().where()
					.field("perm_id").equal(permId).and("http_method")
					.equal(codeId).queryOne();

			return map;
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void cascadeInsert(Permission permission, String... fields)
			throws Exception {
		try {
			this.cascadeDAO.insert(permission, fields);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void cascadeDelete(Permission permission, String... fields)
			throws Exception {
		try {
			this.cascadeDAO.delete(permission, fields);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

}
