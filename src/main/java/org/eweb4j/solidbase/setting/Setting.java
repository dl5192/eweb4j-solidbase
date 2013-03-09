package org.eweb4j.solidbase.setting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eweb4j.mvc.validator.annotation.Enumer;
import org.eweb4j.mvc.validator.annotation.Required;
import org.eweb4j.orm.Model;
import org.eweb4j.solidbase.files.Files;
import org.eweb4j.solidbase.role.model.Role;

/**
 * 系统设置，随时增加字段
 * @author weiwei
 *
 */
@Entity
@Table(name="t_setting")
public class Setting extends Model<Setting>{

	public final static Setting inst = new Setting();
	
	@OneToOne
	@JoinColumn(name="user_default_role")
	private Role userDefaultRole;
	
	@Column(name="user_perm_control")
	@Required
	@Enumer(words={"yes", "no"})
	private String userPermControl;//yes | no
	
	@Column(name = "site_title")
	private String siteTitle;//网站标题
	
	@Column(name = "seo_keyword")
	private String seoKeyword;//网站seo关键词
	
	@OneToOne
	@JoinColumn(name="favicon_id")
	private Files favicon;//网站favicon
	
	@Column(name = "seo_desc")
	private String seoDesc;//网站SEO描述
	
	@OneToOne
	@JoinColumn(name="logo_id")
	private Files logo;//网站LOGO图片
	
	@Column(name = "foot_info")
	private String footInfo;//网站底部信息
	
	@Column(name = "hot_num")
	private Integer hotNum = 10;//网站热门文章列表条目数
	
	@Column(name = "new_num")
	private Integer newNum = 10;//网站最新文章列表条目数

	public Role getUserDefaultRole() {
		return userDefaultRole;
	}

	public void setUserDefaultRole(Role userDefaultRole) {
		this.userDefaultRole = userDefaultRole;
	}

	public String getUserPermControl() {
		return userPermControl;
	}

	public void setUserPermControl(String userPermControl) {
		this.userPermControl = userPermControl;
	}

	public String getSiteTitle() {
		return this.siteTitle;
	}

	public void setSiteTitle(String siteTitle) {
		this.siteTitle = siteTitle;
	}

	public String getSeoKeyword() {
		return this.seoKeyword;
	}

	public void setSeoKeyword(String seoKeyword) {
		this.seoKeyword = seoKeyword;
	}

	public Files getFavicon() {
		return this.favicon;
	}

	public void setFavicon(Files favicon) {
		this.favicon = favicon;
	}

	public String getSeoDesc() {
		return this.seoDesc;
	}

	public void setSeoDesc(String seoDesc) {
		this.seoDesc = seoDesc;
	}

	public Files getLogo() {
		return this.logo;
	}

	public void setLogo(Files logo) {
		this.logo = logo;
	}

	public String getFootInfo() {
		return this.footInfo;
	}

	public void setFootInfo(String footInfo) {
		this.footInfo = footInfo;
	}

	public Integer getHotNum() {
		return this.hotNum;
	}

	public void setHotNum(Integer hotNum) {
		this.hotNum = hotNum;
	}

	public Integer getNewNum() {
		return this.newNum;
	}

	public void setNewNum(Integer newNum) {
		this.newNum = newNum;
	}
	
}
