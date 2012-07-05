package org.eweb4j.solidbase.resource.model;

public class ResourceException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResourceException(String mess) {
		super(mess);
	}

	public ResourceException(String mess, Exception e) {
		super(mess, e);
	}

}
