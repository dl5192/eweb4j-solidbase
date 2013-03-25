package org.eweb4j.solidbase.role.dao;

import java.util.Collection;

import org.eweb4j.orm.Db;
import org.eweb4j.orm.dao.DAOException;
import org.eweb4j.solidbase.role.model.Role;
import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.role.model.RoleException;


public class RoleDAOImpl implements RoleDAO {
	
	private String dsName = null;
	
	public void setDsName(String dsName) {
		this.dsName = dsName;
	}

	public void insert(Role role) throws RoleException {
		try {
			Db.ar(role, dsName).create();
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void deleteById(long id) throws RoleException {
		try {
			Role role = new Role();
			role.setRoleId(id);
			Db.ar(role).delete();
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void update(Role role) throws RoleException {
		try {
			Db.ar(role,dsName).save();
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Role selectById(long id) throws RoleException {
		try {
			return Db.ar(Role.class,dsName).findById(id);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Role selectByName(String name) throws RoleException {
		try {
			return Db.ar(Role.class,dsName).find("byName", name).first();
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Collection<Role> selectAll() throws RoleException {
		try {
			return Db.ar(Role.class,dsName).findAll();
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Collection<Role> divPage(int p, int n) throws RoleException {
		try {
			return Db.ar(Role.class,dsName).find().fetch(p, n);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public long countAll() throws RoleException {
		try {
			return Db.ar(Role.class,dsName).count();
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public long countByLike(String keyword) throws RoleException {
		try {
			return Db.ar(Role.class,dsName).count("byNameLike", "%" + keyword + "%");
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Collection<Role> selectByLike(String keyword, int p, int n) throws RoleException {
		try {
			return Db.ar(Role.class,dsName).find("byNameLike", "%" + keyword + "%").fetch(p, n);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void cascadeDelete(Role role, String... fields) throws RoleException {
		try {
			Db.ar(role,dsName).cascade().remove(fields);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void cascadeInsert(Role role, String... fields) throws RoleException {
		try {
			Db.ar(role,dsName).cascade().persist(fields);
		} catch (DAOException e) {
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void cascadeSelect(Role role,String... fields) throws RoleException {
		try{
			Db.ar(role,dsName).cascade().fetch(fields);
		}catch(DAOException e){
			throw new RoleException(RoleCons.DATA_ACCESS_ERR(), e);
		}
	}
}
