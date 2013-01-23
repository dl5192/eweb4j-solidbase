package org.eweb4j.solidbase.column;

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
 * @date 2012-12-3 下午10:21:58
 */
@Entity
@Table(name = "t_column")
public class Column {

	@Id
	private Long id;
	
	private String name;//栏目名称
	
	private String href;//栏目链接
	
	private int sort;//排序
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Column parent;//父栏目
	
	@OneToMany
	private List<Column> children = new ArrayList<Column>();//子栏目

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

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Column getParent() {
		return this.parent;
	}

	public void setParent(Column parent) {
		this.parent = parent;
	}

	public List<Column> getChildren() {
		return this.children;
	}

	public void setChildren(List<Column> children) {
		this.children = children;
	}
	
}
