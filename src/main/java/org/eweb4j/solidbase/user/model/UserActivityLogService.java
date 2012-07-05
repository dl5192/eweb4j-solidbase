package org.eweb4j.solidbase.user.model;

import org.eweb4j.mvc.view.PageMod;

public interface UserActivityLogService {
	public PageMod<UserActivityLog> getListPage(int pageNum, int numPerPage)
			throws Exception;
	public void createLogInfo(UserActivityLog log) throws Exception;
}
