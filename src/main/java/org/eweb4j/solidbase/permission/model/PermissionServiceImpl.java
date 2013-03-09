package org.eweb4j.solidbase.permission.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eweb4j.mvc.view.EditPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.orm.jdbc.transaction.Trans;
import org.eweb4j.orm.jdbc.transaction.Transaction;
import org.eweb4j.solidbase.code.dao.CodeDAO;
import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.code.model.CodeService;
import org.eweb4j.solidbase.permission.dao.PermissionDAO;
import org.eweb4j.solidbase.resource.dao.ResourceDAO;
import org.eweb4j.solidbase.resource.model.Resource;
import org.eweb4j.solidbase.resource.model.ResourceCons;
import org.eweb4j.util.CommonUtil;

public class PermissionServiceImpl implements PermissionService {

	private PermissionDAO permissionDAO;
	private CodeDAO codeDAO;
	private ResourceDAO resourceDAO;
	private CodeService codeService;

	public void setCodeService(CodeService codeService) {
		this.codeService = codeService;
	}

	public void setResourceDAO(ResourceDAO resourceDAO) {
		this.resourceDAO = resourceDAO;
	}

	private void addAndUpdateLogic(final Permission permission)
			throws Exception {
		if (permission == null)
			throw new Exception(PermissionCons.PERMISSION_NOT_FOUND_MESS());

		Collection<Code> httpMethods = permission.getHttpMethods();
		if (httpMethods == null || httpMethods.size() == 0)
			throw new Exception(PermissionCons.HTTP_METHOD_REQUIRED_MESS());

		for (Code code : httpMethods) {
			long codeId = code.getCodeId();
			Code db_code = codeDAO.selectOneByCodeId(codeId);
			if (db_code == null)
				throw new Exception(PermissionCons.HTTP_METHOD_NOT_FOUND_MESS());
		}

		Code perm_type = permission.getType();
		if (perm_type == null)
			throw new Exception(PermissionCons.PERMISSION_TYPE_REQUIRED_MESS());

		Code db_perm_type = codeDAO.selectOneByCodeId(perm_type.getCodeId());
		if (db_perm_type == null)
			throw new Exception(PermissionCons.PERMISSION_TYPE_REQUIRED_MESS());
	}

	private Resource getResourceByAdd(final Permission permission) throws Exception {
		Resource res = permission.getResource();
		if (res.getResId() > 0)
			return res;
		if (res.getUri() == null || res.getUri().length() == 0)
			throw new Exception(ResourceCons.URI_NOT_FOUND_MESS());

		Resource db_res = resourceDAO.selectOneByUri(res.getUri());
		if (db_res == null) {
			long resId = resourceDAO.insert(res);
			res.setResId(resId);
		} else {
			res.setResId(db_res.getResId());
		}

		return res;
	}

	public void addPermission(final Permission permission) throws Exception {
		addAndUpdateLogic(permission);

		Permission db_perm = permissionDAO.selectOneByName(permission.getName());
		if (db_perm != null)
			throw new Exception(PermissionCons.DUP_PERMISSION_MESS());

		Resource res = getResourceByAdd(permission);

		Collection<Permission> db_perms = permissionDAO.selectByResource(res.getResId());
		if (db_perms != null && db_perms.size() > 0) {
			Collection<Code> httpMethods = permission.getHttpMethods();
			for (Code httpMethod : httpMethods) {
				long codeId = httpMethod.getCodeId();
				for (Permission perm : db_perms) {
					long permId = perm.getPermId();
					PermHttpIndex phi = permissionDAO.selectRelTableData(permId, codeId);
					if (phi != null)
						throw new Exception(PermissionCons.DUP_PERMISSION_MESS());
				}
			}
		}

		Transaction.execute(new Trans() {
			public void run(Object... args) throws Exception {
				String now = CommonUtil.getNowTime();
				permission.setAddTime(now);
				permission.setModifyTime(now);
				long permId = permissionDAO.insert(permission);
				permission.setPermId(permId);
				permissionDAO.cascadeInsert(permission, "httpMethods");
			}
		}, "");

	}

	public void update(final Permission permission) throws Exception {
		this.addAndUpdateLogic(permission);

		Permission db_perm = permissionDAO.selectOneByName(permission.getName());
		if (db_perm != null && db_perm.getPermId() != permission.getPermId())
			throw new Exception(PermissionCons.DUP_PERMISSION_MESS());

		Resource res = getResourceByAdd(permission);

		Collection<Permission> db_perms = permissionDAO.selectByResource(res.getResId());
		if (db_perms != null && db_perms.size() > 0) {
			Collection<Code> httpMethods = permission.getHttpMethods();
			for (Code httpMethod : httpMethods) {
				long codeId = httpMethod.getCodeId();
				for (Permission perm : db_perms) {
					long permId = perm.getPermId();

					PermHttpIndex phi = permissionDAO.selectRelTableData(permId, codeId);
					if (phi == null)
						continue;

					if (permId != phi.getPerm().getPermId())
						throw new Exception(PermissionCons.DUP_PERMISSION_MESS());
				}
			}
		}

		Transaction.execute(new Trans() {
			public void run(Object... args) throws Exception {
				String now = CommonUtil.getNowTime();
				permission.setModifyTime(now);
				permissionDAO.update(permission);
				Permission new_perm = new Permission();
				new_perm.setPermId(permission.getPermId());
				permissionDAO.cascadeDelete(new_perm, "httpMethods");
				permissionDAO.cascadeInsert(permission, "httpMethods");
			}
		}, "");
	}

	public PermissionDAO getPermissionDAO() {
		return permissionDAO;
	}

	public void setPermissionDAO(PermissionDAO permissionDAO) {
		this.permissionDAO = permissionDAO;
	}

	public CodeDAO getCodeDAO() {
		return codeDAO;
	}

	public void setCodeDAO(CodeDAO codeDAO) {
		this.codeDAO = codeDAO;
	}

	public PageMod<Permission> getPage(int pageNum, int numPerPage) throws Exception {
		Collection<Permission> pojos = permissionDAO.divPage(pageNum, numPerPage);
		if (pojos != null)
			permissionDAO.cascadeSelect(pojos.toArray(new Permission[] {}));

		long allCount = permissionDAO.countAll();
		return new PageMod<Permission>(pojos, allCount);
	}

	public EditPage<Permission> getEditPage(long permId) throws Exception {
		Permission db_perm = permissionDAO.selectOneById(permId);
		if (db_perm == null)
			throw new Exception(PermissionCons.PERMISSION_NOT_FOUND_MESS());

		permissionDAO.cascadeSelect(new Permission[] { db_perm });

		String model = PermissionCons.MODEL_NAME();
		String action = model + "/" + permId;
		return new EditPage<Permission>(model, action, db_perm);
	}

	public void remove(long permId) throws Exception {
		this.batchRemove(new long[] { permId });
	}

	public void batchRemove(final long[] permIds) throws Exception {
		if (permIds == null || permIds.length == 0)
			throw new Exception(PermissionCons.PERMISSION_NOT_FOUND_MESS());

		Transaction.execute(new Trans() {
			public void run(Object... args) throws Exception {
				for (long permId : permIds) {

					Permission db_perm = permissionDAO.selectOneById(permId);
					if (db_perm == null)
						throw new Exception(PermissionCons.PERMISSION_NOT_FOUND_MESS());

					Permission perm = new Permission();
					perm.setPermId(permId);

					permissionDAO.cascadeDelete(perm, "httpMethods");
					permissionDAO.delete(permId);
				}
			}
		}, "");
	}

	public Permission findByURIAndHttpMethod(String uri, String httpMethodStr)
			throws Exception {

		Resource res = resourceDAO.selectOneByUri(uri);
		if (res == null)
			return null;

		long resId = res.getResId();

		String[] httpMethods = httpMethodStr.split("\\|");
		List<Long> httpMethodIds = new ArrayList<Long>();
		for (String httpMethod : httpMethods) {
			Code method = codeDAO.selectOneByCodeValue(httpMethod);
			if (method != null)
				httpMethodIds.add(method.getCodeId());
		}

		long[] methodIds = new long[httpMethodIds.size()];
		for (int i = 0; i < methodIds.length; i++) {
			methodIds[i] = httpMethodIds.get(i);
		}

		return permissionDAO.selectOneByResourceAndHttpMethod(resId, methodIds);
	}

	public Collection<Permission> findByTypeId(long permTypeId) throws Exception {
		return permissionDAO.selectByTypeId(permTypeId);
	}

	public Collection<PermissionMod> assemPermissionMod() throws Exception {
		Collection<PermissionMod> result = null;
		Code permType = codeService.getAndCreateCodeByCodeValue(PermissionCons.PERMISSION_TYPE_CODE_VALUE());

		Collection<Code> permTypes = codeDAO.selectPageByCodeTypeId(permType.getCodeId(), -1, -1);

		if (permTypes == null || permTypes.size() == 0)
			throw new Exception("缺少权限分类信息,请先到代码管理处添加权限分类");

		result = new ArrayList<PermissionMod>();
		for (Code type : permTypes) {
			long permTypeId = type.getCodeId();
			Collection<Permission> perms = this.findByTypeId(permTypeId);
			PermissionMod permMod = new PermissionMod();
			permMod.setPermissions(perms);
			permMod.setPermType(type);

			result.add(permMod);
		}

		return result;
	}
}
