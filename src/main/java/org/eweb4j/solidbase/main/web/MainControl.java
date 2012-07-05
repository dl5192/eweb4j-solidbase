package org.eweb4j.solidbase.main.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.component.dwz.menu.MenuException;
import org.eweb4j.component.dwz.menu.navmenu.NavMenu;
import org.eweb4j.component.dwz.menu.navmenu.NavMenuCons;
import org.eweb4j.component.dwz.menu.navmenu.NavMenuService;
import org.eweb4j.component.dwz.menu.treemenu.TreeMenu;
import org.eweb4j.component.dwz.menu.treemenu.TreeMenuCons;
import org.eweb4j.component.dwz.menu.treemenu.TreeMenuService;
import org.eweb4j.ioc.IOC;
import org.eweb4j.mvc.Context;
import org.eweb4j.mvc.MVC;
import org.eweb4j.mvc.action.annotation.Controller;
import org.eweb4j.solidbase.main.model.MainCons;
import org.eweb4j.solidbase.main.model.MainModelView;
import org.eweb4j.solidbase.user.model.User;
import org.eweb4j.solidbase.user.model.UserCons;

/**
 * 默认控制器，负责后台主页的显示，统计等操作。
 * 
 * @author weiwei
 * 
 */
@Controller
@Path("${MainConstant.MODEL_NAME}")
public class MainControl {

	private Long navMenuId = null;
	private String pageName = null;

	private DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());
	private NavMenuService navMenuService = IOC.getBean(NavMenuCons.IOC_SERVICE_BEAN_ID());
	private TreeMenuService treeMenuService = IOC.getBean(TreeMenuCons.IOC_SERVICE_BEAN_ID());

	private final static String format = "<div class=\"accordion\" fillSpace=\"sideBar\">%s</div>";

	private boolean findPerm(List<Long> permissions, List<TreeMenu> parents) throws MenuException {
		if (permissions == null)
			return false;

		for (TreeMenu parent : parents) {
			List<TreeMenu> children = treeMenuService.getChildren(parent.getTreeMenuId());
			if (children == null || children.size() == 0)
				continue;

			for (TreeMenu tm : children)
				for (Long treemenuId : permissions)
					if (treemenuId.equals(tm.getTreeMenuId()))
						return true;
		}

		return false;
	}

	/**
	 * 显示后台界面, 暂时包含一些菜单权限处理逻辑。以后记得重构
	 * 
	 * @return
	 */
	@GET
	@Path("/")
	public String doGet() {
		List<NavMenu> navMenus = null;
		String treeMenus = "";
		final HttpSession session = MVC.ctx().getRequest().getSession(true);
		try {

			User loginUser = (User) session.getAttribute(UserCons
					.LOGIN_USER_ATTR_NAME());

			/* 用户有超能力采用缓存、并且不采用菜单控制 */
			if ("yes".equals(loginUser.getSuperPower())) {
				navMenus = navMenuService.findAllOrderBy("rank", 1);
				if (navMenus != null && navMenus.size() > 0)
					treeMenus = dwz.getAccordion(
							navMenus.get(0).getNavMenuId(), true);

			} else {

				/* 树形菜单权限集 */
				List<Long> treeMenuPerms = loginUser.getTreeMenuPerms();

				/* 导航菜单权限集 */
				List<Long> navMenuPerms = loginUser.getNavMenuPerms();

				/* 首先从缓存中获取 */
				navMenus = (List<NavMenu>) session.getAttribute(NavMenu.class
						.getName() + "_LIST_FOR_DWZ_NAVMENU");
				if (navMenus == null)
					navMenus = new ArrayList<NavMenu>();

				/* 如果缓存中获取的导航菜单没有,则从数据库中获取，并且受权限控制 */
				if (navMenus.size() == 0) {

					/* 从数据中获取导航菜单 */
					List<NavMenu> _navMenus = navMenuService.findAllOrderBy(
							"rank", 1);

					if (_navMenus != null) {
						for (NavMenu n : _navMenus) {
							List<TreeMenu> parent = treeMenuService
									.getTopParent(n.getNavMenuId());
							if (parent == null || parent.size() == 1) {
								if (n.getHref().endsWith(".jsp")
										|| n.getHref().endsWith(".html")) {
									if (navMenuPerms == null)
										continue;

									for (Long id : navMenuPerms) {
										if (n.getNavMenuId().equals(id)) {
											navMenus.add(n);
											break;
										}
									}

								}

								continue;
							}

							if (findPerm(treeMenuPerms, parent)) {
								navMenus.add(n);
							}
						}

						if (navMenus != null)
							session.setAttribute(NavMenu.class.getName()
									+ "_LIST_FOR_DWZ_NAVMENU", navMenus);
					}
				}

				treeMenus = (String) session.getAttribute(TreeMenu.class
						.getName() + "_STRING_FOR_DWZ_TREE");
				if (treeMenus == null && navMenus.size() > 0) {
					dwz.setTreeMenuPermissions(treeMenuPerms);
					treeMenus = dwz.getAccordion(
							navMenus.get(0).getNavMenuId(), false);
					if (treeMenus != null)
						session.setAttribute(TreeMenu.class.getName()
								+ "_STRING_FOR_DWZ_TREE", treeMenus);
				}

			}

			MainModelView mmv = new MainModelView();
			mmv.setTitle(MainCons.TITLE());
			mmv.setNavMenus(navMenus);
			mmv.setTreeMenus(treeMenus);
			mmv.setCopyRight(MainCons.COPY_RIGHT());

			MVC.ctx().getModel().put("MainModel", mmv);

		} catch (Exception e) {
			e.printStackTrace();
			return dwz.getFailedJson(e.getMessage()).toString();
		}

		return "forward:main/view/main.jsp";
	}

	/**
	 * 切换导航菜单
	 * 
	 * @return
	 */
	@GET
	@POST
	@Path("/switchEnv/{navMenuId}")
	public String switchEnvHandler(Context context) {
		String treeMenu = (String) context.getSession().getAttribute(
				NavMenu.class.getName() + "_SWITCH_ENV_" + navMenuId);
		User loginUser = (User) context.getRequest().getSession(true)
				.getAttribute(UserCons.LOGIN_USER_ATTR_NAME());
		try {
			/* 用户有超能力不采用缓存 */
			if ("yes".equals(loginUser.getSuperPower())) {
				treeMenu = dwz.getAccordion(navMenuId, true);
				context.getSession().setAttribute(
						NavMenu.class.getName() + "_SWITCH_ENV_" + navMenuId,
						treeMenu);
			} else if (treeMenu == null) {
				dwz.setTreeMenuPermissions(loginUser.getTreeMenuPerms());
				treeMenu = dwz.getAccordion(navMenuId, false);
				context.getSession().setAttribute(
						NavMenu.class.getName() + "_SWITCH_ENV_" + navMenuId,
						treeMenu);
			}

			return String.format(format, treeMenu);
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

	@GET
	@POST
	@Path("/switchEnv/{pageName}.html")
	public String doSwitchPage1(Map<String, Object> model) {
		model.put("switchEnvPath", pageName + ".html");
		return "forward:main/view/switchEnv.jsp";
	}

	@GET
	@POST
	@Path("/switchEnv/{pageName}.jsp")
	public String doSwitchPage2(Map<String, Object> model) {
		model.put("switchEnvPath", pageName + ".jsp");
		return "forward:main/view/switchEnv.jsp";
	}

	@Path("/about-us")
	@POST
	@GET
	public String doAboutUs() {
		return "forward:main/view/about.jsp";
	}

	public void setNavMenuId(Long navMenuId) {
		this.navMenuId = navMenuId;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

}
