package org.eweb4j.solidbase.resource.model;

import java.util.Collection;

import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.mvc.view.PageMod;

public interface ResourceService {
	public Collection<Resource> getAllResource() throws ResourceException;

	public long createResourceInfo(Resource resource) throws ResourceException;

	public void updateResourceInfo(Resource resource) throws ResourceException;

	public EditPage<Resource> getEditPage(long resId) throws ResourceException;

	public void batchRemoveResourceInfo(long[] ids) throws ResourceException;

	public void removeResourceInfo(long resId) throws ResourceException;

	public PageMod<Resource> getPageDepartInfo(int pageNum, int numPerPage) throws ResourceException;
}
