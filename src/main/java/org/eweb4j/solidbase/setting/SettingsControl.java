package org.eweb4j.solidbase.setting;

import java.util.Collection;
import java.util.Map;

import javax.ws.rs.QueryParam;

import org.eweb4j.component.dwz.DWZ;
import org.eweb4j.component.dwz.DWZCons;
import org.eweb4j.ioc.IOC;
import org.eweb4j.mvc.action.annotation.Singleton;
import org.eweb4j.solidbase.role.model.Role;
import org.eweb4j.solidbase.role.model.RoleCons;
import org.eweb4j.solidbase.role.model.RoleException;
import org.eweb4j.solidbase.role.model.RoleService;

@Singleton
public class SettingsControl {

	private DWZ dwz = IOC.getBean(DWZCons.IOC_DWZ_BEAN_ID());
	private RoleService roleService = IOC.getBean(RoleCons.IOC_SERVICE_BEAN_ID());
	
	public String doAtGetOrPost(Map<String, Object> model){
		
		model.put("pojo", Setting.inst.find().first());
		
		Collection<Role> roles = null;
		try {
			roles = roleService.getAll();
		} catch (RoleException e) {
			e.printStackTrace();
			return dwz.getFailedJson(e.toString()).toString();
		}
		
		model.put("roles", roles);
		
		return "forward:preference/view/setting.jsp";
	}
	
	public String doAtPut(@QueryParam("setting") Setting setting){
		
		try{
			setting.save();
		}catch(Exception e){
			return dwz.getFailedJson(e.toString()).toString();
		}
		
		return dwz.getSuccessJson("更新设置成功", "setting", "", "", "系统参数设置").toString();
	}
	
}
