package org.eweb4j.solidbase.department.web;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.mvc.view.DataAssemUtil;
import org.eweb4j.mvc.view.DivPageComp;
import org.eweb4j.mvc.view.ListPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.mvc.view.SearchForm;
import org.eweb4j.solidbase.department.model.Department;
import org.eweb4j.solidbase.department.model.DepartmentCons;

@Path("${DepartmentConstant.MODEL_NAME}")
public class PagingDepartmentAction extends DepartmentBaseAction {

	private int pageNum = 1;
	private int numPerPage = 20;
	private SearchForm searchForm = new SearchForm(DepartmentCons.MODEL_NAME() + "/list", "");
	private PageMod<Department> pageMod;
	private long allCount;
	private List<Department> pojos;
	private DivPageComp dpc;
	private ListPage listPage;

	@Path("/list")
	@GET
	@POST
	public String doGet(Map model) {

		try {
			pageMod = departService.getPageDepartInfo(pageNum, numPerPage);
			allCount = pageMod.getAllCount();
			pojos = pageMod.getPojos();

			dpc = new DivPageComp(pageNum, numPerPage, allCount - 1, 8);

			listPage = new ListPage(DepartmentCons.MODEL_NAME(), searchForm, pojos, dpc);
			listPage = DataAssemUtil.assemHead(listPage, pojos, DepartmentCons.getMap());

			model.put("listPage", listPage);
			model.put("random", Math.random());
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return DepartmentCons.PAGING_ACTION_RESULT();
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

}
