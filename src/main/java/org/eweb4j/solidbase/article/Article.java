package org.eweb4j.solidbase.article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eweb4j.solidbase.articlecate.ArticleCate;
import org.eweb4j.solidbase.gallery.Gallery;

/**
 * TODO
 * @author weiwei l.weiwei@163.com
 * @date 2012-12-3 下午09:50:21
 */
@Entity
@Table(name = "t_article")
public class Article {
	@Id
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ArticleCate cate;// 文章分类
	
	private String title;// 文章标题
	
	private String intro;// 文章简介
	
	private String content;// 文章内容
	
	@Column(name = "count_view")
	private long countView;// 文章被查看次数
	
	private String thumb;//文章缩略图
	
	@ManyToOne
	private Gallery gallery ;// 文章图集
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArticleCate getCate() {
		return this.cate;
	}

	public void setCate(ArticleCate cate) {
		this.cate = cate;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCountView() {
		return this.countView;
	}

	public void setCountView(long countView) {
		this.countView = countView;
	}

	public String getThumb() {
		return this.thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public Gallery getGallery() {
		return this.gallery;
	}

	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}

}
