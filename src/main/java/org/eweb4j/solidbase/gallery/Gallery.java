package org.eweb4j.solidbase.gallery;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.eweb4j.solidbase.files.model.Files;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2012-12-3 下午09:54:55
 */
@Entity
@Table(name = "t_gallery")
public class Gallery {

	@Id
	private Long id;
	
	private String name; // 图集名字
	
	private String intro; // 图集简介
	
	private String thumb; // 图集缩略图
	
	private int width; // 图集宽度
	
	private int height; // 图集高度
	
	@ManyToMany
	@JoinTable(name = "t_gallery_files_index", joinColumns = @JoinColumn(name = "gallery_id"), inverseJoinColumns = @JoinColumn(name = "files_id"))
	private List<Files> images = new ArrayList<Files>(); // 图集图片

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

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getThumb() {
		return this.thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public List<Files> getImages() {
		return this.images;
	}

	public void setImages(List<Files> images) {
		this.images = images;
	}
	
}
