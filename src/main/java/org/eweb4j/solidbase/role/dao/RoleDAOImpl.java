package org.eweb4j.solidbase.role.dao;

import java.util.List;

import org.eweb4j.orm.LikeType;
import org.eweb4j.orm.OrderType;
import org.eweb4j.orm.dao.DAOException;
import org.eweb4j.orm.dao.DAOFactory;
import org.eweb4j.orm.dao.cascade.CascadeDAO;
import org.eweb4j.orm.dao.delete.DeleteDAO;
import org.eweb4j.orm.dao.insert.InsertDAO;
import org.eweb4j.orm.dao.select.DivPageDAO;
import org.eweb4j.orm.dao.select.SelectDAO;
import org.eweb4j.orm.dao.update.UpdateDAO;
import org.eweb4j.solidbase.role.model.Role;
import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.role.model.RoleException;


public class RoleDAOImpl implements RoleDAO {
	private SelectDAO selectDAO = null;
	private UpdateDAO updateDAO = null;
	private DeleteDAO deleteDAO = null;
	private DivPageDAO divPageDAO = null;
	private InsertDAO insertDAO = null;
	private CascadeDAO cascadeDAO = null;

	public void setDsName(String dsName) {
		this.selectDAO = DAOFactory.getSelectDAO(dsName);
		this.updateDAO = DAOFactory.getUpdateDAO(dsName);
		this.deleteDAO = DAOFactory.getDeleteDAO(dsName);
		this.divPageDAO = DAOFactory.getDivPageDAO(dsName);
		this.insertDAO = DAOFactory.getInsertDAO(dsName);
		this.cascadeDAO = DAOFactory.getCascadeDAO(dsName);
	}

	public void insert(Role role) throws RoleException {
		try {
			this.insertDAO.insert(role);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void deleteById(long id) throws RoleException {
		try {
			Role role = new Role();
			role.setRoleId(id);
			this.deleteDAO.deleteById(role);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void update(Role role) throws RoleException {
		try {
			this.updateDAO.update(role);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Role selectById(long id) throws RoleException {
		try {
			Role role = new Role();
			role.setRoleId(id);
			return this.selectDAO.selectOneById(role);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Role selectByName(String name) throws RoleException {
		try {
			String[] fields = { "name" };
			String[] values = { name };
			return this.selectDAO.selectOne(Role.class, fields, values);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public List<Role> selectAll() throws RoleException {
		try {
			return this.selectDAO.selectAll(Role.class);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public List<Role> divPage(int p, int n) throws RoleException {
		try {
			return this.divPageDAO.divPage(Role.class, p, n);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public long countAll() throws RoleException {
		try {
			return this.selectDAO.selectCount(Role.class);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public long countByLike(String keyword) throws RoleException {
		try {
			String condition = "name like ? ";
			Object[] args = { "%" + keyword + "%" };
			return this.selectDAO.selectCount(Role.class, condition, args);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public List<Role> selectByLike(String keyword, int pageNum, int numPerPage)
			throws RoleException {
		try {
			Role role = new Role();
			role.setName(keyword);
			String[] fields = { "name" };
			int likeType = LikeType.ALL_LIKE;
			int orderType = OrderType.DESC_ORDER;
			return this.divPageDAO.divPageByFieldLikeValue(role, fields,
					likeType, orderType, pageNum, numPerPage, true);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void cascadeDelete(Role role, String... fields) throws RoleException {
		try {
			cascadeDAO.delete(role, fields);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void cascadeInsert(Role role, String... fields) throws RoleException {
		try {
			cascadeDAO.insert(role, fields);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void cascadeSelect(Role role,String... fields) throws RoleException {
		try{
			cascadeDAO.select(role, fields);
		}catch(DAOException e){
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}
}
