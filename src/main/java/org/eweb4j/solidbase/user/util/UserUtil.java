package org.eweb4j.solidbase.user.util;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.eweb4j.solidbase.department.model.Department;
import org.eweb4j.solidbase.role.model.Role;
import org.eweb4j.solidbase.user.model.User;
import org.eweb4j.util.StringUtil;

public class UserUtil {
	public static boolean isValid(User user) {
		boolean result = true;
		String format = "yyyy-MM-dd";
		String availablePeriod = user.getAvailablePeriod();
		if (availablePeriod == null || availablePeriod.trim().length() == 0) {
			result = true;
		} else {

			SimpleDateFormat sdf = new SimpleDateFormat(format);
			String now_str = StringUtil.getNowTime(format);
			try {
				Date date = sdf.parse(availablePeriod);
				Date now = sdf.parse(now_str);

				if (now.after(date)) {
					result = false;
				}

			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

		user.setIsValid(result);
		return result;
	}

	public static void setRole(long[] roleIds, User user) {
		if (roleIds != null && roleIds.length > 0) {
			for (long roleId : roleIds) {
				Role role = new Role();
				role.setRoleId(roleId);
				user.getRoles().add(role);
			}
		} else {
			user.setRoles(null);
		}
	}

	public static void setDepart(long[] departIds, User user) {
		if (departIds != null && departIds.length > 0) {
			for (long departId : departIds) {
				Department depart = new Department();
				depart.setDepartId(departId);
				user.getDepartments().add(depart);
			}
		} else {
			user.setDepartments(null);
		}
	}

	public static void main(String[] args) throws Exception {
		String format = "yyyy-MM-dd";
		String date = "2011-02-07";
		String now_str = StringUtil.getNowTime(format);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		System.out.println("now_str-->" + now_str);
		Date when = sdf.parse(date);
		Date now = sdf.parse(now_str);

		System.out.println(now.after(when));
	}
}
