package org.eweb4j.solidbase.permission.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.eweb4j.solidbase.code.model.Code;
import org.eweb4j.solidbase.resource.model.Resource;

/**
 * 权限数据模型
 * 
 * @author weiwei
 * @since JDK 1.5
 * @CopyRight WinOn Ltd. 2012
 * @version Gateway-1.0.0
 */
@Entity
@Table(name = "t_permission")
public class Permission {
	/* 自增长ID */
	@Id
	@Column(name = "id")
	private long permId;

	/* 权限名称 */
	private String name;

	@Transient
	private String httpMethodStr;

	@OneToOne
	private Resource resource;

	/* http 方法， POST, GET, PUT, DELETE */

	@ManyToMany
	@JoinTable(name = "t_perm_http_method", joinColumns = @JoinColumn(name = "perm_id"), inverseJoinColumns = @JoinColumn(name = "http_method"))
	private List<Code> httpMethods = new ArrayList<Code>();

	/* 权限分类 */
	@OneToOne
	@JoinColumn(name="type")
	private Code type;

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	/* 备注 */
	private String remark;

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

	public String getHttpMethodStr() {
		if (this.httpMethods == null && this.permId > 0)
			return null;

		StringBuilder sb = new StringBuilder();
		for (Code method : httpMethods) {
			if (sb.length() > 0)
				sb.append("|");

			sb.append(method.getCodeValue());
		}
		this.httpMethodStr = sb.toString();
		return this.httpMethodStr;
	}

	public void setHttpMethodStr(String httpMethodStr) {
		this.httpMethodStr = httpMethodStr;
	}

	public long getPermId() {
		return permId;
	}

	public void setPermId(long permId) {
		this.permId = permId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Code> getHttpMethods() {
		return httpMethods;
	}

	public void setHttpMethods(List<Code> httpMethods) {
		this.httpMethods = httpMethods;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Code getType() {
		return type;
	}

	public void setType(Code type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Permission [permId=" + permId + ", name=" + name + "]";
	}

}
