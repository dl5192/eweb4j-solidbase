package org.eweb4j.solidbase.user.model;

import java.util.Collection;

import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.orm.Db;
import org.eweb4j.util.CommonUtil;

public class UserActivityLogServiceImpl implements UserActivityLogService {

	private String dsName = null;
	
	public void setDsName(String dsName) {
		this.dsName = dsName;
	}

	private final static Class<UserActivityLog> clazz = UserActivityLog.class;

	public PageMod<UserActivityLog> getListPage(int p, int n) throws Exception {
		Collection<UserActivityLog> pojos = Db.ar(clazz, dsName).find().fetch(p,n);

		long allCount = Db.ar(clazz, dsName).count();

		return new PageMod<UserActivityLog>(pojos, allCount);
	}

	public void createLogInfo(UserActivityLog log) throws Exception {
		log.setTime(CommonUtil.getNowTime());
		Db.ar(log, dsName).create();
	}

}
