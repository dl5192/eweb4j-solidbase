package org.eweb4j.solidbase.filecate.model;

import java.util.ArrayList;
import java.util.Collection;

import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.orm.Db;
import org.eweb4j.orm.Page;

public class FileCateServiceImpl implements FileCateService {

	public void create(FileCate fileCate) throws FileCateException {
		if (fileCate == null)
			throw new FileCateException("can not be null");
		
		Db.ar(fileCate).create();
	}

	public PageMod<FileCate> getPage(int p, int n) throws FileCateException {
		if (p <= 0)
			p = 1;
		if (n <= 0)
			n = 20;
		
		Page<FileCate> page = 
			Db.ar(FileCate.class)
				.dao()
				.fetch("thumb")
				.selectAll()
				.getPage(p, n);
		
		Collection<FileCate> cates = page.getList();
		return new PageMod<FileCate>(cates, page.getTotalRowCount());
	}

	public void remove(Long id) throws FileCateException {
		FileCate c = new FileCate();
		c.setId(id);
		Db.ar(c).delete();
	}

	public void batchRemove(Long[] ids) throws FileCateException {
		if (ids == null || ids.length == 0)
			throw new FileCateException("nothing to remove");
		
		Collection<FileCate> cates = new ArrayList<FileCate>(ids.length);
		for (Long id : ids){
			FileCate c = new FileCate();
			c.setId(id);
			cates.add(c);
		}
		
		Db.batchDelete(cates.toArray());
	}

	public void update(FileCate fileCate) throws FileCateException {
		if (fileCate == null)
			throw new FileCateException("can not be null");
		
		Db.ar(fileCate).save();
	}

	public FileCate get(Long id) throws FileCateException {
		FileCate pojo = Db.ar(FileCate.class).findById(id);
		if (pojo == null)
			throw new FileCateException("not found");
		
		return pojo;
	}

	public Collection<FileCate> getAll() throws FileCateException {
		return Db.ar(FileCate.class).findAll();
	}

}
