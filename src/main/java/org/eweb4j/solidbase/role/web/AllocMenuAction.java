package org.eweb4j.solidbase.role.web;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.component.dwz.menu.treemenu.TreeMenuCons;
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
public class AllocMenuAction extends BaseAction {

	@Path("/alloc-menu")
	@POST
	public String doAllocMenu(Map model) {
		try {
			dwz.setRootUlStyleClass("expand tree treeFolder treeCheck");
			model.put("roles", service.getAll());
			String menus = dwz.createAllTreeMenu();
			dwz.setRootUlStyleClass(TreeMenuCons.ROOT_UL_STYLE_CLASS());
			model.put("menus", menus);
			
			model.put("action", RoleCons.ADD_ROLE_MENU_ACTION());
			model.put("openType", RoleCons.ALLOC_MENU_OPEN_TYPE());
			model.put("random", Math.random());
		} catch (RoleException e) {
			return dwz.getFailedJson("角色信息获取失败，请检查系统是否具有角色信息").toString();
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return RoleCons.ALLOC_MENU_ACTION_RESULT();
	}

}
