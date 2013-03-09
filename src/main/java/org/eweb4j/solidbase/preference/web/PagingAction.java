package org.eweb4j.solidbase.preference.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.cache.Props;
import org.eweb4j.mvc.view.DataAssemUtil;
import org.eweb4j.mvc.view.ListPage;
import org.eweb4j.solidbase.preference.model.PreferCons;
import org.eweb4j.solidbase.preference.model.Preference;

@Path("${PreferenceConstant.MODEL_NAME}")
public class PagingAction {

	@Path("/list")
	@GET
	@POST
	public String doList(Map<String, Object> model) {

		List<Preference> pojos = new ArrayList<Preference>();
		Set<Entry<String, Map<String, String>>> set = Props.getProps().entrySet();
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

		try {
			ListPage listPage = new ListPage("preferences", null, pojos, null);
			listPage = DataAssemUtil.assemHead(listPage, pojos,PreferCons.getMap());

			model.put("random", Math.random());
			model.put("listPage", listPage);
		} catch (Exception e) {

		}
		
		return PreferCons.PAGING_ACTION_RESULT();
	}
}
