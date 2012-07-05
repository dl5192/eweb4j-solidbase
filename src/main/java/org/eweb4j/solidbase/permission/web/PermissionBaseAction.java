package org.eweb4j.solidbase.permission.web;

import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.ioc.IOC;
import org.eweb4j.solidbase.code.model.CodeCons;
import org.eweb4j.solidbase.code.model.CodeService;
import org.eweb4j.solidbase.permission.model.Permission;
import org.eweb4j.solidbase.permission.model.PermissionCons;
import org.eweb4j.solidbase.permission.model.PermissionService;

public abstract class PermissionBaseAction {
	protected long[] httpMethods;
	protected long permId;
	protected Permission permission;
	protected CodeService codeService = IOC.getBean(CodeCons.IOC_SERVICE_BEAN_ID());
	protected PermissionService permService = IOC.getBean(PermissionCons.IOC_SERVICE_BEAN_ID());
	protected DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());

	public void setPermId(long permId) {
		this.permId = permId;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public void setHttpMethods(long[] httpMethods) {
		this.httpMethods = httpMethods;
	}

}
