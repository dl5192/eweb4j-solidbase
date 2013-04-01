package org.eweb4j.solidbase.files.model;

import java.io.File;

import org.eweb4j.config.ConfigConstant;
import org.eweb4j.mvc.view.PageMod;
import org.eweb4j.orm.Db;
import org.eweb4j.orm.Page;
import org.eweb4j.solidbase.setting.Setting;
import org.eweb4j.util.CommonUtil;
import org.eweb4j.util.FileUtil;

public class FilesServiceImpl implements FilesService{
	
	public String create(Files files, File uploadFile) throws FilesException {
		if (uploadFile == null)
			throw new FilesException("upload file can not be null");
		
		if (files == null)
			throw new FilesException("files can not be null");
		
		//检查保存文件的目录是否合法
		Setting setting = Db.ar(Setting.class).find().first();
		String uploadBaseDir = setting.getFileBaseDir().replace("${RootPath}", ConfigConstant.ROOT_PATH);
		if (uploadBaseDir == null || uploadBaseDir.trim().length() == 0)
			throw new FilesException("Setting.FileBaseDir can not be empty");
		
		File baseDir = new File(uploadBaseDir);
		if (!baseDir.exists())
			throw new FilesException("Setting.FileBaseDir -> " + baseDir.getAbsolutePath() + " not found");
		
		if (!baseDir.isDirectory())
			throw new FilesException("Setting.FileBaseDir -> " + baseDir.getAbsolutePath() + " must be a directory");
		
		String base = baseDir.getAbsolutePath();
		
		//拷贝上传的文件到指定目录
		String dateDir = CommonUtil.getNowTime("yyyy-MM-dd");
		File _dateFolder = new File(base+"/" + dateDir);
		if (!_dateFolder.exists())
			_dateFolder.mkdir();
		
		String fileName = CommonUtil.md5(CommonUtil.getNow()+String.valueOf(CommonUtil.random(1, 100)));
		fileName = fileName + "." + FileUtil.getExt(uploadFile);
		String savePath = dateDir + "/" + fileName;
		
		File dst = new File(base + "/" + savePath);
		FileUtil.copy(uploadFile, dst);
		
		files.setSavePath(savePath);
		
		Db.ar(files).create();
		
		return files.getSavePath();
	}

	public PageMod<Files> getPage(int p, int n) throws FilesException {
		if (p <= 0)
			p = 1;
		if (n <= 0)
			n = 20;
		
		Page<Files> page = 
			Db.ar(Files.class)
				.dao()
				.fetch("cate")
				.selectAll()
				.getPage(p, n);
		
		return new PageMod<Files>(page.getList(), page.getTotalRowCount());
	}

	public void remove(Long id) throws FilesException {
		Files c = Db.ar(Files.class).findById(id);
		if (c == null)
			throw new FilesException("id->"+id+" not found");
		
		Setting set = Db.ar(Setting.class).find().first();
		String baseDir = set.getFileBaseDir();
		File file = new File(baseDir + "/" + c.getSavePath());
		if (file.exists()) {
			boolean isOk = FileUtil.deleteFile(file);
			if (!isOk)
				throw new FilesException("file->"+file.getAbsolutePath()+" can not delete");
		}		
		
		Db.ar(c).delete();
	}

	public void batchRemove(Long[] ids) throws FilesException {
		if (ids == null || ids.length == 0)
			throw new FilesException("nothing to remove");
		
		for (Long id : ids){
			remove(id);
		}
	}

	public void update(Files files) throws FilesException {
		if (files == null)
			throw new FilesException("can not be null");
		if (files.getId() == null || files.getId() <= 0)
			throw new FilesException("files not found");
		
		Db.ar(files).save();
	}

	public Files get(Long id) throws FilesException {
		Files pojo = Db.ar(Files.class).findById(id);
		if (pojo == null)
			throw new FilesException("not found");
		
		return pojo;
	}
}
