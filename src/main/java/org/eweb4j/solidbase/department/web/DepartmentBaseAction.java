package org.eweb4j.solidbase.department.web;

import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.ioc.IOC;
import org.eweb4j.solidbase.department.model.DepartmentCons;
import org.eweb4j.solidbase.department.model.DepartmentService;

public abstract class DepartmentBaseAction {
	protected DepartmentService departService = IOC.getBean(DepartmentCons.IOC_SERVICE_BEAN_ID());
	protected DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());
}
