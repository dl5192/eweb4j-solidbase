package org.eweb4j.solidbase.role.model;

import java.util.List;

import org.eweb4j.component.dwz.menu.treemenu.TreeMenuCons;
import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.orm.jdbc.transaction.Trans;
import org.eweb4j.orm.jdbc.transaction.Transaction;
import org.eweb4j.solidbase.permission.dao.PermissionDAO;
import org.eweb4j.solidbase.permission.model.Permission;
import org.eweb4j.solidbase.permission.model.PermissionCons;
import org.eweb4j.solidbase.role.dao.RoleDAO;
import org.eweb4j.solidbase.role.util.RoleUtil;
import org.eweb4j.util.CommonUtil;

public class RoleServiceImpl implements RoleService {

	private RoleDAO roleDAO = null;

	private PermissionDAO permDAO = null;

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void setPermDAO(PermissionDAO permDAO) {
		this.permDAO = permDAO;
	}

	public void addRole(Role role) throws RoleException {
		if (role == null)
			throw new RoleException("要添加的角色信息不能为空");

		// 检查角色名称是否与现有的重复
		Role db_role = roleDAO.selectByName(role.getName());
		if (null != db_role)
			throw new RoleException("角色名称已经存在，请更换之");

		String now = CommonUtil.getNowTime();
		role.setAddTime(now);
		role.setModifyTime(now);
		// 存入数据库
		roleDAO.insert(role);
	}

	public void removeRole(long id) throws RoleException {
		if (id < 0)
			throw new RoleException("角色ID值无效");

		if (id == 0)
			throw new RoleException("该角色不允许删除");

		Role db_role = this.roleDAO.selectById(id);
		if (db_role == null)
			throw new RoleException("角色ID值无效");

		this.roleDAO.deleteById(id);
	}

	public void editRole(Role role) throws RoleException {
		if (role == null)
			throw new RoleException("角色不能为空");

		long id = role.getRoleId();
		if (id <= 0)
			throw new RoleException("角色ID值无效");

		Role db_role = this.roleDAO.selectById(id);
		if (db_role == null)
			throw new RoleException("角色ID值无效");

		String name = role.getName();
		Role db_n_role = this.roleDAO.selectByName(name);
		if (db_n_role != null && db_n_role.getRoleId() != id)
			throw new RoleException("角色名不能重复,请更换之");

		role.setModifyTime(CommonUtil.getNowTime());

		this.roleDAO.update(role);
	}

	public PageMod<Role> getPage(int p, int n) throws RoleException {
		List<Role> pojos = roleDAO.divPage(p, n);
		long allCount = this.roleDAO.countAll();

		return new PageMod<Role>(pojos, allCount);
	}

	public List<Role> getAll() throws RoleException {
		return this.roleDAO.selectAll();
	}

	public EditPage<Role> getEditPage(long id) throws RoleException {
		if (id < 0)
			throw new RoleException("角色ID值无效");

		if (id == 0)
			throw new RoleException("该角色不允许修改");

		Role db_role = this.roleDAO.selectById(id);
		if (db_role == null)
			throw new RoleException("角色ID值无效");

		return new EditPage<Role>(RoleCons.MODEL_NAME(), RoleCons.MODEL_NAME()
				+ "/" + id, db_role);
	}

	public void batchRemove(Long[] ids) throws RoleException {
		if (null == ids || ids.length == 0)
			throw new RoleException("请选择要删除的记录");

		for (long id : ids) {
			this.removeRole(id);
		}
	}

	public PageMod<Role> queryByKeyword(String keyword, int pageNum,
			int numPerPage) throws RoleException {
		if (keyword == null)
			keyword = "";
		List<Role> pojos = null;
		long allCount = 0;
		if (keyword.length() == 0) {
			return this.getPage(pageNum, numPerPage);
		} else {
			pojos = this.roleDAO.selectByLike(keyword, pageNum, numPerPage);
			allCount = this.roleDAO.countByLike(keyword);
		}

		return new PageMod<Role>(pojos, allCount);
	}

	public void addRolePermissionRelation(final long[] roleIds,
			final long[] permissionIds) throws RoleException {
		if (roleIds == null || roleIds.length == 0)
			throw new RoleException(RoleCons.ROLE_NOT_SELECTED_MESS());

		if (permissionIds == null || permissionIds.length == 0)
			throw new RoleException(
					PermissionCons.PERMISSION_NOT_SELECTED_MESS());

		Transaction.execute(new Trans() {

			@Override
			public void run(Object... args) throws RoleException {
				for (long roleId : roleIds) {
					final Role role = new Role();
					role.setRoleId(roleId);
					RoleUtil.setPermission(permissionIds, role);
					roleDAO.cascadeInsert(role, "permissions");
				}
			}
		}, "");
	}

	public void deleteRolePermissionRelation(final long roleId,
			final long[] permissionIds) throws RoleException {
		if (permissionIds == null || permissionIds.length == 0)
			throw new RoleException(
					PermissionCons.PERMISSION_NOT_SELECTED_MESS());

		Role db_role = roleDAO.selectById(roleId);
		if (db_role == null)
			throw new RoleException(RoleCons.ROLE_NOT_SELECTED_MESS());

		Transaction.execute(new Trans() {

			@Override
			public void run(Object... args) throws RoleException {
				final Role role = new Role();
				role.setRoleId(roleId);
				RoleUtil.setPermission(permissionIds, role);
				roleDAO.cascadeDelete(role, "permissions");
			}
		}, "");
	}

	public Role findPermissionByRoleId(long roleId) throws RoleException {

		Role db_role = roleDAO.selectById(roleId);
		if (db_role == null)
			throw new RoleException(RoleCons.ROLE_NOT_FOUND_MESS());

		Role role = new Role();
		role.setRoleId(roleId);
		roleDAO.cascadeSelect(role, "permissions");
		List<Permission> perms = role.getPermissions();
		if (perms != null && perms.size() > 0) {
			try {
				permDAO.cascadeSelect(perms.toArray(new Permission[] {}),
						"httpMethods", "resource");
			} catch (Exception e) {
				throw new RoleException("权限数据操作错误", e);
			}
		}

		return role;
	}

	public void addRoleMenuRelation(final long[] roleIds,
			final long[] treeMenuIds, final long[] navMenuIds)
			throws RoleException {
		if (roleIds == null || roleIds.length == 0)
			throw new RoleException(RoleCons.ROLE_NOT_SELECTED_MESS());

		if ((treeMenuIds == null || treeMenuIds.length == 0)
				&& (navMenuIds == null ||navMenuIds.length == 0))
			throw new RoleException(TreeMenuCons.MENU_NOT_SELECTED_MESS());

		Transaction.execute(new Trans() {

			@Override
			public void run(Object... args) throws RoleException {
				for (long roleId : roleIds) {
					final Role role = new Role();
					role.setRoleId(roleId);

					if (treeMenuIds != null && treeMenuIds.length > 0) {
						RoleUtil.setMenu(treeMenuIds, role);
						roleDAO.cascadeInsert(role, "menus");
					}

					if (navMenuIds != null && navMenuIds.length > 0) {
						RoleUtil.setNavMenu(navMenuIds, role);
						roleDAO.cascadeInsert(role, "navMenus");
					}
				}
			}
		}, "");
	}

	public Role findTreeMenuByRoleId(long roleId) throws RoleException {
		Role db_role = roleDAO.selectById(roleId);
		if (db_role == null)
			throw new RoleException(RoleCons.ROLE_NOT_FOUND_MESS());

		Role role = new Role();
		role.setRoleId(roleId);
		roleDAO.cascadeSelect(role, "menus", "navMenus");

		return role;
	}

	public void deleteRoleMenuRelation(final long roleId,
			final long[] treeMenuIds, final long[] navMenuIds)
			throws RoleException {
		if ((treeMenuIds == null || treeMenuIds.length == 0)
				&& (navMenuIds == null || navMenuIds.length == 0))
			throw new RoleException(TreeMenuCons.MENU_NOT_SELECTED_MESS());

		Role db_role = roleDAO.selectById(roleId);
		if (db_role == null)
			throw new RoleException(RoleCons.ROLE_NOT_SELECTED_MESS());

		Transaction.execute(new Trans() {

			@Override
			public void run(Object... args) throws RoleException {
				final Role role = new Role();
				role.setRoleId(roleId);

				if (treeMenuIds != null && treeMenuIds.length > 0) {
					RoleUtil.setMenu(treeMenuIds, role);
					roleDAO.cascadeDelete(role, "menus");
				}

				if (navMenuIds != null && navMenuIds.length > 0) {
					RoleUtil.setNavMenu(navMenuIds, role);
					roleDAO.cascadeDelete(role, "navMenus");
				}

			}
		}, "");
	}
}
