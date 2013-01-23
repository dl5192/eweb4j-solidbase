package org.eweb4j.solidbase.ad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eweb4j.solidbase.files.Files;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2012-12-3 下午10:15:53
 */
@Entity
@Table(name = "t_ad")
public class AD {
	@Id
	private Long id;
	
	private String title;// 广告标题
	
	private String intro;// 广告描述
	
	private String href;// 广告链接
	
	@ManyToOne
	private Files image;// 广告图
	
	private int sort;// 排序
	
	@Column(name = "open_type")
	private String openType;//打开方式

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Files getImage() {
		return this.image;
	}

	public void setImage(Files image) {
		this.image = image;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getOpenType() {
		return this.openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}
	
}
