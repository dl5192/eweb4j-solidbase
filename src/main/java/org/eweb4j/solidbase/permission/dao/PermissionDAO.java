package org.eweb4j.solidbase.permission.dao;

import java.util.List;
import java.util.Map;

import org.eweb4j.solidbase.permission.model.Permission;

public interface PermissionDAO {

	long insert(Permission permission) throws Exception;

	Permission selectOneById(long permId) throws Exception;

	void cascadeSelect(Permission[] permissions, String... fields)
			throws Exception;

	List<Permission> divPage(int pageNum, int numPerPage) throws Exception;

	long countAll() throws Exception;

	void update(Permission permission) throws Exception;

	void delete(long permId) throws Exception;

	Permission selectOneByName(String name) throws Exception;

	Permission selectOneByResourceAndHttpMethod(long resId, long[] httpMethods)
			throws Exception;

	List<Permission> selectByTypeId(long permTypeId) throws Exception;

	List<Permission> selectByResource(long resId) throws Exception;

	Map<String, Object> selectRelTableData(long permId, long codeId)
			throws Exception;

	void cascadeInsert(Permission permission, String... fields)
			throws Exception;

	void cascadeDelete(Permission permission, String... fields)
			throws Exception;

}
