package org.eweb4j.solidbase.role.dao;

import java.util.List;

import org.eweb4j.solidbase.role.model.Role;
import org.eweb4j.solidbase.role.model.RoleException;

public interface RoleDAO {
	public void insert(Role role) throws RoleException;

	public void deleteById(long id) throws RoleException;

	public void update(Role role) throws RoleException;

	public Role selectById(long id) throws RoleException;

	public Role selectByName(String name) throws RoleException;

	public List<Role> selectAll() throws RoleException;

	public List<Role> divPage(int p, int n) throws RoleException;

	public long countAll() throws RoleException;

	public long countByLike(String keyword) throws RoleException;

	public List<Role> selectByLike(String keyword, int pageNum, int numPerPage)
			throws RoleException;

	public void cascadeDelete(Role new_role, String... fields) throws RoleException;

	public void cascadeInsert(Role role, String... fields) throws RoleException;

	public void cascadeSelect(Role role, String... fields) throws RoleException;
}
