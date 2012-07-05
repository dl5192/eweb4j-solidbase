package org.eweb4j.solidbase.role.web;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.mvc.view.DivPageComp;
import org.eweb4j.mvc.view.ListPage;
import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.role.model.RoleException;

@Path("${RoleConstant.MODEL_NAME}")
public class SearchAction extends BaseAction {

	private String keyword = "";

	@Path("/search")
	@GET
	@POST
	public String doSearch(Map model) {
		try {
			pageMod = service.queryByKeyword(keyword, pageNum, numPerPage);
			allCount = pageMod.getAllCount();
			pojos = pageMod.getPojos();

			DivPageComp dpc = new DivPageComp(pageNum, numPerPage, allCount, 20);

			searchForm.setKeyword(keyword);
			listPage = new ListPage(RoleCons.MODEL_NAME(), searchForm, pojos, dpc);

			model.put("listPage", listPage);
			model.put("random", Math.random());
		} catch (RoleException e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return RoleCons.PAGING_ACTION_RESULT();
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
