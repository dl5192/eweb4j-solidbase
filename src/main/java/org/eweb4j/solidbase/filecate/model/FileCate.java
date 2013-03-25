package org.eweb4j.solidbase.filecate.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eweb4j.solidbase.files.model.Files;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2012-12-3 下午10:10:38
 */
@Entity
@Table(name = "t_file_cate")
public class FileCate {

	@Id
	private Long id;
	
	private String name;// 类别名称
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="thumb_id")
	private Files thumb;// 类别缩略图
	
	private int sort;// 类别排序
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Files getThumb() {
		return thumb;
	}

	public void setThumb(Files thumb) {
		this.thumb = thumb;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
}
