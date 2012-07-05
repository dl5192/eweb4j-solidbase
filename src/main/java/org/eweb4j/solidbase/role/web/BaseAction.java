package org.eweb4j.solidbase.role.web;

import java.util.List;

import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.ioc.IOC;
import org.eweb4j.mvc.validator.annotation.Int;
import org.eweb4j.mvc.validator.annotation.Size;
import org.eweb4j.mvc.view.ListPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.mvc.view.SearchForm;
import org.eweb4j.solidbase.role.model.Role;
import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.role.model.RoleService;

public class BaseAction {

	@Size(min = -1)
	@Int
	protected int pageNum = 1;
	@Size(min = -1)
	@Int
	protected int numPerPage = 20;

	protected RoleService service = IOC.getBean(RoleCons.IOC_SERVICE_BEAN_ID());

	protected DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());

	protected SearchForm searchForm = new SearchForm(RoleCons.MODEL_NAME()
			+ "/search", "");
	protected ListPage listPage = null;
	protected List<Role> pojos = null;
	protected PageMod<Role> pageMod = null;
	protected long allCount = 0;

	public void setDwz(DWZ dwz) {
		this.dwz = dwz;
	}

	public void setService(RoleService service) {
		this.service = service;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
}
