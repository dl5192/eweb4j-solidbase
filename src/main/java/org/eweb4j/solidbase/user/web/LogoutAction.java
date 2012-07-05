package org.eweb4j.solidbase.user.web;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.eweb4j.mvc.MVC;

@Path("${UserConstant.MODEL_NAME}")
public class LogoutAction extends BaseAction {
	@PUT
	@Path("logout")
	public String doLogout() {
		MVC.ctx().getSession().invalidate();
		return "redirect:login";
	}
}
