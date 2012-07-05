package org.eweb4j.solidbase.preference.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eweb4j.cache.Props;

public class PreferStore {
	private static Set<Entry<String, Map<String, String>>> set = Props.getProps().entrySet();

	public static List<Preference> queryList() {
		List<Preference> pojos = new ArrayList<Preference>();

		int id = 0;
		for (Entry<String, Map<String, String>> entry : set) {
			String propId = entry.getKey();

			Map<String, String> prop = entry.getValue();
			for (Entry<String, String> e : prop.entrySet()) {
				id++;
				Preference preference = new Preference();
				preference.setId(id);
				preference.setPropId(propId);
				preference.setParam(e.getKey());
				preference.setValue(e.getValue());
				pojos.add(preference);
			}
		}

		return pojos;
	}

	public static Preference queryOne(long properId) {
		int id = 0;
		for (Entry<String, Map<String, String>> entry : set) {
			String propId = entry.getKey();

			Map<String, String> prop = entry.getValue();
			for (Entry<String, String> e : prop.entrySet()) {
				id++;
				if (properId == id) {
					Preference preference = new Preference();
					preference.setId(id);
					preference.setPropId(propId);
					preference.setParam(e.getKey());
					preference.setValue(e.getValue());
					return preference;
				}

			}
		}

		return null;
	}
	
	public static void update(Preference preference){
		
	}
}
