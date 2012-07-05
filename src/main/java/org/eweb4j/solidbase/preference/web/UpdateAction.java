package org.eweb4j.solidbase.preference.web;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.ioc.IOC;
import org.eweb4j.solidbase.preference.model.PreferCons;

@Path("${PreferenceConstant.MODEL_NAME}")
public class UpdateAction {

	private DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());

	@Path("/")
	@POST
	@PUT
	public String doPOST(@QueryParam("key") String key, @QueryParam("value") String value) {
		try {
			PreferCons.write(key, value);
			return PreferCons.DWZ_SUCCESS_JSON("写入成功");
		} catch (Exception e) {
			return dwz.getFailedJson(e.getMessage()).toString();
		}
	}

}
