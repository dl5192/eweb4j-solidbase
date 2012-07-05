package org.eweb4j.solidbase.permission.model;

import java.util.Comparator;

import org.eweb4j.mvc.config.bean.ActionConfigBean;


public class ActionComparator implements Comparator<ActionConfigBean> {

	public int compare(ActionConfigBean o1, ActionConfigBean o2) {
		
		String uri1 = o1.getUriMapping();
		String uri2 = o2.getUriMapping();
		
		return uri1.compareTo(uri2);
		
	}

}
