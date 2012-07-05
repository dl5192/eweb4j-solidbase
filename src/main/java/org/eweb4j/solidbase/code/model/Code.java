package org.eweb4j.solidbase.code.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.eweb4j.mvc.validator.annotation.Length;
import org.eweb4j.mvc.validator.annotation.Required;


/**
 * 类别(代码管理用),有父子关系
 * 
 * @author weiwei
 * 
 */
@Entity
@Table(name = "t_code")
public class Code implements Serializable {
	private static final long serialVersionUID = 262557676199399666L;

	@Id
	@Column(name = "id")
	private long codeId = -1;// 类别ID,即类别代码

	@Required
	@Column(name = "code_value")
	private String codeValue;// 代码值

	@Required
	@Length(max = 50)
	private String remark;// 类别备注

	@OneToOne
	private Code parent;// 父类别

	@OneToOne
	private Code type;// 元类别(类别的类别)

	@Transient
	private String inputValue; // 自动建议输入的值, 在此用来排序的.

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

	public long getCodeId() {
		return codeId;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public Code getParent() {
		return parent;
	}

	public void setParent(Code parent) {
		this.parent = parent;
	}

	public Code getType() {
		return type;
	}

	public void setType(Code type) {
		this.type = type;
	}

	public void setCodeId(long codeId) {
		this.codeId = codeId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInputValue() {
		return inputValue;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	@Override
	public String toString() {
		return "Code [codeId=" + codeId + ", codeValue=" + codeValue
				+ ", remark=" + remark + ", inputValue=" + inputValue
				+ ", addTime=" + addTime + ", modifyTime=" + modifyTime + "]";
	}

}
