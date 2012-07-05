package org.eweb4j.solidbase.permission.model;

import java.util.ArrayList;
import java.util.List;

import org.eweb4j.solidbase.code.model.Code;

public class PermissionMod {
	private Code permType;
	private List<Permission> permissions = new ArrayList<Permission>();

	public Code getPermType() {
		return permType;
	}

	public void setPermType(Code permType) {
		this.permType = permType;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
