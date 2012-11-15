package org.eweb4j.solidbase.role.web;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.ioc.IOC;
import org.eweb4j.solidbase.permission.model.PermissionCons;
import org.eweb4j.solidbase.permission.model.PermissionService;
import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.role.model.RoleException;

/**
 * 分配权限控制器
 * 
 * @author weiwei
 * @since JDK 1.5
 * @CopyRight WinOn Ltd. 2012
 * @version Gateway-1.0.0
 * 
 */

@Path("${RoleConstant.MODEL_NAME}")
public class AllocPermissionAction extends BaseAction {

	private PermissionService permService = IOC.getBean(PermissionCons.IOC_SERVICE_BEAN_ID());

	@Path("/alloc-permission")
	@POST
	public String doAllocPerm(Map model) {
		try {
			model.put("roles", service.getAll());
			model.put("permissions", permService.assemPermissionMod());
			model.put("action", RoleCons.ADD_ROLE_PERM_ACTION());
			model.put("openType", RoleCons.ALLOC_PERM_OPEN_TYPE());
			model.put("random", Math.random());
		} catch (RoleException e) {
			return dwz.getFailedJson("角色信息获取失败，请检查系统是否具有角色信息").toString();
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return RoleCons.ALLOC_PERM_ACTION_RESULT();
	}

}
