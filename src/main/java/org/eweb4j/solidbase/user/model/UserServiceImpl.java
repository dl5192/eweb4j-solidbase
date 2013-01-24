package org.eweb4j.solidbase.user.model;

import java.util.List;

import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.orm.jdbc.transaction.Trans;
import org.eweb4j.orm.jdbc.transaction.Transaction;
import org.eweb4j.solidbase.department.dao.DepartmentDAO;
import org.eweb4j.solidbase.department.model.Department;
import org.eweb4j.solidbase.department.model.DepartmentCons;
import org.eweb4j.solidbase.role.dao.RoleDAO;
import org.eweb4j.solidbase.role.model.Role;
import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.setting.Setting;
import org.eweb4j.solidbase.user.dao.UserDAO;
import org.eweb4j.solidbase.user.util.UserUtil;
import org.eweb4j.util.CommonUtil;
import org.eweb4j.util.MD5Util;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO = null;
	private RoleDAO roleDAO = null;
	private DepartmentDAO departmentDAO = null;

	// 由IOC注入
	public void setUserDAO(UserDAO userDAO) {
		if (this.userDAO == null)
			this.userDAO = userDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		if (this.roleDAO == null)
			this.roleDAO = roleDAO;
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		if (this.departmentDAO == null)
			this.departmentDAO = departmentDAO;
	}

	public User login(String sessionAuthcode, String ip, User user)
			throws Exception {
		String authcode = user.getAuthcode();
		if (authcode == null || sessionAuthcode == null|| !authcode.equalsIgnoreCase(sessionAuthcode))
			throw new Exception(UserCons.INCONRECT_AUTH_CODE());

		user.setPassword(MD5Util.getMD5(user.getPassword()));
		User db_User = userDAO.getOneByAccAndPwd(user);

		// 账号或密码错误
		if (db_User == null || !user.getAccount().equals(db_User.getAccount()) || !user.getPassword().equals(db_User.getPassword()))
			throw new Exception(UserCons.INCORECT_PWD_OR_ACC());

		// 账号被锁定
		if (UserCons.LOCK().equals(db_User.getStatus()))
			throw new Exception(UserCons.ACC_LOCKED());

		// 帐号失效
		if (!UserUtil.isValid(db_User)) {
			throw new Exception(UserCons.ACC_INVALID());
		}

		db_User.setLastLoginIp(ip);
		db_User.setLastLoginTime(CommonUtil.getNowTime());
		db_User.setStatus(UserCons.NORMAL());
		userDAO.updateLoginStatus(db_User);

		return db_User;
	}

	public void register(final String sessionAuthcode, final User user)
			throws Exception {
		String authcode = user.getAuthcode();

		if (authcode == null || sessionAuthcode == null || !authcode.equalsIgnoreCase(sessionAuthcode))
			throw new Exception(UserCons.INCONRECT_AUTH_CODE());

		String rePwd = user.getRePwd();
		if (rePwd == null || !rePwd.equals(user.getPassword()))
			throw new Exception(UserCons.INCORECT_REPEAT_PWD());

		User tmp_User = userDAO.getOneByAccount(user.getAccount());
		if (tmp_User != null)
			throw new Exception(UserCons.DUPLICATE_ACC());

		userRoleAndDepartLogic(user);

		String now = CommonUtil.getNowTime();
		user.setPassword(MD5Util.getMD5(user.getPassword()));
		user.setRegTime(now);
		user.setStatus(UserCons.NORMAL());
		user.setAddTime(now);
		user.setModifyTime(now);
		
		Setting setting = Setting.inst.find().first();
		if (setting != null){
			user.setSuperPower(setting.getUserPermControl());
			user.getRoles().add(setting.getUserDefaultRole());
		}

		Transaction.execute(new Trans() {
			public void run(Object... args) throws Exception {
				userDAO.insert(user);
				userDAO.cascadeInsert(user);
			}
		});

	}

	private void userRoleAndDepartLogic(final User user) throws Exception {
		List<Role> roles = user.getRoles();
		if (roles != null)
			for (Role role : roles) {
				Role db_role = roleDAO.selectById(role.getRoleId());
				if (db_role == null)
					throw new Exception(RoleCons.ROLE_NOT_FOUND_MESS());
			}

		List<Department> departs = user.getDepartments();
		if (departs != null)
			for (Department depart : departs) {
				Department db_depart = departmentDAO.selectOneByDepartId(depart
						.getDepartId());
				if (db_depart == null)
					throw new Exception(
							DepartmentCons.DEPARTMENT_NOT_FOUND_MESS());
			}
	}

	public void batchRemove(Long[] ids) throws Exception {
		if (ids == null || ids.length == 0)
			throw new Exception(UserCons.USER_NOT_FOUND());

		for (Long id : ids) {
			User user = userDAO.getOne(id);
			if (user == null)
				throw new Exception(UserCons.USER_NOT_FOUND());

			userDAO.removeOne(id);
		}
	}

	public void removeOne(Long id) throws Exception {
		this.batchRemove(new Long[] { id });
	}

	public void add(User user) throws Exception {
		user.setAuthcode("");
		user.setRePwd(user.getPassword());
		this.register("", user);
	}

	public void update(final User user) throws Exception {
		Long id = user.getId();
		if (null == id || id <= 0)
			throw new Exception(UserCons.USER_NOT_FOUND());

		String rePwd = user.getRePwd();
		if (!rePwd.equals(user.getPassword()))
			throw new Exception(UserCons.INCORECT_REPEAT_PWD());

		User db_User = userDAO.getOne(id);
		if (db_User == null)
			throw new Exception(UserCons.USER_NOT_FOUND());

		User other_User = userDAO.getOtherByAccount(id, user.getAccount());
		if (other_User != null)
			throw new Exception(UserCons.DUPLICATE_ACC());

		if (!db_User.getPassword().equals(user.getPassword()))
			user.setPassword(MD5Util.getMD5(user.getPassword()));

		this.userRoleAndDepartLogic(user);

		user.setModifyTime(CommonUtil.getNowTime());

		Transaction.execute(new Trans() {

			@Override
			public void run(Object... args) throws Exception {
				user.setRoles(null);
				user.setDepartments(null);
				userDAO.update(user);
				User new_user = new User();
				new_user.setId(user.getId());
			}
		}, "");

	}

	public EditPage<User> getEditPage(Long id) throws Exception {
		if (id == null || id <= 0)
			throw new Exception(UserCons.USER_NOT_FOUND());

		User user = userDAO.getOne(id);
		if (user == null)
			throw new Exception(UserCons.USER_NOT_FOUND());

		this.cascadeUser(new User[] { user });

		return new EditPage<User>(UserCons.MODEL_NAME(), UserCons.MODEL_NAME()
				+ "/" + id, user);
	}

	public void cascadeUser(User[] users) throws Exception {
		if (users == null)
			return;

		this.userDAO.cascadeSelect(users);
		for (User user : users) {
			UserUtil.isValid(user);

			List<Department> departs = user.getDepartments();
			if (departs != null) {
				departmentDAO.cascadeSelect(departs
						.toArray(new Department[] {}));
			}
		}
	}

	public PageMod<User> getListPage(int pageNum, int numPerPage)
			throws Exception {
		List<User> pojos = this.userDAO.getPage(pageNum, numPerPage);
		long allCount = this.userDAO.countAll();
		if (pojos != null)
			this.cascadeUser(pojos.toArray(new User[] {}));

		return new PageMod<User>(pojos, allCount);
	}

	public PageMod<User> getSearchResult(String keyword, int pageNum,
			int numPerPage) throws Exception {
		if (keyword == null || keyword.length() == 0) {
			return getListPage(pageNum, numPerPage);
		}

		List<User> pojos = this.userDAO.getSearchResult(keyword, pageNum,
				numPerPage);
		if (pojos != null)
			this.cascadeUser(pojos.toArray(new User[] {}));

		long count = this.userDAO.countSearch(keyword);

		return new PageMod<User>(pojos, count);
	}

	public void lock(Long id) throws Exception {
		this.batchLock(new Long[] { id });
	}

	public void batchLock(Long[] ids) throws Exception {
		if (ids == null || ids.length == 0)
			throw new Exception(UserCons.USER_NOT_FOUND());

		for (Long id : ids) {
			User user = this.userDAO.getOne(id);
			if (user == null)
				throw new Exception(UserCons.USER_NOT_FOUND());

			user.setStatus(UserCons.LOCK());
			this.userDAO.updateByFields(user, "status");
		}
	}

	public void unLock(Long id) throws Exception {
		this.batchUnLock(new Long[] { id });
	}

	public void batchUnLock(Long[] ids) throws Exception {
		if (ids == null || ids.length == 0)
			throw new Exception(UserCons.USER_NOT_FOUND());

		for (Long id : ids) {
			User user = this.userDAO.getOne(id);
			if (user == null)
				throw new Exception(UserCons.USER_NOT_FOUND());

			user.setStatus(UserCons.NORMAL());
			this.userDAO.updateByFields(user, "status");
		}
	}

	public void addUserRoleRelation(final long[] userIds, final long[] roleIds)
			throws Exception {
		if (userIds == null || userIds.length == 0)
			throw new Exception(UserCons.USER_NOT_SELECTED_MESS());

		if (roleIds == null || roleIds.length == 0)
			throw new Exception(RoleCons.ROLE_NOT_SELECTED_MESS());

		Transaction.execute(new Trans() {

			@Override
			public void run(Object... args) throws Exception {
				for (long userId : userIds) {
					final User user = new User();
					user.setId(userId);
					UserUtil.setRole(roleIds, user);
					user.setDepartments(null);
					userDAO.cascadeInsert(user, "roles");
				}
			}
		}, "");

	}

	public void addUserDepartmentRelation(final long[] userIds, final long[] departmentIds) throws Exception {
		if (userIds == null || userIds.length == 0)
			throw new Exception(UserCons.USER_NOT_SELECTED_MESS());

		if (departmentIds == null || departmentIds.length == 0)
			throw new Exception(DepartmentCons.DEPARTMENT_NOT_SELECTED_MESS());

		Transaction.execute(new Trans() {

			public void run(Object... args) throws Exception {
				for (long userId : userIds) {
					final User user = new User();
					user.setId(userId);
					UserUtil.setDepart(departmentIds, user);
					user.setRoles(null);
					userDAO.cascadeInsert(user, "departments");
				}
			}
		}, "");
	}

	public User queryUserById(long id) throws Exception {
		User user = userDAO.getOne(id);
		if (user == null)
			throw new Exception(UserCons.USER_NOT_FOUND());
		this.cascadeUser(new User[] { user });

		return user;
	}

	public void deleteUserRoleRelation(final long userId, final long[] roleIds)
			throws Exception {
		if (roleIds == null || roleIds.length == 0)
			throw new Exception(RoleCons.ROLE_NOT_SELECTED_MESS());

		queryUserById(userId);

		Transaction.execute(new Trans() {

			@Override
			public void run(Object... args) throws Exception {
				User user = new User();
				user.setId(userId);
				user.setDepartments(null);
				for (long roleId : roleIds) {
					Role role = new Role();
					role.setRoleId(roleId);
					user.getRoles().add(role);
				}

				userDAO.cascadeDelete(user, "roles");

			}
		}, "");

	}

	public void deleteUserDepartmentRelation(final long userId,
			final long[] departmentIds) throws Exception {
		if (departmentIds == null || departmentIds.length == 0)
			throw new Exception(DepartmentCons.DEPARTMENT_NOT_SELECTED_MESS());

		queryUserById(userId);

		Transaction.execute(new Trans() {

			@Override
			public void run(Object... args) throws Exception {
				User user = new User();
				user.setId(userId);
				user.setRoles(null);
				for (long departId : departmentIds) {
					Department depart = new Department();
					depart.setDepartId(departId);
					user.getDepartments().add(depart);
				}

				userDAO.cascadeDelete(user, "departments");

			}
		}, "");

	}

}
