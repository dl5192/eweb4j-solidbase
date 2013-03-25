package org.eweb4j.solidbase.files.model;

public class FilesException extends Exception {

	private static final long serialVersionUID = 1L;

	public FilesException(String mess) {
		super(mess);
	}

	public FilesException(String mess, Exception e) {
		super(mess, e);
	}
}
