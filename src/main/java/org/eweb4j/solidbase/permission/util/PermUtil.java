package org.eweb4j.solidbase.permission.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.eweb4j.cache.ActionConfigBeanCache;
import org.eweb4j.mvc.config.bean.ActionConfigBean;
import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.code.model.CodeService;
import org.eweb4j.solidbase.permission.model.ActionComparator;
import org.eweb4j.solidbase.permission.model.Permission;
import org.eweb4j.solidbase.permission.model.PermissionCons;
import org.eweb4j.solidbase.permission.model.PermissionService;

public class PermUtil {
	public static void setActions(PermissionService permService,
			CodeService codeService, HttpServletRequest request)
			throws Exception {
		setActions(permService, codeService, request, null);
	}

	public static void setActions(PermissionService permService,
			CodeService codeService, HttpServletRequest request,
			Permission permission) throws Exception {
		Map<Object, ActionConfigBean> map = ActionConfigBeanCache.getAll();
		List<ActionConfigBean> actions = new ArrayList<ActionConfigBean>(
				map.size());
		for (Iterator<Entry<Object, ActionConfigBean>> it = map.entrySet()
				.iterator(); it.hasNext();) {
			Entry<Object, ActionConfigBean> entry = it.next();
			ActionConfigBean mvcBean = entry.getValue();

			Permission t_p = permService.findByURIAndHttpMethod(
					mvcBean.getUriMapping(), mvcBean.getHttpMethod());
			if (t_p == null) {
				actions.add(mvcBean);
			} else if (permission != null
					&& t_p.getPermId() == permission.getPermId()) {
				actions.add(mvcBean);
			}
		}

		Collections.sort(actions, new ActionComparator());
		request.setAttribute("actions", actions);
	}

	public static void setHttpMethods(long[] methods, Permission perm)
			throws Exception {
		if (methods == null || methods.length == 0)
			throw new Exception(PermissionCons.HTTP_METHOD_REQUIRED_MESS());

		for (long codeId : methods) {
			Code method = new Code();
			method.setCodeId(codeId);
			perm.getHttpMethods().add(method);
		}
	}

}
