package org.eweb4j.solidbase.user.dao;

import java.util.List;

import org.eweb4j.solidbase.user.model.User;


public interface UserDAO {
	
	public User getOneByAccAndPwd(User user);

	public void updateLoginStatus(User user);

	public void insert(User user);
	
	public void cascadeInsert(User user, String... fields);
	
	public void cascadeSelect(User[] users, String... fields);

	public User getOneByAccount(String account);

	public User getOtherByAccount(long id, String account);

	public User getOne(long id);

	public void removeOne(long id);

	public void update(User user, String... fields);

	public List<User> getPage(int pageNum, int numPerPage);

	public long countAll();

	public List<User> getSearchResult(String keyword, int pageNum,int numPerPage);

	public long countSearch(String keyword);

	public void updateByFields(User user, String... fields);

	public void cascadeDelete(User user, String... fields);

	public void batchRemove(User... users);
}
