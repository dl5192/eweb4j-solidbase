package org.eweb4j.solidbase.user.model;

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
import javax.persistence.Transient;

import org.eweb4j.mvc.validator.annotation.Equals;
import org.eweb4j.mvc.validator.annotation.Length;
import org.eweb4j.mvc.validator.annotation.Required;
import org.eweb4j.solidbase.department.model.Department;
import org.eweb4j.solidbase.role.model.Role;

/**
 * 用户-持久化对象
 * 
 * @author weiwei[l.weiwei@163.com]
 * 
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable {
	
	private static final long serialVersionUID = -3248564641931360005L;
	
	@Id
	private long id;// 自增长id

	@Column(name = "super_power")
	private String superPower; // 是否拥有超级能力，可以越过权限检查, yes|no

	@Transient
	@Required
	@Length(min=5, max=5)
	private String authcode;// 验证码，非数据表字段
	
	@Required
	@Length(min=4, max=16)
	private String account;// 账号,4-16
	
	@Required
	@Length(min=4, max=32)
	private String password;// 密码，4-32,MD5加密
	
	@Transient
	@Required
	@Equals(to="password")
	private String rePwd;
	
	private String status;// 用户状态：'正常'、'锁定'
	
	@Column(name = "last_login_time")
	private String lastLoginTime;// 上一次登陆时间
	
	@Column(name = "last_login_ip")
	private String lastLoginIp;// 上一次登陆IP
	
	@Column(name = "register_time")
	private String regTime;

	@ManyToMany
	@JoinTable(name = "t_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<Role>();

	@ManyToMany
	@JoinTable(name = "t_user_department", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "department_id"))
	private List<Department> departments = new ArrayList<Department>();

	@Transient
	private boolean isValid; // 帐号是否有效,非数据表字段

	@Column(name = "true_name")
	private String trueName; // 用户姓名
	
	private String email; // 电子邮箱
	
	@Column(name = "id_num")
	private String idNum; // 证件号码
	
	private String fax; // 联系传真
	
	private String addr; // 联系地址
	
	@Column(name = "office_phone")
	private String officePhone;
	
	@Column(name = "mobile_num")
	private String mobileNum; // 手机号码
	

	@Column(name = "home_phone")
	private String homePhone; // 家庭电话
	
	@Column(name = "available_period")
	private String availablePeriod; // 帐号有效期

	@Column(name = "add_time")
	protected String addTime;

	@Column(name = "modify_time")
	protected String modifyTime;
	
	/* 属性菜单权限 */
	private List<Long> treeMenuPerms;
	
	/* 导航菜单权限 */
	private List<Long> navMenuPerms;

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

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthcode() {
		return authcode;
	}

	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRePwd() {
		return rePwd;
	}

	public void setRePwd(String rePwd) {
		this.rePwd = rePwd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getAvailablePeriod() {
		return availablePeriod;
	}

	public void setAvailablePeriod(String availablePeriod) {
		this.availablePeriod = availablePeriod;
	}

	public String getSuperPower() {
		return superPower;
	}

	public void setSuperPower(String superPower) {
		this.superPower = superPower;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", superPower=" + superPower + ", authcode="
				+ authcode + ", account=" + account + ", password=" + password
				+ ", rePwd=" + rePwd + ", status=" + status
				+ ", lastLoginTime=" + lastLoginTime + ", lastLoginIp="
				+ lastLoginIp + ", regTime=" + regTime + ", roles=" + roles
				+ ", departments=" + departments + ", isValid=" + isValid
				+ ", trueName=" + trueName + ", email=" + email + ", idNum="
				+ idNum + ", fax=" + fax + ", addr=" + addr + ", officePhone="
				+ officePhone + ", mobileNum=" + mobileNum + ", homePhone="
				+ homePhone + ", availablePeriod=" + availablePeriod
				+ ", addTime=" + addTime + ", modifyTime=" + modifyTime + "]";
	}

	public List<Long> getTreeMenuPerms() {
		return treeMenuPerms;
	}

	public void setTreeMenuPerms(List<Long> treeMenuPerms) {
		this.treeMenuPerms = treeMenuPerms;
	}

	public List<Long> getNavMenuPerms() {
		return navMenuPerms;
	}

	public void setNavMenuPerms(List<Long> navMenuPerms) {
		this.navMenuPerms = navMenuPerms;
	}

}
