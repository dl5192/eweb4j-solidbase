package org.eweb4j.solidbase.user.model;

import java.util.List;

import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.orm.dao.DAOFactory;
import org.eweb4j.orm.dao.cascade.CascadeDAO;
import org.eweb4j.orm.dao.insert.InsertDAO;
import org.eweb4j.orm.dao.select.DivPageDAO;
import org.eweb4j.orm.dao.select.SelectDAO;
import org.eweb4j.util.CommonUtil;

public class UserActivityLogServiceImpl implements UserActivityLogService {

	public UserActivityLogServiceImpl() {

	}

	public void setDsName(String dsName) {
		divPageDAO = DAOFactory.getDivPageDAO(dsName);
		selectDAO = DAOFactory.getSelectDAO(dsName);
		insertDAO = DAOFactory.getInsertDAO(dsName);
		cascadeDAO = DAOFactory.getCascadeDAO(dsName);
	}

	private final static Class<UserActivityLog> clazz = UserActivityLog.class;
	private DivPageDAO divPageDAO;
	private SelectDAO selectDAO;
	private InsertDAO insertDAO;
	private CascadeDAO cascadeDAO;

	public PageMod<UserActivityLog> getListPage(int pageNum, int numPerPage)
			throws Exception {
		List<UserActivityLog> pojos = divPageDAO.divPage(clazz, pageNum,
				numPerPage);
		if (pojos != null)
			cascadeDAO.select(pojos.toArray(new UserActivityLog[] {}), "user");

		long allCount = selectDAO.selectCount(clazz);

		return new PageMod<UserActivityLog>(pojos, allCount);
	}

	public void createLogInfo(UserActivityLog log) throws Exception {
		log.setTime(CommonUtil.getNowTime());
		insertDAO.insert(log);
	}

}
