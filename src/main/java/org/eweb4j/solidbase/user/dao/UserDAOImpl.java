package org.eweb4j.solidbase.user.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eweb4j.orm.Db;
import org.eweb4j.orm.dao.DAOFactory;
import org.eweb4j.solidbase.user.model.User;

public class UserDAOImpl implements UserDAO {

	// 暂时用不到
	private String dsName;

	public UserDAOImpl() {
	}

	public void setDsName(String dsName) {
		this.dsName = dsName;
	}

	public User getOneByAccAndPwd(final User user) {
		return Db.ar(User.class).find("byAccountAndPassword", user.getAccount(), user.getPassword()).first();
	}

	public void updateLoginStatus(User user) {
		Db.ar(user).save("status", "lastLoginTime", "lastLoginIp");
	}

	public void insert(User user) {
		Db.ar(user).create();
	}

	public User getOneByAccount(final String account) {
		return Db.ar(User.class).find("byAccount", account).first();
	}

	public User getOtherByAccount(long id, String account) {
		return Db.ar(User.class).find("id <> ? and account = ?", id, account).first();
	}

	public User getOne(final long id) {
		return Db.ar(User.class).findById(id);
	}

	public void removeOne(long id) {
		User user = new User();
		user.setId(id);
		Db.ar(user).delete();
	}

	public void cascadeInsert(final User user, final String... fields) {
		DAOFactory.getCascadeDAO(dsName).insert(user, fields);
	}

	public void cascadeDelete(final User user, final String... fields) {
		DAOFactory.getCascadeDAO(dsName).delete(user, fields);
	}

	public void update(final User user, String... fields) {
		Db.ar(user).save(fields);
	}

	public List<User> getPage(int page, int length) {
		Collection<User> users = Db.ar(User.class).find().fetch(page, length);
		return new ArrayList<User>(users);
	}

	public long countAll() {
		return Db.ar(User.class).count();
	}

	public List<User> getSearchResult(String keyword, int page, int length) {
		Collection<User> users = 
			DAOFactory.getDAO(User.class, dsName)
				.fetch("roles")//cascade fetch
				.selectAll()
				.where()
					.field("account").like(keyword)
				.query(page, length);
		
		return new ArrayList<User>(users);
	}

	public long countSearch(String keyword) {
		return Db.ar(User.class).count("byAccountLike", "%" + keyword + "%");
	}

	public void updateByFields(User user, String... fields) {
		Db.ar(user).save(fields);
	}

	public void cascadeSelect(User[] users, String... fields) {
		DAOFactory.getCascadeDAO(dsName).select(users, fields);
	}

	public void batchRemove(User... users) {
		Db.batchDelete(users);
	}

}
