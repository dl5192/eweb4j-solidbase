package org.eweb4j.solidbase.user.web;

import java.util.Collection;

import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.ioc.IOC;
import org.eweb4j.mvc.view.ListPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.mvc.view.SearchForm;
import org.eweb4j.solidbase.department.model.DepartmentCons;
import org.eweb4j.solidbase.department.model.DepartmentService;
import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.role.model.RoleService;
import org.eweb4j.solidbase.user.model.User;
import org.eweb4j.solidbase.user.model.UserCons;
import org.eweb4j.solidbase.user.model.UserService;

public abstract class BaseAction {
	protected UserService userService = IOC.getBean(UserCons.IOC_SERVICE_BEAN_ID());
	protected RoleService roleService = IOC.getBean(RoleCons.IOC_SERVICE_BEAN_ID());
	protected DepartmentService departService = IOC.getBean(DepartmentCons.IOC_SERVICE_BEAN_ID());
	protected DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());

	protected User user;
	protected Long[] ids;
	protected Long id;

	protected int pageNum = 1;
	protected int numPerPage = 20;
	protected String keyword = "";

	protected SearchForm searchForm = new SearchForm(UserCons.MODEL_NAME() + "/search", "");
	protected ListPage listPage = null;
	protected Collection<User> pojos = null;
	protected PageMod<User> pageMod = null;
	protected long allCount = 0;

	public void setUser(User user) {
		this.user = user;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDwz(DWZ dwz) {
		this.dwz = dwz;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
