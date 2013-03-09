package org.eweb4j.solidbase.user.web;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.mvc.view.DivPageComp;
import org.eweb4j.mvc.view.ListPage;
import org.eweb4j.solidbase.user.model.UserCons;

@Path("${UserConstant.MODEL_NAME}")
public class PagingAction extends BaseAction {

	@GET
	@POST
	@Path("list")
	public String listHandler(Map<String, Object> model) {
		try {
			pageMod = userService.getListPage(pageNum, numPerPage);
			allCount = pageMod.getAllCount();
			pojos = pageMod.getPojos();

			DivPageComp dpc = new DivPageComp(pageNum, numPerPage, allCount, 20);

			listPage = new ListPage(UserCons.MODEL_NAME(), searchForm, pojos, dpc);

			model.put("listPage", listPage);
			model.put("random", Math.random());
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return UserCons.PAGING_ACTION_RESULT();
	}
}
