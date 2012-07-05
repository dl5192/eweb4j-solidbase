package org.eweb4j.solidbase.user.web.inter;

import javax.servlet.http.HttpSession;

import org.eweb4j.mvc.Context;
import org.eweb4j.mvc.action.annotation.Singleton;
import org.eweb4j.mvc.interceptor.Interceptor;
import org.eweb4j.mvc.interceptor.Uri;
import org.eweb4j.solidbase.user.model.User;

/**
 * 登陆验证拦截器
 * 
 * @author weiwei
 */
@Interceptor(
	priority = 0,
	method = "check",
	uri = { @Uri(value="_css", type="!start"), @Uri(value="_js", type="!start") },
	except = {"users/login.jsp", "users/register.jsp", "users/login", "users/register", "users/captcha.jsp", "dwzres/javascripts/jquery-1.4.4.min.js"}
)
@Singleton
public class LoginVerify {

	public String check(Context context) {
		HttpSession session = context.getRequest().getSession(true);
		User loginUser = (User) session.getAttribute("loginUser");
		
		if (loginUser == null) 
			return "action:users/login";

		return null;
	}
}
