package org.eweb4j.solidbase.user.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;

import org.eweb4j.component.dwz.menu.navmenu.NavMenu;
import org.eweb4j.component.dwz.menu.treemenu.TreeMenu;
import org.eweb4j.config.Log;
import org.eweb4j.config.LogFactory;
import org.eweb4j.mvc.MVC;
import org.eweb4j.mvc.action.Validation;
import org.eweb4j.mvc.validator.annotation.Validate;
import org.eweb4j.solidbase.role.model.Role;
import org.eweb4j.solidbase.user.model.User;
import org.eweb4j.solidbase.user.model.UserCons;
import org.eweb4j.util.StringUtil;

import com.google.code.kaptcha.Constants;

@Path("${UserConstant.MODEL_NAME}")
public class LoginAction extends BaseAction {

	static Log log = LogFactory.getMVCLogger(LoginAction.class);

	public String doLoginAtGet() {
		return UserCons.LOGIN_ACTION_RESULT();
	}

	@Validate({ "user.authcode", "user.account", "user.password" })
	public Object doLoginAtPut(Validation val, Map model) {
		if (val.hasErr()){
			model.put("valError", val.getAllErr());
			return UserCons.LOGIN_ACTION_RESULT();
		}
		
		List<Long> treeMenuPerms = new ArrayList<Long>();
		List<Long> navMenuPerms = new ArrayList<Long>();
		try {
			String _authCode = (String) MVC.ctx().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
			User loginUser = userService.login(_authCode,StringUtil.getIpAddr(MVC.ctx().getRequest()), user);

			// 登陆成功之后，将用户的权限信息查询出来放到session内存中
			/* 权限控制 */
			List<Role> roles = loginUser.getRoles();
			if (roles == null)
				roles = new ArrayList<Role>();

			for (Role role : roles) {
				Role _role = roleService.findTreeMenuByRoleId(role.getRoleId());
				/* 角色对应权限集合 */
				List<TreeMenu> tms = _role.getMenus();
				List<NavMenu> nms = _role.getNavMenus();

				if (tms != null) {
					for (TreeMenu tm : tms)
						treeMenuPerms.add(tm.getTreeMenuId());
				}
				if (nms != null) {
					for (NavMenu nm : nms)
						navMenuPerms.add(nm.getNavMenuId());
				}
			}

			log.fatal("treeMenuPerms-->" + treeMenuPerms.toString());
			log.fatal("navMenuPerms-->" + navMenuPerms.toString());

			loginUser.setTreeMenuPerms(treeMenuPerms);
			loginUser.setNavMenuPerms(navMenuPerms);

			MVC.ctx().getSession().setAttribute(UserCons.LOGIN_USER_ATTR_NAME(), loginUser);

		} catch (Exception e) {
			model.put(UserCons.LOGIN_ERR_ATTR_NAME(), e.getMessage());
			return UserCons.LOGIN_ACTION_RESULT();
		}

		return UserCons.LOGIN_SUCCESS_REDIRECT();
	}

}
