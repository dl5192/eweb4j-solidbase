package org.eweb4j.solidbase.user.dao;

import java.util.List;

import org.eweb4j.solidbase.user.model.User;


public interface UserDAO {
	public User getOneByAccAndPwd(User user) throws Exception;

	public void updateLoginStatus(User user) throws Exception;

	public long insert(User user) throws Exception;
	
	public void cascadeInsert(User user, String... fields) throws Exception;
	
	public void cascadeSelect(User[] users, String... fields) throws Exception;

	public User getOneByAccount(String account) throws Exception;

	public User getOtherByAccount(long id, String account)
			throws Exception;

	public User getOne(long id) throws Exception;

	public void removeOne(long id) throws Exception;

	public void update(User user) throws Exception;

	public List<User> getPage(int pageNum, int numPerPage)
			throws Exception;

	public long countAll() throws Exception;

	public List<User> getSearchResult(String keyword, int pageNum,
			int numPerPage) throws Exception;

	public long countSearch(String keyword) throws Exception;

	public void updateByFields(User user, String... fields)
			throws Exception;

	public void cascadeDelete(User user, String... fields) throws Exception;
}
