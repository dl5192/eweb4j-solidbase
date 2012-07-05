package org.eweb4j.solidbase.user.web;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.ioc.IOC;
import org.eweb4j.mvc.view.DataAssemUtil;
import org.eweb4j.mvc.view.DivPageComp;
import org.eweb4j.mvc.view.ListPage;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.mvc.view.SearchForm;
import org.eweb4j.solidbase.user.model.UserActivityLog;
import org.eweb4j.solidbase.user.model.UserActivityLogCons;
import org.eweb4j.solidbase.user.model.UserActivityLogService;
import org.eweb4j.solidbase.user.model.UserCons;

@Path("${UserConstant.MODEL_NAME}")
public class ListUserActivityLogAction {

	private UserActivityLogService service = IOC.getBean(UserActivityLogCons
			.IOC_SERVICE_BEAN_ID());

	private int pageNum = 1;
	private int numPerPage = 20;

	private PageMod<UserActivityLog> pageMod = null;
	private long allCount = 0;
	private List<UserActivityLog> pojos = null;
	private DivPageComp dpc = null;
	private ListPage listPage = null;
	private SearchForm searchForm = new SearchForm("users/logs/search", "");

	@Path("/logs")
	@GET
	@POST
	public String doGet(Map model) {
		try {
			pageMod = service.getListPage(pageNum, numPerPage);
			allCount = pageMod.getAllCount();
			pojos = pageMod.getPojos();

			dpc = new DivPageComp(pageNum, numPerPage, allCount, 10);

			listPage = new ListPage("users/logs", searchForm, pojos, dpc);
			listPage = DataAssemUtil.assemHead(listPage, pojos,
					UserActivityLogCons.getMap());

			model.put("listPage", listPage);
			model.put("random", Math.random());

		} catch (Exception e) {
			return e.toString();
		}
		return UserCons.PAGING_LOG_ACTION_RESULT();
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
}
