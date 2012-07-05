DROP DATABASE IF EXISTS solidbase.sql;
CREATE DATABASE solidbase.sql DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
SET FOREIGN_KEY_CHECKS=0;
USE solidbase.sql;

-- ----------------------------
-- Created by EWeb4J Framework 1.9-SNAPSHOT
-- at 2012-06-10 16:41:34
-- Models of org.eweb4j.solidbase.permission.model.Permission
-- Records of t_permission
-- ----------------------------
DROP TABLE IF EXISTS t_permission;
CREATE TABLE t_permission(
	id bigint (20)  NOT NULL  AUTO_INCREMENT , 
	name varchar (255)  NOT NULL , 
	resource_id bigint (20)  NOT NULL , 
	type bigint (20)  NOT NULL , 
	remark varchar (255)  NOT NULL , 
	add_time varchar (255)  NOT NULL , 
	modify_time varchar (255)  NOT NULL , 
	PRIMARY KEY (id), 
	KEY resource_id (resource_id), 
	CONSTRAINT resource_id FOREIGN KEY (resource_id) REFERENCES t_resource (id), 
	KEY type (type), 
	CONSTRAINT type FOREIGN KEY (type) REFERENCES t_code (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Created by EWeb4J Framework 1.9-SNAPSHOT
-- at 2012-06-10 16:41:34
-- Models of org.eweb4j.solidbase.preference.model.Preference
-- Records of t_preference
-- ----------------------------
DROP TABLE IF EXISTS t_preference;
CREATE TABLE t_preference(
	id bigint (20)  NOT NULL  AUTO_INCREMENT , 
	prop_id varchar (255)  NOT NULL , 
	param varchar (255)  NOT NULL , 
	value varchar (255)  NOT NULL , 
	remark varchar (255)  NOT NULL , 
	PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Created by EWeb4J Framework 1.9-SNAPSHOT
-- at 2012-06-10 16:41:34
-- Models of org.eweb4j.solidbase.user.model.User
-- Records of t_user
-- ----------------------------
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user(
	id bigint (20)  NOT NULL  AUTO_INCREMENT , 
	super_power varchar (255)  NOT NULL , 
	account varchar (255)  NOT NULL , 
	password varchar (255)  NOT NULL , 
	status varchar (255)  NOT NULL , 
	last_login_time varchar (255)  NOT NULL , 
	last_login_ip varchar (255)  NOT NULL , 
	register_time varchar (255)  NOT NULL , 
	true_name varchar (255)  NOT NULL , 
	email varchar (255)  NOT NULL , 
	id_num varchar (255)  NOT NULL , 
	fax varchar (255)  NOT NULL , 
	addr varchar (255)  NOT NULL , 
	office_phone varchar (255)  NOT NULL , 
	mobile_num varchar (255)  NOT NULL , 
	home_phone varchar (255)  NOT NULL , 
	available_period varchar (255)  NOT NULL , 
	add_time varchar (255)  NOT NULL , 
	modify_time varchar (255)  NOT NULL , 
	PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Created by EWeb4J Framework 1.9-SNAPSHOT
-- at 2012-06-10 16:41:34
-- Models of org.eweb4j.solidbase.department.model.Department
-- Records of t_department
-- ----------------------------
DROP TABLE IF EXISTS t_department;
CREATE TABLE t_department(
	id bigint (20)  NOT NULL  AUTO_INCREMENT , 
	code_id bigint (20)  NOT NULL , 
	depart_level bigint (20)  NOT NULL , 
	depart_cate bigint (20)  NOT NULL , 
	add_time varchar (255)  NOT NULL , 
	modify_time varchar (255)  NOT NULL , 
	PRIMARY KEY (id), 
	KEY code_id (code_id), 
	CONSTRAINT code_id FOREIGN KEY (code_id) REFERENCES t_code (id), 
	KEY depart_level (depart_level), 
	CONSTRAINT depart_level FOREIGN KEY (depart_level) REFERENCES t_code (id), 
	KEY depart_cate (depart_cate), 
	CONSTRAINT depart_cate FOREIGN KEY (depart_cate) REFERENCES t_code (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Created by EWeb4J Framework 1.9-SNAPSHOT
-- at 2012-06-10 16:41:34
-- Models of org.eweb4j.solidbase.user.model.UserActivityLog
-- Records of t_user_activity_log
-- ----------------------------
DROP TABLE IF EXISTS t_user_activity_log;
CREATE TABLE t_user_activity_log(
	id bigint (20)  NOT NULL  AUTO_INCREMENT , 
	user_id bigint (20)  NOT NULL , 
	user_name varchar (255)  NOT NULL , 
	user_account varchar (255)  NOT NULL , 
	activity varchar (255)  NOT NULL , 
	time varchar (255)  NOT NULL , 
	result varchar (255)  NOT NULL , 
	failure_cause varchar (255)  NOT NULL , 
	PRIMARY KEY (id), 
	KEY user_id (user_id), 
	CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES t_user (user_id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Created by EWeb4J Framework 1.9-SNAPSHOT
-- at 2012-06-10 16:41:34
-- Models of org.eweb4j.solidbase.role.model.Role
-- Records of t_role
-- ----------------------------
DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role(
	id bigint (20)  NOT NULL  AUTO_INCREMENT , 
	name varchar (255)  NOT NULL , 
	description varchar (255)  NOT NULL , 
	add_time varchar (255)  NOT NULL , 
	modify_time varchar (255)  NOT NULL , 
	PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Created by EWeb4J Framework 1.9-SNAPSHOT
-- at 2012-06-10 16:41:34
-- Models of org.eweb4j.solidbase.setting.Setting
-- Records of t_setting
-- ----------------------------
DROP TABLE IF EXISTS t_setting;
CREATE TABLE t_setting(
	id bigint (20)  NOT NULL  AUTO_INCREMENT , 
	user_default_role bigint (20) , 
	user_perm_control varchar (255)  NOT NULL , 
	PRIMARY KEY (id), 
	KEY user_default_role (user_default_role), 
	CONSTRAINT user_default_role FOREIGN KEY (user_default_role) REFERENCES t_role (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Created by EWeb4J Framework 1.9-SNAPSHOT
-- at 2012-06-10 16:41:34
-- Models of org.eweb4j.solidbase.code.model.Code
-- Records of t_code
-- ----------------------------
DROP TABLE IF EXISTS t_code;
CREATE TABLE t_code(
	id bigint (20)  NOT NULL  AUTO_INCREMENT , 
	code_value varchar (255)  NOT NULL , 
	remark varchar (255)  NOT NULL , 
	parent_id bigint (20)  NOT NULL , 
	type_id bigint (20)  NOT NULL , 
	add_time varchar (255)  NOT NULL , 
	modify_time varchar (255)  NOT NULL , 
	PRIMARY KEY (id), 
	KEY parent_id (parent_id), 
	CONSTRAINT parent_id FOREIGN KEY (parent_id) REFERENCES t_code (id), 
	KEY type_id (type_id), 
	CONSTRAINT type_id FOREIGN KEY (type_id) REFERENCES t_code (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Created by EWeb4J Framework 1.9-SNAPSHOT
-- at 2012-06-10 16:41:34
-- Models of org.eweb4j.solidbase.resource.model.Resource
-- Records of t_resource
-- ----------------------------
DROP TABLE IF EXISTS t_resource;
CREATE TABLE t_resource(
	id bigint (20)  NOT NULL  AUTO_INCREMENT , 
	uri varchar (255)  NOT NULL , 
	PRIMARY KEY (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Created by EWeb4J Framework 1.9-SNAPSHOT
-- at 2012-06-10 16:41:34
-- Records of t_perm_http_method
-- ----------------------------
DROP TABLE IF EXISTS t_perm_http_method;
CREATE TABLE t_perm_http_method(
	id bigint (20) NOT NULL AUTO_INCREMENT,
	perm_id bigint (20) ,
	http_method bigint (20) ,
	PRIMARY KEY (id), 
	KEY perm_id (perm_id), 
	CONSTRAINT t_perm_http_method_perm_id FOREIGN KEY (perm_id) REFERENCES t_permission (id), 
	KEY http_method (http_method), 
	CONSTRAINT t_perm_http_method_http_method FOREIGN KEY (http_method) REFERENCES t_code (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Created by EWeb4J Framework 1.9-SNAPSHOT
-- at 2012-06-10 16:41:34
-- Records of t_user_role
-- ----------------------------
DROP TABLE IF EXISTS t_user_role;
CREATE TABLE t_user_role(
	id bigint (20) NOT NULL AUTO_INCREMENT,
	user_id bigint (20) ,
	role_id bigint (20) ,
	PRIMARY KEY (id), 
	KEY user_id (user_id), 
	CONSTRAINT t_user_role_user_id FOREIGN KEY (user_id) REFERENCES t_user (id), 
	KEY role_id (role_id), 
	CONSTRAINT t_user_role_role_id FOREIGN KEY (role_id) REFERENCES t_role (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Created by EWeb4J Framework 1.9-SNAPSHOT
-- at 2012-06-10 16:41:34
-- Records of t_user_department
-- ----------------------------
DROP TABLE IF EXISTS t_user_department;
CREATE TABLE t_user_department(
	id bigint (20) NOT NULL AUTO_INCREMENT,
	user_id bigint (20) ,
	department_id bigint (20) ,
	PRIMARY KEY (id), 
	KEY user_id (user_id), 
	CONSTRAINT t_user_department_user_id FOREIGN KEY (user_id) REFERENCES t_user (id), 
	KEY department_id (department_id), 
	CONSTRAINT t_user_department_department_id FOREIGN KEY (department_id) REFERENCES t_department (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Created by EWeb4J Framework 1.9-SNAPSHOT
-- at 2012-06-10 16:41:34
-- Records of t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS t_role_permission;
CREATE TABLE t_role_permission(
	id bigint (20) NOT NULL AUTO_INCREMENT,
	role_id bigint (20) ,
	perm_id bigint (20) ,
	PRIMARY KEY (id), 
	KEY role_id (role_id), 
	CONSTRAINT t_role_permission_role_id FOREIGN KEY (role_id) REFERENCES t_role (id), 
	KEY perm_id (perm_id), 
	CONSTRAINT t_role_permission_perm_id FOREIGN KEY (perm_id) REFERENCES t_permission (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Created by EWeb4J Framework 1.9-SNAPSHOT
-- at 2012-06-10 16:41:34
-- Records of t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS t_role_menu;
CREATE TABLE t_role_menu(
	id bigint (20) NOT NULL AUTO_INCREMENT,
	role_id bigint (20) ,
	menu_id bigint (20) ,
	PRIMARY KEY (id), 
	KEY role_id (role_id), 
	CONSTRAINT t_role_menu_role_id FOREIGN KEY (role_id) REFERENCES t_role (id), 
	KEY menu_id (menu_id), 
	CONSTRAINT t_role_menu_menu_id FOREIGN KEY (menu_id) REFERENCES TreeMenu (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Created by EWeb4J Framework 1.9-SNAPSHOT
-- at 2012-06-10 16:41:34
-- Records of t_role_navmenu
-- ----------------------------
DROP TABLE IF EXISTS t_role_navmenu;
CREATE TABLE t_role_navmenu(
	id bigint (20) NOT NULL AUTO_INCREMENT,
	role_id bigint (20) ,
	navmenu_id bigint (20) ,
	PRIMARY KEY (id), 
	KEY role_id (role_id), 
	CONSTRAINT t_role_navmenu_role_id FOREIGN KEY (role_id) REFERENCES t_role (id), 
	KEY navmenu_id (navmenu_id), 
	CONSTRAINT t_role_navmenu_navmenu_id FOREIGN KEY (navmenu_id) REFERENCES NavMenu (id) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
