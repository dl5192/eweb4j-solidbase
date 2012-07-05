package org.eweb4j.solidbase.role.model;

public class RoleException extends Exception {

	private static final long serialVersionUID = 6268349385582688120L;

	public RoleException(String mess) {
		super(mess);
	}

	public RoleException(String mess, Exception e) {
		super(mess, e);
	}

}
