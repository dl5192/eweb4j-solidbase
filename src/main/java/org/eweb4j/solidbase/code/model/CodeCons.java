package org.eweb4j.solidbase.code.model;

import java.util.Map;

import org.eweb4j.cache.Props;
import org.eweb4j.config.ConfigConstant;
import org.eweb4j.mvc.view.CallBackJson;
import org.eweb4j.util.RegexList;

/**
 * 常量.从配置文件中读取.
 * 
 * @author weiwei
 * 
 */
public class CodeCons {
	/**
	 * 配置文件ID (在eweb4j框架启动培植文件中定义)
	 */
	private final static String propId = "CodeConstant";
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
		path = ConfigConstant.BASE_URL + path;
		String title = map.get("SHOW_LIST_TITLE");
		return new CallBackJson("200", mess, rel, path, callbackType, title)
				.toString();
	}

	public static String OPEN_TYPE() {
		return map.get("OPEN_TYPE");
	}

	public static String TYPE_NOT_FOUND_MESS() {
		return map.get("TYPE_NOT_FOUND_MESS");
	}

	public static String PARENT_NOT_FOUND_MESS() {
		return map.get("PARENT_NOT_FOUND_MESS");
	}

	public static String CODE_NOT_FOUND_MESS() {
		return map.get("CODE_NOT_FOUND_MESS");
	}

	public static String DUP_CODE_VALUE_MESS() {
		return map.get("DUP_CODE_VALUE_MESS");
	}

	public static String DATA_ACCESS_ERR() {
		return map.get("DATA_ACCESS_ERR");
	}

	public static String META_TYPE() {
		return map.get("META_TYPE");
	}

	public static String TOP_TYPE() {
		return map.get("TOP_TYPE");
	}

	public static String INVALID_CODE() {
		return map.get("INVALID_CODE");
	}

	public static long META_CODE_ID() {
		String id = map.get("META_CODE_ID");

		if (id.matches(RegexList.integer_regexp))
			return Long.parseLong(id);

		return -1;
	}

	public static long TOP_CODE_ID() {
		String id = map.get("TOP_CODE_ID");

		if (id.matches(RegexList.integer_regexp))
			return Long.parseLong(id);

		return -1;
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
}
