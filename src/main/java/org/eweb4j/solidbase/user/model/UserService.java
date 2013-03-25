package org.eweb4j.solidbase.user.model;

import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.mvc.view.PageMod;

public interface UserService {
	/**
	 * 登陆
	 * 
	 * @param sessionAuthcode
	 *            session保存的验证码
	 * @param ip
	 *            用户ip
	 * @param user
	 *            包含用户填写的账号密码
	 * @return
	 */
	public User login(String session_authcode, String ip, User user) throws Exception;

	/**
	 * 注册
	 * 
	 * @param sessionAuthcode
	 *            session保存的验证码
	 * @param user
	 *            包含用户账号密码
	 */
	public void register(String sessionAuthcode, User user) throws Exception;

	/**
	 * 批量删除用户信息
	 * 
	 * @param ids
	 *            要删除记录的ID数组
	 */
	public void batchRemove(Long[] ids) throws Exception;

	/**
	 * 删除用户信息
	 * 
	 * @param id
	 *            要删除用户的数据库记录ID
	 */
	public void removeOne(Long id) throws Exception;

	/**
	 * 管理员后台添加用户信息
	 * 
	 * @param user
	 *            包含了输入的用户信息
	 */
	public void add(User user) throws Exception;

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 *            包含了要更新的用户信息
	 */
	public void update(User user) throws Exception;

	/**
	 * 返回某个用户信息的编辑页面
	 * 
	 * @param id
	 * @return
	 */
	public EditPage<User> getEditPage(Long id) throws Exception;

	/**
	 * 分页返回用户信息列表
	 * 
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	public PageMod<User> getListPage(int pageNum, int numPerPage)
			throws Exception;

	/**
	 * 关键字查询，分页
	 * 
	 * @param keyword
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 */
	public PageMod<User> getSearchResult(String keyword, int pageNum, int numPerPage) throws Exception;

	/**
	 * 锁定用户账号，使其账号无法登陆
	 * 
	 * @param id
	 *            要锁定的用户的数据库记录ID
	 */
	public void lock(Long id) throws Exception;

	/**
	 * 批量锁定用户账号，使其账号无法登陆
	 * 
	 * @param ids
	 *            要锁定的用户的数据库记录ID数组
	 */
	public void batchLock(Long[] ids) throws Exception;

	/**
	 * 解锁用户账号，使其账号恢复正常使用
	 * 
	 * @param id
	 *            要解锁的用户的数据库记录ID
	 */
	public void unLock(Long id) throws Exception;

	/**
	 * 解锁用户账号，使其账号恢复正常使用
	 * 
	 * @param ids
	 *            要解锁的用户的数据库记录ID数组
	 */
	public void batchUnLock(Long[] ids) throws Exception;

	/**
	 * 添加用户角色关联关系
	 * 
	 * @param userIds
	 * @param roleIds
	 * @throws Exception
	 */
	public void addUserRoleRelation(long[] userIds, long[] roleIds)
			throws Exception;

	/**
	 * 添加用户部门关联关系
	 * 
	 * @param userIds
	 * @param departmentIds
	 * @throws Exception
	 */
	public void addUserDepartmentRelation(long[] userIds, long[] departmentIds)
			throws Exception;

	/**
	 * 
	 * @param users
	 * @throws Exception
	 */
	public void cascadeUser(User[] users) throws Exception;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User queryUserById(long id) throws Exception;

	/**
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void deleteUserRoleRelation(long userId, long[] roleIds)
			throws Exception;

	public void deleteUserDepartmentRelation(long userId, long[] departmentIds)
			throws Exception;
}
