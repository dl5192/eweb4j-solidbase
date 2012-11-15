package org.eweb4j.solidbase.department.model;

import java.util.Map;

import org.eweb4j.cache.Props;
import org.eweb4j.mvc.config.MVCConfigConstant;
import org.eweb4j.mvc.view.CallBackJson;

public class DepartmentCons {
	/**
	 * 配置文件ID (在eweb4j框架启动配置文件中定义)
	 */
	private final static String propId = "DepartmentConstant";
	private static Map<String, String> map = Props.getMap(propId);

	public static Map<String, String> getMap() {
		return map;
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
		path = MVCConfigConstant.BASE_URL + path;
		String title = map.get("SHOW_LIST_TITLE");
		return new CallBackJson("200", mess, rel, path, callbackType, title)
				.toString();
	}

	public static String OPEN_TYPE() {
		return map.get("OPEN_TYPE");
	}

	public static String IN_COMPLETE_DEPARTMENT_INFO_MESS() {
		return map.get("IN_COMPLETE_DEPARTMENT_INFO_MESS");
	}

	public static String DEPARTMENT_NOT_FOUND_MESS() {
		return map.get("DEPARTMENT_NOT_FOUND_MESS");
	}

	public static String DUP_DEPARTMENT_NAME_MESS() {
		return map.get("DUP_DEPARTMENT_NAME_MESS");
	}

	public static String DUP_DEPARTMENT_CODE_VALUE_MESS() {
		return map.get("DUP_DEPARTMENT_CODE_VALUE_MESS");
	}

	public static String LEVEL_NOT_FOUND_MESS() {
		return map.get("LEVEL_NOT_FOUND_MESS");
	}

	public static String PARENT_DEPARTMENT_NOT_FOUND_MESS() {
		return map.get("PARENT_DEPARTMENT_NOT_FOUND_MESS");
	}

	public static String DEPARTMENT_CATE_NOT_FOUND_MESS() {
		return map.get("DEPARTMENT_CATE_NOT_FOUND_MESS");
	}

	public static String DATA_ACCESS_ERR() {
		return map.get("DATA_ACCESS_ERR");
	}

	public static String DEPARTMENT_TYPE_CODE_VALUE() {
		return map.get("DEPARTMENT_TYPE_CODE_VALUE");
	}

	public static String LEVEL_TYPE_CODE_VALUE() {
		return map.get("LEVEL_TYPE_CODE_VALUE");
	}

	public static String DEPARTMENT_CATE_TYPE_CODE_VALUE() {
		return map.get("DEPARTMENT_CATE_TYPE_CODE_VALUE");
	}

	public static String PARENT_CAN_NOT_SAME_TO_SELFT_MESS() {
		return map.get("PARENT_CAN_NOT_SAME_TO_SELFT_MESS");
	}

	public static String DEPARTMENT_NOT_SELECTED_MESS() {
		return "请选择部门！";
	}

	public static String EDIT_ACTION_RESULT() {
		return map.get("EDIT_ACTION_RESULT");
	}

	public static String NEW_ACTION_RESULT() {
		return map.get("NEW_ACTION_RESULT");
	}

	public static String PAGING_ACTION_RESULT() {
		return map.get("PAGING_ACTION_RESULT");
	}

	public static String CODE_DATA_OP_EXP() {
		return "代码数据接口操作异常";
	}
}
