package org.eweb4j.solidbase.user.dao;

import java.util.List;

import org.eweb4j.orm.LikeType;
import org.eweb4j.orm.dao.DAOException;
import org.eweb4j.orm.dao.DAOFactory;
import org.eweb4j.orm.dao.cascade.CascadeDAO;
import org.eweb4j.orm.dao.delete.DeleteDAO;
import org.eweb4j.orm.dao.insert.InsertDAO;
import org.eweb4j.orm.dao.select.DivPageDAO;
import org.eweb4j.orm.dao.select.SearchDAO;
import org.eweb4j.orm.dao.select.SelectDAO;
import org.eweb4j.orm.dao.update.UpdateDAO;
import org.eweb4j.solidbase.user.model.User;
import org.eweb4j.solidbase.user.model.UserCons;


public class UserDAOImpl implements UserDAO {
	private SelectDAO selectDAO = null;
	private UpdateDAO updateDAO = null;
	private DeleteDAO deleteDAO = null;
	private InsertDAO insertDAO = null;
	private DivPageDAO divPageDAO = null;
	private SearchDAO searchDAO = null;
	private CascadeDAO cascadeDAO = null;

	public UserDAOImpl() {

	}

	public void setDsName(String dsName) {
		this.selectDAO = DAOFactory.getSelectDAO(dsName);
		this.updateDAO = DAOFactory.getUpdateDAO(dsName);
		this.deleteDAO = DAOFactory.getDeleteDAO(dsName);
		this.insertDAO = DAOFactory.getInsertDAO(dsName);
		this.divPageDAO = DAOFactory.getDivPageDAO(dsName);
		this.searchDAO = DAOFactory.getSearchDAO(dsName);
		this.cascadeDAO = DAOFactory.getCascadeDAO(dsName);
	}

	public User getOneByAccAndPwd(final User user) throws Exception {
		if (user == null)
			return null;
		User db_user = this.selectDAO.selectOne(user, "account", "password");
		this.cascadeDAO.select(db_user);

		return db_user;
	}

	public void updateLoginStatus(User user) throws Exception {
		if (user == null)
			throw new Exception(UserCons.UPDATE_FAIL());

		String[] fields = { "status", "lastLoginTime", "lastLoginIp" };
		this.updateDAO.updateByFields(user, fields);
	}

	public long insert(User user) throws Exception {
		long id = 0;
		if (user == null)
			throw new Exception(UserCons.UPDATE_FAIL());
		try {
			id = Long.parseLong(String.valueOf(this.insertDAO.insert(user) + ""));
		} catch (DAOException e) {
			throw new Exception(e);
		}

		return id;
	}

	public User getOneByAccount(final String account) throws Exception {
		if (account == null || "".equals(account.trim()))
			return null;

		String[] fields = { "account" };
		String[] values = { account };
		User user = this.selectDAO.selectOne(User.class, fields, values);
		this.cascadeDAO.select(user);

		return user;
	}

	public User getOtherByAccount(long id, String account) throws Exception {
		if (id <= 0 || account == null || "".equals(account.trim()))
			return null;

		String condition = "id <> ? and account = ?";
		List<User> users = this.selectDAO.selectWhere(User.class, condition,
				id, account);
		if (users == null)
			return null;

		User first_user = users.get(0);

		return first_user;
	}

	public User getOne(final long id) throws Exception {
		if (id <= 0)
			return null;
		User user = this.selectDAO.selectOneById(User.class, id);
		this.cascadeDAO.select(user);

		return user;
	}

	public void removeOne(long id) throws Exception {
		this.deleteDAO.deleteById(User.class, id);
	}

	public void cascadeInsert(final User user, final String... fields)
			throws Exception {
		try {
			this.cascadeDAO.insert(user, fields);
		} catch (DAOException e) {
			throw new Exception(UserCons.DATA_ACCESS_ERR());
		}
	}

	public void cascadeDelete(final User user, final String... fields)
			throws Exception {
		try {
			this.cascadeDAO.delete(user, fields);
		} catch (DAOException e) {
			throw new Exception(UserCons.DATA_ACCESS_ERR());
		}
	}

	public void update(final User user) throws Exception {
		if (user == null)
			throw new Exception(UserCons.UPDATE_FAIL());
		try {
			this.updateDAO.update(user);
		} catch (DAOException e) {
			throw new Exception(e);
		}
	}

	public List<User> getPage(int pageNum, int numPerPage) throws Exception {
		List<User> users = null;

		try {
			users = this.divPageDAO.divPage(User.class, pageNum, numPerPage);
		} catch (DAOException e) {
			throw new Exception(UserCons.DATA_ACCESS_ERR(), e);
		}

		return users;
	}

	public long countAll() throws Exception {
		return this.selectDAO.selectCount(User.class);
	}

	public List<User> getSearchResult(String keyword, int pageNum,
			int numPerPage) throws Exception {
		String[] fields = { "account" };
		String[] values = { keyword };
		boolean isOR = true;
		List<User> users = this.searchDAO.searchByLikeAndOrderByIdFieldDESC(
				User.class, fields, values, LikeType.ALL_LIKE, isOR);
		if (users != null)
			this.cascadeDAO.select(users.toArray(new User[] {}), "role");

		return users;
	}

	public long countSearch(String keyword) throws Exception {
		String condition = "account like ? ";
		String k = "'%" + keyword + "%'";
		Object[] args = { k };

		return this.selectDAO.selectCount(User.class, condition, args);
	}

	public void updateByFields(User user, String... fields) throws Exception {
		if (user == null || fields == null || fields.length == 0)
			throw new Exception(UserCons.UPDATE_FAIL());
		try {
			Number r = this.updateDAO.updateByFields(user, fields);
			if (r == null || r.equals(0))
				throw new Exception(UserCons.UPDATE_FAIL());
		} catch (DAOException e) {
			throw new Exception(e);
		}
	}

	public void cascadeSelect(User[] users, String... fields) throws Exception {
		try {
			this.cascadeDAO.select(users, fields);
		} catch (DAOException e) {
			throw new Exception(UserCons.DATA_ACCESS_ERR(), e);
		}
	}
}
