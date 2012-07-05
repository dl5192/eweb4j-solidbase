package org.eweb4j.solidbase.department.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eweb4j.solidbase.code.model.Code;

@Entity
@Table(name = "t_department")
public class Department implements Serializable {

	private static final long serialVersionUID = 4501527970281063073L;

	@Id
	@Column(name = "id")
	private long departId;

	// 部门名称=code.codeRemark
	// 部门代码=code.codeValue
	// 上级部门=code.parent.codeId-->fetch in t_department
	@OneToOne
	private Code code; // 组合一个一一对应的代码块,使用其层级关系

	@OneToOne
	@JoinColumn(name="depart_level")
	private Code level; // 部门级别

	private List<Department> children = new ArrayList<Department>(); // 孩子节点

	@OneToOne
	@JoinColumn(name="depart_cate")
	private Code departCate; // 部门类型

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

	public long getDepartId() {
		return departId;
	}

	public void setDepartId(long departId) {
		this.departId = departId;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public Code getLevel() {
		return level;
	}

	public void setLevel(Code level) {
		this.level = level;
	}

	public Code getDepartCate() {
		return departCate;
	}

	public void setDepartCate(Code departCate) {
		this.departCate = departCate;
	}

	public List<Department> getChildren() {
		return children;
	}

	public void setChildren(List<Department> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Department [departId=" + departId + ", code=" + code
				+ ", level=" + level + ", departCate=" + departCate
				+ ", addTime=" + addTime + ", modifyTime=" + modifyTime + "]";
	}

}
