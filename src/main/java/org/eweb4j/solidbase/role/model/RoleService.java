package org.eweb4j.solidbase.role.model;

import java.util.List;

import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.mvc.view.PageMod;

public interface RoleService {

	/**
	 * 添加角色信息
	 * 
	 * @param role
	 * @throws RoleException
	 */
	public void addRole(Role role) throws RoleException;

	/**
	 * 批量删除角色信息
	 * 
	 * @param ids
	 *            long数组
	 * @throws RoleException
	 */
	public void batchRemove(Long[] ids) throws RoleException;

	/**
	 * 删除角色信息
	 * 
	 * @param id
	 * @throws RoleException
	 */
	public void removeRole(long id) throws RoleException;

	/**
	 * 修改角色信息
	 * 
	 * @param role
	 * @throws RoleException
	 */
	public void editRole(Role role) throws RoleException;

	/**
	 * 分页获取角色列表
	 * 
	 * @param p
	 * @param n
	 * @return
	 * @throws RoleException
	 */
	public PageMod<Role> getPage(int p, int n) throws RoleException;

	/**
	 * 获取所有角色
	 * 
	 * @return
	 * @throws RoleException
	 */
	public List<Role> getAll() throws RoleException;

	/**
	 * 获取编辑页面数据，
	 * 
	 * @param id
	 * @return
	 * @throws RoleException
	 */
	public EditPage<Role> getEditPage(long id) throws RoleException;

	/**
	 * 
	 * @param keyword
	 * @param pageNum
	 * @param numPerPage
	 * @return
	 * @throws RoleException
	 */
	public PageMod<Role> queryByKeyword(String keyword, int pageNum,
			int numPerPage) throws RoleException;

	/**
	 * 添加角色-权限关联信息
	 * 
	 * @param roleIds
	 * @param permissionIds
	 * @throws RoleException
	 */
	public void addRolePermissionRelation(long[] roleIds, long[] permissionIds)
			throws RoleException;

	/**
	 * 删除角色-权限关联信息
	 * 
	 * @param roleId
	 * @param permissionIds
	 * @throws RoleException
	 */
	public void deleteRolePermissionRelation(long roleId, long[] permissionIds)
			throws RoleException;

	/**
	 * 获取某个角色拥有的权限集
	 * 
	 * @param roleId
	 * @return
	 * @throws RoleException
	 */
	Role findPermissionByRoleId(long roleId) throws RoleException;

	/**
	 * 添加角色-菜单关联信息
	 * 
	 * @param roleIds
	 * @param treeMenuIds
	 * @param navMenuIds
	 * @throws RoleException
	 */
	public void addRoleMenuRelation(long[] roleIds, long[] treeMenuIds,
			long[] navMenuIds) throws RoleException;

	/**
	 * 获取某个角色拥有的菜单集
	 * 
	 * @param roleId
	 * @return
	 */
	public Role findTreeMenuByRoleId(long roleId) throws RoleException;

	/**
	 * 删除角色-菜单关联信息
	 * 
	 * @param roleId
	 * @param treeMenuIds
	 * @param navMenuIds
	 */
	public void deleteRoleMenuRelation(long roleId, long[] treeMenuIds,
			long[] navMenuIds) throws RoleException;

}
