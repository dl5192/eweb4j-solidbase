package org.eweb4j.solidbase.resource.model;

import java.util.List;

import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.orm.jdbc.transaction.Trans;
import org.eweb4j.orm.jdbc.transaction.Transaction;
import org.eweb4j.solidbase.resource.dao.ResourceDAO;

public class ResourceServiceImpl implements ResourceService {
	private ResourceDAO resourceDAO;

	public void setResourceDAO(ResourceDAO resourceDAO) {
		this.resourceDAO = resourceDAO;
	}

	public List<Resource> getAllResource() throws ResourceException {

		return resourceDAO.selectAll();
	}

	public long createResourceInfo(Resource resource) throws ResourceException {
		if (resource == null)
			throw new ResourceException(ResourceCons.RESOURCE_REQUIRED_MESS());
		Resource db_res = this.resourceDAO.selectOneByUri(resource.getUri());
		if (db_res != null)
			throw new ResourceException(ResourceCons.DUP_RESOURCE_MESS());

		return this.resourceDAO.insert(resource);
	}

	public void updateResourceInfo(Resource resource) throws ResourceException {
		if (resource == null)
			throw new ResourceException(ResourceCons.RESOURCE_REQUIRED_MESS());
		Resource db_res = this.resourceDAO.selectOneByUri(resource.getUri());
		if (db_res != null && db_res.getResId() == resource.getResId())
			throw new ResourceException(ResourceCons.DUP_RESOURCE_MESS());

		this.resourceDAO.update(resource);
	}

	public EditPage<Resource> getEditPage(long resId) throws ResourceException {

		Resource pojo = this.resourceDAO.selectOneByResId(resId);
		String model = ResourceCons.MODEL_NAME();
		String action = model + "/" + resId;
		return new EditPage<Resource>(model, action, pojo);
	}

	public void batchRemoveResourceInfo(final long[] ids)
			throws ResourceException {
		if (ids == null || ids.length == 0)
			throw new ResourceException(
					ResourceCons.RESOURCE_ID_NOT_FOUND_MESS());

		Transaction.execute(new Trans() {

			@Override
			public void run(Object... args) throws Exception {
				for (long id : ids) {
					resourceDAO.deleteByResId(id);
				}

			}
		}, "");

	}

	public void removeResourceInfo(long resId) throws ResourceException {
		this.batchRemoveResourceInfo(new long[] { resId });
	}

	public PageMod<Resource> getPageDepartInfo(int pageNum, int numPerPage)
			throws ResourceException {

		List<Resource> pojos = this.resourceDAO.divPage(pageNum, numPerPage);
		long allCount = resourceDAO.countAll();

		return new PageMod<Resource>(pojos, allCount);
	}
}
