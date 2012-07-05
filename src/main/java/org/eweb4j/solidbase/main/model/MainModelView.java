package org.eweb4j.solidbase.main.model;

import java.util.List;

import org.eweb4j.component.dwz.menu.navmenu.NavMenu;


/**
 * dwz后台主页面的视图模型
 * @author weiwei
 *
 */
public class MainModelView{
	private String title;//标题
	private List<NavMenu> navMenus;//导航菜单
	private String treeMenus;//树形菜单
	private String copyRight ;//版权
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<NavMenu> getNavMenus() {
		return navMenus;
	}
	public void setNavMenus(List<NavMenu> navMenus) {
		this.navMenus = navMenus;
	}
	public String getTreeMenus() {
		return treeMenus;
	}
	public void setTreeMenus(String treeMenus) {
		this.treeMenus = treeMenus;
	}
	public String getCopyRight() {
		return copyRight;
	}
	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}
}
