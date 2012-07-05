package org.eweb4j.solidbase.resource.dao;

import java.util.List;

import org.eweb4j.orm.dao.DAOException;
import org.eweb4j.orm.dao.DAOFactory;
import org.eweb4j.orm.dao.delete.DeleteDAO;
import org.eweb4j.orm.dao.insert.InsertDAO;
import org.eweb4j.orm.dao.select.DivPageDAO;
import org.eweb4j.orm.dao.select.SelectDAO;
import org.eweb4j.orm.dao.update.UpdateDAO;
import org.eweb4j.solidbase.resource.model.Resource;
import org.eweb4j.solidbase.resource.model.ResourceCons;
import org.eweb4j.solidbase.resource.model.ResourceException;

public class ResourceDAOImpl implements ResourceDAO {

	private Class<Resource> clazz = Resource.class;

	private SelectDAO selectDAO;
	private InsertDAO insertDAO;
	private UpdateDAO updateDAO;
	private DeleteDAO deleteDAO;
	private DivPageDAO divPageDAO;

	public void setDsName(String dsName) {
		this.selectDAO = DAOFactory.getSelectDAO(dsName);
		this.insertDAO = DAOFactory.getInsertDAO(dsName);
		this.updateDAO = DAOFactory.getUpdateDAO(dsName);
		this.deleteDAO = DAOFactory.getDeleteDAO(dsName);
		this.divPageDAO = DAOFactory.getDivPageDAO(dsName);
	}

	public Resource selectOneByUri(String uri) throws ResourceException {
		try {
			String[] fields = { "uri" };
			String[] values = { uri };
			return selectDAO.selectOne(clazz, fields, values);
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

	public long insert(Resource resource) throws ResourceException {
		long id = -1;
		try {
			id = Long
					.parseLong(String.valueOf(this.insertDAO.insert(resource)));
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
		return id;
	}

	public List<Resource> selectAll() throws ResourceException {
		try {
			return this.selectDAO.selectAll(clazz);
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void update(Resource resource) throws ResourceException {
		try {
			this.updateDAO.update(resource);
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

	public Resource selectOneByResId(long resId) throws ResourceException {
		try {
			return this.selectDAO.selectOneById(clazz, resId);
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

	public void deleteByResId(long id) throws ResourceException {
		try {
			this.deleteDAO.deleteById(clazz, id);
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

	public long countAll() throws ResourceException {
		try {
			return this.selectDAO.selectCount(clazz);
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

	public List<Resource> divPage(int pageNum, int numPerPage)
			throws ResourceException {
		try {
			return this.divPageDAO.divPage(clazz, pageNum, numPerPage);
		} catch (DAOException e) {
			throw new ResourceException(ResourceCons.DATA_ACCESS_ERR(), e);
		}
	}

}
