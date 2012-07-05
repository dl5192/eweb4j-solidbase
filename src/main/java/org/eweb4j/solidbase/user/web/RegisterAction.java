package org.eweb4j.solidbase.user.web;

import java.util.Map;

import javax.ws.rs.Path;

import org.eweb4j.mvc.MVC;
import org.eweb4j.mvc.action.Validation;
import org.eweb4j.mvc.validator.annotation.Validate;
import org.eweb4j.solidbase.user.model.UserCons;

import com.google.code.kaptcha.Constants;

@Path("${UserConstant.MODEL_NAME}")
public class RegisterAction extends BaseAction {
	
	public String doRegisterAtGet() {
		return UserCons.REGISTER_ACTION_RESULT();
	}

	@Validate({ "user.authcode", "user.account", "user.password", "user.rePwd" })
	public String doRegisterAtPost(Validation val, Map model) {
		if (val.hasErr()){
			model.put("valError", val.getAllErr());
			return UserCons.REGISTER_ACTION_RESULT();
		}
		try {
			String sessionAuthcode = (String) MVC.ctx().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
			userService.register(sessionAuthcode, user);
		} catch (Exception e) {
			e.printStackTrace();
			model.put("registerError", e.getMessage());
			return UserCons.REGISTER_ACTION_RESULT();
		}

		return UserCons.REGISTER_SUCCESS_INFO();
	}
}
