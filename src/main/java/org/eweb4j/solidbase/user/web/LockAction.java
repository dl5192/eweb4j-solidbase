package org.eweb4j.solidbase.user.web;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.user.model.UserCons;

/**
 * 锁定|解锁用户。将user表的status设置为“锁定”、“正常”
 * 
 * @author weiwei
 * 
 */
@Path("${UserConstant.MODEL_NAME}")
public class LockAction extends BaseAction {
	@PUT
	@Path("batchLock")
	public String batchLock() {
		try {
			userService.batchLock(ids);

			return UserCons.DWZ_SUCCESS_JSON("锁定用户成功");
		} catch (Exception e) {

			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	@PUT
	@Path("{id}/lock")
	public void lockOne() {
		this.ids = new Long[] { this.id };
		batchLock();
	}

	@PUT
	@Path("batchUnLock")
	public String batchUnLock() {
		try {
			userService.batchUnLock(ids);

			return UserCons.DWZ_SUCCESS_JSON("解锁用户成功");
		} catch (Exception e) {

			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	@PUT
	@Path("{id}/unLock")
	public void unLockOne() {
		this.ids = new Long[] { this.id };
		batchUnLock();
	}
}
