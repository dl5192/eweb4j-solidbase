package org.eweb4j.solidbase.main.model;

import java.util.Map;

import org.eweb4j.cache.Props;

public class MainCons {
	private final static String propId = "MainConstant";
	private final static Map<String, String> map = Props.getMap(propId);

	public static String COPY_RIGHT() {
		return map.get("COPY_RIGHT");
	}

	public static String TITLE() {
		return map.get("TITLE");
	}

	public static String MODEL_NAME() {
		return map.get("MODEL_NAME");
	}
}
