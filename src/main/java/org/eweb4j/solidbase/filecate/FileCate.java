package org.eweb4j.solidbase.filecate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	private String thumb;// 类别缩略图
	
	private int sort;// 类别排序
	
	@ManyToOne(fetch = FetchType.LAZY)
	private FileCate parent;// 父类别

	@OneToMany(mappedBy = "parent")
	private List<FileCate> children = new ArrayList<FileCate>();// 子类别

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

	public String getThumb() {
		return this.thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public FileCate getParent() {
		return this.parent;
	}

	public void setParent(FileCate parent) {
		this.parent = parent;
	}

	public List<FileCate> getChildren() {
		return this.children;
	}

	public void setChildren(List<FileCate> children) {
		this.children = children;
	}
	
}
