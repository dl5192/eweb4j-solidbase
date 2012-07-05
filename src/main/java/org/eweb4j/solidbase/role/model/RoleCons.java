package org.eweb4j.solidbase.role.model;

import java.util.Map;

import org.eweb4j.cache.Props;
import org.eweb4j.config.ConfigConstant;
import org.eweb4j.mvc.view.CallBackJson;

public class RoleCons {
	private final static String propId = "RoleConstant";
	private final static Map<String, String> map = Props.getMap(propId);

	public static String IOC_DAO_BEAN_ID() {
		return map.get("IOC_DAO_BEAN_ID");
	}

	public static String IOC_SERVICE_BEAN_ID() {
		return map.get("IOC_SERVICE_BEAN_ID");
	}

	public static String MODEL_NAME() {
		return map.get("MODEL_NAME");
	}

	public static String DWZ_SUCCESS_JSON(String _mess) {
		String openType = OPEN_TYPE();
		String callbackType = "dialog".equalsIgnoreCase(openType) ? "reloadTab"
				: "closeCurrent";
		String mess = _mess == null ? "操作成功" : _mess;
		String rel = map.get("SHOW_LIST_REL");
		String path = map.get("SHOW_LIST_PATH");
		path = ConfigConstant.BASE_URL + path;
		String title = map.get("SHOW_LIST_TITLE");
		return new CallBackJson("200", mess, rel, path, callbackType, title)
				.toString();
	}

	public static String DWZ_SUCCESS_JSON_FOR_ALLOC_PERM(String _mess) {
		String openType = ALLOC_PERM_OPEN_TYPE();
		String callbackType = "dialog".equalsIgnoreCase(openType) ? "reloadTab"
				: "closeCurrent";
		callbackType = "reloadTab";
		String mess = _mess == null ? "操作成功" : _mess;
		String rel = map.get("ALLOC_PERM.SHOW_LIST_REL");
		String path = map.get("ALLOC_PERM.SHOW_LIST_PATH");
		path = ConfigConstant.BASE_URL + path;
		String title = map.get("ALLOC_PERM.SHOW_LIST_TITLE");
		return new CallBackJson("200", mess, rel, path, callbackType, title)
				.toString();
	}

	public static String OPEN_TYPE() {
		return map.get("OPEN_TYPE");
	}

	public static String ROLE_NOT_FOUND_MESS() {
		return map.get("ROLE_NOT_FOUND_MESS");
	}

	public static String ROLE_NOT_SELECTED_MESS() {
		return "请选择角色！";
	}

	public static String DATA_ACCESS_ERR() {
		return map.get("DATA_ACCESS_ERR");
	}

	public static String NEW_ACTION_RESULT() {
		return map.get("NEW_ACTION_RESULT");
	}

	public static String ALLOC_PERM_ACTION_RESULT() {
		return map.get("ALLOC_PERM_ACTION_RESULT");
	}

	public static String ADD_ROLE_PERM_ACTION() {
		return map.get("ADD_ROLE_PERM_ACTION");
	}

	public static String EDIT_ACTION_RESULT() {
		return map.get("EDIT_ACTION_RESULT");
	}

	public static String ALLOC_PERM_OPEN_TYPE() {
		return map.get("ALLOC_PERM.OPEN_TYPE");
	}

	public static String GET_ROLE_PERM_ACTION_RESULT() {
		return map.get("GET_ROLE_PERM_ACTION_RESULT");
	}

	public static String PAGING_ACTION_RESULT() {
		return map.get("PAGING_ACTION_RESULT");
	}

	public static String ADD_ROLE_MENU_ACTION() {
		return map.get("ADD_ROLE_MENU_ACTION");
	}

	public static String ALLOC_MENU_OPEN_TYPE() {
		return map.get("ALLOC_MENU.OPEN_TYPE");
	}

	public static String ALLOC_MENU_ACTION_RESULT() {
		return map.get("ALLOC_MENU_ACTION_RESULT");
	}

	public static String DWZ_SUCCESS_JSON_FOR_ALLOC_MENU(String _mess) {
		String openType = ALLOC_MENU_OPEN_TYPE();
		String callbackType = "dialog".equalsIgnoreCase(openType) ? "reloadTab"
				: "closeCurrent";
		callbackType = "reloadTab";
		String mess = _mess == null ? "操作成功" : _mess;
		String rel = map.get("ALLOC_MENU.SHOW_LIST_REL");
		String path = map.get("ALLOC_MENU.SHOW_LIST_PATH");
		path = ConfigConstant.BASE_URL + path;
		String title = map.get("ALLOC_MENU.SHOW_LIST_TITLE");
		return new CallBackJson("200", mess, rel, path, callbackType, title)
				.toString();
	}

	public static String GET_ROLE_MENU_ACTION_RESULT() {
		return map.get("GET_ROLE_MENU_ACTION_RESULT");
	}
}
