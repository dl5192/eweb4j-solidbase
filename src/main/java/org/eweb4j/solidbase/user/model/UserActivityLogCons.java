package org.eweb4j.solidbase.user.model;

import java.util.Map;

import org.eweb4j.cache.Props;


public class UserActivityLogCons {
	private final static String propId = "UserActivityLogConstant";
	private final static Map<String, String> map = Props.getMap(propId);

	public static String IOC_SERVICE_BEAN_ID() {
		return map.get("IOC_SERVICE_BEAN_ID");
	}

	public static Map<String, String> getMap() {
		return map;
	}
}
