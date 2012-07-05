package org.eweb4j.solidbase.preference.web;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eweb4j.solidbase.preference.model.PreferCons;

@Path("${PreferenceConstant.MODEL_NAME}")
public class EditAction {
	
	@GET
	@POST
	@Path("/{preferId}/edit")
	public String doEdit(){
		return PreferCons.EDIT_ACTION_RESULT();
	}
	
}
