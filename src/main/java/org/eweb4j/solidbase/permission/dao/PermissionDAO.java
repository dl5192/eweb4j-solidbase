package org.eweb4j.solidbase.permission.dao;

import java.util.Collection;

import org.eweb4j.solidbase.permission.model.PermHttpIndex;
import org.eweb4j.solidbase.permission.model.Permission;

public interface PermissionDAO {

	long insert(Permission permission) throws Exception;

	Permission selectOneById(long permId) throws Exception;

	void cascadeSelect(Permission[] permissions, String... fields) throws Exception;

	Collection<Permission> divPage(int pageNum, int numPerPage) throws Exception;

	long countAll() throws Exception;

	void update(Permission permission) throws Exception;

	void delete(long permId) throws Exception;

	Permission selectOneByName(String name) throws Exception;

	Permission selectOneByResourceAndHttpMethod(long resId, Collection<Long> httpMethods) throws Exception;

	Collection<Permission> selectByTypeId(long permTypeId) throws Exception;

	Collection<Permission> selectByResource(long resId) throws Exception;

	PermHttpIndex selectRelTableData(long permId, long codeId) throws Exception;

	void cascadeInsert(Permission permission, String... fields) throws Exception;

	void cascadeDelete(Permission permission, String... fields) throws Exception;

}
