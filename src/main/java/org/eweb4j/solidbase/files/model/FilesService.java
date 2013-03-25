package org.eweb4j.solidbase.files.model;

import java.io.File;

import org.eweb4j.mvc.view.PageMod;

public interface FilesService {

	String create(Files files, File uploadFile) throws FilesException;

	PageMod<Files> getPage(int p, int n) throws FilesException;

	void remove(Long id) throws FilesException;

	void batchRemove(Long[] ids) throws FilesException;

	void update(Files files) throws FilesException;

	Files get(Long id) throws FilesException;
	
}
