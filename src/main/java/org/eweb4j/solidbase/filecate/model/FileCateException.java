package org.eweb4j.solidbase.filecate.model;

public class FileCateException extends Exception {

	private static final long serialVersionUID = 1L;

	public FileCateException(String mess) {
		super(mess);
	}

	public FileCateException(String mess, Exception e) {
		super(mess, e);
	}
}
