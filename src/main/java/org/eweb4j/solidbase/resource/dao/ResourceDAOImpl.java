package org.eweb4j.solidbase.resource.dao;

import java.util.Collection;

import org.eweb4j.orm.Db;
import org.eweb4j.orm.dao.DAOException;
import org.eweb4j.solidbase.resource.model.Resource;
import org.eweb4j.solidbase.resource.model.ResourceCons;
import org.eweb4j.solidbase.resource.model.ResourceException;

public class ResourceDAOImpl implements ResourceDAO {

	private String dsName;
	private static Class<Resource> clazz = Resource.class;

	public void setDsName(String dsName) {
		this.dsName = dsName;
	}

	public Resource selectOneByUri(String uri) throws ResourceException {
		try {
			return Db.ar(clazz, dsName).find("byUri", uri).first();
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

	public long insert(Resource resource) throws ResourceException {
		try {
			Db.ar(resource, dsName).create();
			return resource.getResId();
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Collection<Resource> selectAll() throws ResourceException {
		try {
			return Db.ar(clazz, dsName).findAll();
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void update(Resource resource) throws ResourceException {
		try {
			Db.ar(resource, dsName).save();
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Resource selectOneByResId(long resId) throws ResourceException {
		try {
			return Db.ar(clazz, dsName).findById(resId);
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void deleteByResId(long id) throws ResourceException {
		try {
			Db.ar(clazz, dsName).delete("byResId", id);
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

	public long countAll() throws ResourceException {
		try {
			return Db.ar(clazz, dsName).count();
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Collection<Resource> divPage(int pageNum, int numPerPage) throws ResourceException {
		try {
			return Db.ar(clazz, dsName).find().fetch(pageNum, numPerPage);
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void batchDelete(Resource... resources) throws ResourceException {
		try {
			Db.batchDelete(dsName, resources);
		} catch (DAOException e){
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

}
