package org.eweb4j.solidbase.user.web.inter;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.config.Log;
import org.eweb4j.config.LogFactory;
import org.eweb4j.ioc.IOC;
import org.eweb4j.mvc.Context;
import org.eweb4j.mvc.config.bean.ActionConfigBean;
import org.eweb4j.mvc.interceptor.Interceptor;
import org.eweb4j.mvc.interceptor.Uri;
import org.eweb4j.solidbase.permission.model.Permission;
import org.eweb4j.solidbase.permission.model.PermissionCons;
import org.eweb4j.solidbase.permission.model.PermissionService;
import org.eweb4j.solidbase.role.model.Role;
import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.role.model.RoleService;
import org.eweb4j.solidbase.user.model.User;
import org.eweb4j.solidbase.user.model.UserActivityLog;
import org.eweb4j.solidbase.user.model.UserActivityLogCons;
import org.eweb4j.solidbase.user.model.UserActivityLogService;
import org.eweb4j.util.CommonUtil;

/**
 * 鉴权过滤器
 * 
 * @author weiwei
 * 
 */
@Interceptor(
		priority = 2, 
		policy = "and",
		method = "check", 
		uri = { @Uri(type="*") }, 
		except = {"users/captcha.jsp"})
public class PermissionVerifyFilter {
	
	private Log log = LogFactory.getMVCLogger(getClass());
	
	HttpSession session;
	private User loginUser;
	private List<Role> roles;
	private RoleService roleService = IOC.getBean(RoleCons.IOC_SERVICE_BEAN_ID());
	private PermissionService permService = IOC.getBean(PermissionCons.IOC_SERVICE_BEAN_ID());
	private DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());
	private String permName = "";

	private UserActivityLogService logService = IOC.getBean(UserActivityLogCons.IOC_SERVICE_BEAN_ID());

	private UserActivityLog ual = new UserActivityLog();

	private boolean isSuccess ;
	private String mess = null;

	public String check(Context context) {
		isSuccess = false ;
		String uri = context.getUri();
		if (uri.length() == 0)
			uri = " ";
		if (uri.endsWith("/"))
			uri = uri.substring(0, uri.length() - 1);

		log.info("uri -> " + uri);
		
		session = context.getRequest().getSession(true);
		loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null)
			return null;

		log.info("loginUser -> " + loginUser.getAccount());
		
		// 该用户拥有超能力，可以免权限检查
		if ("yes".equals(loginUser.getSuperPower()))
			return null;

		roles = loginUser.getRoles();
		if (roles == null) {
			session.invalidate();
			return "out:<script>alert('当前登录的账户没有任何权限');</script>";
		}

		log.info("roles -> " + roles);
		
		permName = uri;

		try {
			final String httpMethod = context.getHttpMethod();
			
			Permission permission = permService.findByURIAndHttpMethod(uri, httpMethod);
			
			if (permission != null)
				log.info("first find perm -> " + permission.getPermId()+"|"+permission.getName());
			
			if (permission == null){
				ActionConfigBean actionBean = context.getActionConfigBean();
				if (actionBean != null){
					String actionName = actionBean.getUriMapping();
					
					log.info("action -> " + actionName + "@" + httpMethod);
					
					permission = permService.findByURIAndHttpMethod(actionName, httpMethod);
					if (permission != null)
						log.info("first find perm -> " + permission.getPermId()+"|"+permission.getName());
					//else
						//return dwz.getFailedJson("抱歉，当前所访问的功能尚未开通，敬请期待！").toString();// 没有被定义成权限的Action不允许访问
				}//else
					//return null;// 如果不是Action且没有定义成权限则表示是普通的URI，默认放过
			}
			
			if (permission == null)
				return null;//没有定义为权限的通通放过
			
			permName = permission.getName();
			ual.setUser(loginUser);
			ual.setUserAccount(loginUser.getAccount());
			ual.setUserName(loginUser.getTrueName());
			ual.setTime(CommonUtil.getNowTime());

			role_item: for (Role r : roles) {

				Role role = roleService.findPermissionByRoleId(r.getRoleId());
				List<Permission> perms = role.getPermissions();

				if (perms == null || perms.size() == 0){
					isSuccess = false;
					continue;
				}

				log.info(r.getRoleId() + "|" + role.getRoleId() + "======== role-perms -> " + perms);
				
				for (Permission perm : perms) {
					// 权限通过
					if (permission != null && permission.getPermId() == perm.getPermId()) {
						isSuccess = true;
						log.info("perm verify success");
						break role_item;
					}
					
					isSuccess = false;
				}
			}
			
			log.info("isSuccess --> " + isSuccess);
			
			if (!isSuccess)
				mess = String.format("用户权限不足, 无法执行[%s]功能", this.permName);
			
		} catch (Exception e) {
			e.printStackTrace();
			mess = "系统出现异常：" + e.toString();
			isSuccess = false;
		}

		log.info("mess -> " + mess);
		
		// user log
		ual.setActivity(permName);
		
		if (isSuccess) {
			ual.setResult("success");
		} else {
			ual.setResult("false");
			ual.setFailureCause(mess);
		}

		try {
			logService.createLogInfo(ual);
			if (isSuccess)
				return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			mess = e.toString();
		}

		return dwz.getFailedJson(mess).toString();
	}
}
