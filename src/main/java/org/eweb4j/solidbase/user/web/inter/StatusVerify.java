package org.eweb4j.solidbase.user.web.inter;

import javax.servlet.http.HttpSession;

import org.eweb4j.mvc.Context;
import org.eweb4j.mvc.action.annotation.Singleton;
import org.eweb4j.mvc.interceptor.Interceptor;
import org.eweb4j.mvc.interceptor.Uri;
import org.eweb4j.orm.dao.DAOFactory;
import org.eweb4j.solidbase.user.model.User;
import org.eweb4j.solidbase.user.model.UserCons;
import org.eweb4j.solidbase.user.util.UserUtil;

/**
 * 用户状态验证拦截器
 * 
 * @author weiwei
 * 
 */
@Interceptor(
	priority=1, 
	policy = "and",
	method = "check", 
	uri = { @Uri(type = "*") }
)
@Singleton
public class StatusVerify {

	public String check(Context context) {
		String error = null;
		HttpSession session = context.getRequest().getSession(true);
		String url = context.getRequest().getRequestURL().toString();
		String loginPath = url.replace(context.getUri(), "") + UserCons.MODEL_NAME() + "/login";

		User user = (User) session.getAttribute("loginUser");
		if (user != null) {
			user = DAOFactory.getSelectDAO().selectOne(user, "id");
			if (user == null) {

			} else if (user.getStatus().equals(UserCons.LOCK())) {
				// 如果用户账号被锁定了，弹出去。
				error = "<script>alert('" + UserCons.LOCKED_MESS()
						+ "'); window.location='" + loginPath + "'</script>";
				session.invalidate();
			} else if (!UserUtil.isValid(user)) {
				// 如果用户帐号失效了,弹出去
				error = "<script>alert('" + UserCons.INVALID_MESS()
						+ "'); window.location='" + loginPath + "'</script>";
				session.invalidate();
			}
		}

		return error;
	}
}
