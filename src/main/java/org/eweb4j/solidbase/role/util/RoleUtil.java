package org.eweb4j.solidbase.role.util;

import org.eweb4j.component.dwz.menu.navmenu.NavMenu;
import org.eweb4j.component.dwz.menu.treemenu.TreeMenu;
import org.eweb4j.solidbase.permission.model.Permission;
import org.eweb4j.solidbase.role.model.Role;

public class RoleUtil {
	public static void setPermission(final long[] permissionIds, final Role role) {
		if (permissionIds != null && permissionIds.length > 0) {
			for (long permId : permissionIds) {
				Permission perm = new Permission();
				perm.setPermId(permId);
				role.getPermissions().add(perm);
			}
		} else {
			role.setPermissions(null);
		}
	}

	public static void setMenu(final long[] treeMenuIds, final Role role) {
		if (treeMenuIds != null && treeMenuIds.length > 0) {
			for (long treeMenuId : treeMenuIds) {
				TreeMenu tm = new TreeMenu();
				tm.setTreeMenuId(treeMenuId);
				role.getMenus().add(tm);
			}
		} else {
			role.setMenus(null);
		}
	}

	public static void setNavMenu(long[] navMenuIds, Role role) {
		if (navMenuIds != null && navMenuIds.length > 0) {
			for (long navMenuId : navMenuIds) {
				NavMenu nm = new NavMenu();
				nm.setNavMenuId(navMenuId);
				role.getNavMenus().add(nm);
			}
		} else {
			role.setNavMenus(null);
		}
	}
}
