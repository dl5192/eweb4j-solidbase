package org.eweb4j.solidbase.department.model;

public class DepartmentException extends Exception {

	private static final long serialVersionUID = -5820438995905699789L;

	public DepartmentException(String mess) {
		super(mess);
	}

	public DepartmentException(String mess, Exception e) {
		super(mess, e);
	}
}
