package org.eweb4j.solidbase.role.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.eweb4j.component.dwz.menu.navmenu.NavMenu;
import org.eweb4j.component.dwz.menu.treemenu.TreeMenu;
import org.eweb4j.solidbase.permission.model.Permission;
import org.eweb4j.util.JsonConverter;

/**
 * 角色-持久化对象
 * 
 * @author weiwei[l.weiwei@163.com]
 * 
 */
@Entity
@Table(name = "t_role")
public class Role implements Serializable {

	private static final long serialVersionUID = -4887320664899008770L;
	@Id
	@Column(name = "id")
	private long roleId;// 自增长ID
	private String name;// 角色名称
	
	private String description;// 角色描述

	@ManyToMany
	@JoinTable(name = "t_role_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "perm_id"))
	private List<Permission> permissions = new ArrayList<Permission>();

	@ManyToMany
	@JoinTable(name = "t_role_menu", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
	private List<TreeMenu> menus = new ArrayList<TreeMenu>();

	@ManyToMany
	@JoinTable(name = "t_role_navmenu", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "navmenu_id"))
	private List<NavMenu> navMenus = new ArrayList<NavMenu>();

	@Column(name = "add_time")
	protected String addTime;

	@Column(name = "modify_time")
	protected String modifyTime;

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Role() {
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<TreeMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<TreeMenu> menus) {
		this.menus = menus;
	}

	public List<NavMenu> getNavMenus() {
		return navMenus;
	}

	public void setNavMenus(List<NavMenu> navMenus) {
		this.navMenus = navMenus;
	}

	public String toString() {
		return JsonConverter.convert(this);
	}
}
