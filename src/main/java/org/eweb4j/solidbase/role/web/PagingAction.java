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
public class PagingAction extends BaseAction {

	@GET
	@POST
	@Path("/list")
	public String doPaging(Map model) {
		try {
			pageMod = service.getPage(pageNum, numPerPage);
			allCount = pageMod.getAllCount();
			pojos = pageMod.getPojos();

			DivPageComp dpc = new DivPageComp(pageNum, numPerPage, allCount, 20);

			listPage = new ListPage(RoleCons.MODEL_NAME(), searchForm, pojos,dpc);
			
			model.put("random", Math.random());
			model.put("listPage", listPage);

			return RoleCons.PAGING_ACTION_RESULT();
		} catch (RoleException e) {

			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}
}
