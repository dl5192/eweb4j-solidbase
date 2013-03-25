package org.eweb4j.solidbase.files.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eweb4j.solidbase.filecate.model.FileCate;

/**
 * 文件
 * @author weiwei l.weiwei@163.com
 * @date 2012-12-3 下午09:57:32
 */
@Entity
@Table(name = "t_files")
public class Files {

	@Id
	private Long id;
	
	@Column(name = "save_path")
	private String savePath; //文件保存相对路径
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cate_id")
	private FileCate cate; //文件类别
	
	@Column(name = "display_name")
	private String displayName ;// 文件名字 
	
	private String intro ;; // 文件简介
	
	private long size; // 文件大小

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSavePath() {
		return this.savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public FileCate getCate() {
		return this.cate;
	}

	public void setCate(FileCate cate) {
		this.cate = cate;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public long getSize() {
		return this.size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
}
