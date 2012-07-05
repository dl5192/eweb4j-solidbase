package org.eweb4j.solidbase.setting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eweb4j.mvc.validator.annotation.Enumer;
import org.eweb4j.mvc.validator.annotation.Int;
import org.eweb4j.mvc.validator.annotation.Required;
import org.eweb4j.mvc.validator.annotation.Size;
import org.eweb4j.orm.Model;
import org.eweb4j.solidbase.role.model.Role;

/**
 * 系统设置，随时增加字段
 * @author weiwei
 *
 */
@Entity
@Table(name="t_setting")
public class Setting extends Model{

	public final static Setting inst = new Setting();
	
	@Column(nullable=false)
	@OneToOne
	@JoinColumn(name="user_default_role")
	@Int
	@Size(min = 1)
	private Role userDefaultRole;
	
	@Column(name="user_perm_control")
	@Required
	@Enumer(words={"yes", "no"})
	private String userPermControl;//yes | no

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
	
}
