package org.eweb4j.solidbase.preference.model;

import java.io.IOException;
import java.util.Map;

import org.eweb4j.cache.Props;
import org.eweb4j.mvc.config.MVCConfigConstant;
import org.eweb4j.mvc.view.CallBackJson;

public class PreferCons {
	private final static String propId = "PreferenceConstant";

	private static Map<String, String> map = Props.getMap(propId);

	public static void write(String key, Object value) throws Exception {
		try {
			Props.writeProp(propId, key, value);
		} catch (IOException e) {
			throw new Exception("系统参数写入失败", e);
		}
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
	
	

	private static String OPEN_TYPE() {
		return map.get("OPEN_TYPE");
	}

	public String MODEL_NAME() {
		return map.get("MODEL_NAME");
	}

	public static Map<String, String> getMap() {
		return map;
	}

	public static String EDIT_ACTION_RESULT() {
		return map.get("EDIT_ACTION_RESULT");
	}

	public static String PAGING_ACTION_RESULT() {
		return map.get("PAGING_ACTION_RESULT");
	}

}
