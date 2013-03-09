package org.eweb4j.solidbase.permission.dao;

import java.util.Collection;

import org.eweb4j.orm.Db;
import org.eweb4j.orm.dao.DAOException;
import org.eweb4j.orm.dao.DAOFactory;
import org.eweb4j.solidbase.permission.model.PermHttpIndex;
import org.eweb4j.solidbase.permission.model.Permission;
import org.eweb4j.solidbase.permission.model.PermissionCons;

public class PermissionDAOImpl implements PermissionDAO {
	
	private String dsName;
	private static Class<Permission> clazz = Permission.class;
	
	public void setDsName(String dsName) {
		this.dsName = dsName;
	}

	public long insert(Permission permission) throws Exception {
		try {
			Db.ar(permission).create();
			return permission.getPermId();
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Permission selectOneById(long permId) throws Exception {
		try {
			return Db.ar(clazz).findById(permId);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void cascadeSelect(Permission[] permissions, String... fields) throws Exception {
		try {
			DAOFactory.getCascadeDAO(dsName).select(permissions, fields);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Collection<Permission> divPage(int pageNum, int numPerPage) throws Exception {
		try {
			return Db.ar(clazz).find().fetch(pageNum, numPerPage);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public long countAll() throws Exception {
		try {
			return Db.ar(clazz).count();
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void update(Permission permission) throws Exception {
		try {
			Db.ar(permission).save();
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void delete(long permId) throws Exception {
		try {
			Db.ar(clazz).delete("byPermId", permId);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Permission selectOneByName(String name) throws Exception {
		try {
			return Db.ar(clazz).find("byName", name).first();
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Permission selectOneByResourceAndHttpMethod(long resId, long[] httpMethods) throws Exception {
		try {

			/**
			 * select * from t_permission where id in (
			 		SELECT perm_id FROM t_perm_http_method WHERE perm_id in (
			 				select id from t_permission where resource_id = {resId} 
			 		) and http_method in('529', '530')
			 	)
			 */
			
			/**
			 * select * from 
			 * 		t_permission p, 
			 * 		t_perm_http_method ph 
			 * where
			 * 		p.resource_id = {resId}
			 * 		and ph.http_method in ()
			 * 		and p.id = ph.perm_id
			 */
			
			return Db.ar(PermHttpIndex.class)
						.dao()
						.alias("ph")
						.join("perm", "p")
						.select(Permission.class)
						.where()
							.field("p.resource").equal(resId)
							.and("ph.http").in(new Object[]{httpMethods})
							.enableExpress(true)
							.and("ph.perm").equal("p.permId")
						.queryOne();
			
//			String selectPermByUriSql = 
//					Db.ar(clazz).dao()
//					.select("permId")
//					.where()
//						.field("resource").equal(resId)
//					.toSql();
//			
//			String selectPermByIdAndHttpMethodSql = 
//					Db.ar(Map.class).dao()
//					.setTable("t_perm_http_method")
//					.select("perm_id")
//					.where()
//						.field("perm_id").inSql(selectPermByUriSql)
//						.and("http_method").in(new Object[]{httpMethods})
//					.toSql();
//
//			return Db.ar(clazz).dao()
//						.selectAll()
//						.where()
//							.field("permId").inSql(selectPermByIdAndHttpMethodSql)
//						.queryOne();

		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Collection<Permission> selectByTypeId(long permTypeId) throws Exception {
		try {
			return Db.ar(clazz).find("byType", permTypeId).fetch();
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Collection<Permission> selectByResource(long resId) throws Exception {
		try {
			return Db.ar(clazz).find("byResource", resId).fetch();
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public PermHttpIndex selectRelTableData(long permId, long codeId) throws Exception {
		try {
			return Db.ar(PermHttpIndex.class).find("byPermAndHttp", permId, codeId).first();
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void cascadeInsert(Permission permission, String... fields) throws Exception {
		try {
			Db.ar(permission).cascade().persist(fields);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void cascadeDelete(Permission permission, String... fields) throws Exception {
		try {
			Db.ar(permission).cascade().remove(fields);
		} catch (DAOException e) {
			throw new Exception(PermissionCons.DATA_ACCESS_ERR(), e);
		}
	}

}
