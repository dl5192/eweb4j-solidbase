package org.eweb4j.solidbase.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * 用户操作记录
 * 
 * @author weiwei
 * 
 */
@Entity
@Table(name = "t_user_activity_log")
public class UserActivityLog {
	@Id
	private long id;

	@OneToOne(mappedBy = "user_id")
	private User user; // xxx用户

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_account")
	private String userAccount;

	private String activity; // xx操作( {URI} + {HTTP METHOD} )

	private String time; // 什么时间

	private String result; // 操作结果 ( 成功 还是 失败 )

	@Column(name = "failure_cause")
	private String failureCause; // 失败原因 ( 权限不足 其他原因 )

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFailureCause() {
		return failureCause;
	}

	public void setFailureCause(String failureCause) {
		this.failureCause = failureCause;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

}
