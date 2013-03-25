package org.eweb4j.solidbase.setting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eweb4j.mvc.validator.annotation.Enumer;
import org.eweb4j.mvc.validator.annotation.Required;
import org.eweb4j.orm.Model;
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
	
	@Column(name="file_base_dir")
	private String fileBaseDir;//文件保存的基本路径

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

	public String getFileBaseDir() {
		return fileBaseDir;
	}

	public void setFileBaseDir(String fileBaseDir) {
		this.fileBaseDir = fileBaseDir;
	}
	
}
