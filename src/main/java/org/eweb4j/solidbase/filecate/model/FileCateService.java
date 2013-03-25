package org.eweb4j.solidbase.filecate.model;

import java.util.Collection;

import org.eweb4j.mvc.view.PageMod;

public interface FileCateService {

	void create(FileCate fileCate) throws FileCateException;

	PageMod<FileCate> getPage(int p, int n) throws FileCateException;

	void remove(Long id) throws FileCateException;

	void batchRemove(Long[] ids) throws FileCateException;

	void update(FileCate fileCate) throws FileCateException;

	FileCate get(Long id) throws FileCateException;

	Collection<FileCate> getAll() throws FileCateException;

}
