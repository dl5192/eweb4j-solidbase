package org.eweb4j.solidbase.code.model;

public class CodeException extends Exception {

	private static final long serialVersionUID = 7335697528793734366L;

	public CodeException(String mess) {
		super(mess);
	}

	public CodeException(String mess, Exception e) {
		super(mess, e);
	}

}
