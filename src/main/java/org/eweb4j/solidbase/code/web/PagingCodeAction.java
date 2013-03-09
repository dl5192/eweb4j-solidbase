package org.eweb4j.solidbase.code.web;

import java.util.Collection;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eweb4j.mvc.view.DataAssemUtil;
import org.eweb4j.mvc.view.DivPageComp;
import org.eweb4j.mvc.view.ListPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.mvc.view.SearchForm;
import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.code.model.CodeCons;

@Path("${CodeConstant.MODEL_NAME}")
public class PagingCodeAction extends CodeBaseAction {
	private ListPage listPage = null;
	private DivPageComp dpc = null;
	private SearchForm searchForm = new SearchForm(CodeCons.MODEL_NAME() + "/search", "");
	private long allCount = 0;
	private Collection<Code> pojos = null;
	private PageMod<Code> pageMod = null;

	/**
	 * 获取给定元类别ID的所有类别信息
	 */
	@Path("/list")
	@GET
	@POST
	public String doList(Map<String, Object> model) {
		try {
			pageMod = service.queryByCodeTypeIdAndParentId(codeTypeId,parentId, pageNum, numPerPage);
			allCount = pageMod.getAllCount();
			pojos = pageMod.getPojos();

			dpc = new DivPageComp(pageNum, numPerPage, allCount - 1, 8);
			searchForm.getParams().put("codeTypeId", codeTypeId);
			searchForm.getParams().put("parentId", parentId);

			listPage = new ListPage(CodeCons.MODEL_NAME(), searchForm, pojos, dpc);
			listPage = DataAssemUtil.assemHead(listPage, pojos, CodeCons.getMap());

			model.put("listPage", listPage);
			model.put("random", Math.random());
		} catch (Exception e) {

			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return CodeCons.PAGING_ACTION_RESULT();
	}

	/**
	 * 获取给定元类别ID的所有类别信息
	 */
	@Path("/search")
	@GET
	@POST
	public String doSearch(Map<String,Object> model, @QueryParam(value = "codeSearch.dwz_codeType.codeId") long codeTypeId, 
			@QueryParam(value = "codeSearch.dwz_codeParent.codeId") long parentId) {

		this.codeTypeId = codeTypeId;
		if (parentId <= 0)
			parentId = -1;
		
		this.parentId = parentId;

		return doList(model);
	}

}
