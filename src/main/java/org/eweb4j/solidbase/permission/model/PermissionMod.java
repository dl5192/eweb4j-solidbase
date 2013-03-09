package org.eweb4j.solidbase.permission.model;

import java.util.ArrayList;
import java.util.Collection;

import org.eweb4j.solidbase.code.model.Code;

public class PermissionMod {
	private Code permType;
	private Collection<Permission> permissions = new ArrayList<Permission>();

	public Code getPermType() {
		return permType;
	}

	public void setPermType(Code permType) {
		this.permType = permType;
	}

	public Collection<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Collection<Permission> permissions) {
		this.permissions = permissions;
	}

}
