package org.eweb4j.solidbase.permission.web;

import java.util.Collection;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.mvc.view.DataAssemUtil;
import org.eweb4j.mvc.view.DivPageComp;
import org.eweb4j.mvc.view.ListPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.mvc.view.SearchForm;
import org.eweb4j.solidbase.permission.model.Permission;
import org.eweb4j.solidbase.permission.model.PermissionCons;

@Path("${PermissionConstant.MODEL_NAME}")
public class PagingPermissionAction extends PermissionBaseAction {

	private int pageNum = 1;
	private int numPerPage = 20;

	private PageMod<Permission> pageMod;
	private long allCount = 0;
	private Collection<Permission> pojos;
	private ListPage listPage;
	private DivPageComp dpc;
	private SearchForm searchForm = new SearchForm(PermissionCons.MODEL_NAME() + "/list", "");

	@Path("/list")
	@GET
	@POST
	public String doPaging(Map<String, Object> model) {
		try {
			pageMod = permService.getPage(pageNum, numPerPage);
			allCount = pageMod.getAllCount();
			pojos = pageMod.getPojos();
			dpc = new DivPageComp(pageNum, numPerPage, allCount, 10);
			
			listPage = new ListPage(PermissionCons.MODEL_NAME(), searchForm, pojos, dpc);
			listPage = DataAssemUtil.assemHead(listPage,PermissionCons.getMap());
			
			model.put("listPage", listPage);
			model.put("random", Math.random());
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return PermissionCons.PAGING_ACTION_RESULT();
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

}
