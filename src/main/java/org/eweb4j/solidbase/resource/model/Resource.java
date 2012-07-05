package org.eweb4j.solidbase.resource.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 系统的URI资源
 * 
 * @author weiwei
 * 
 */
@Entity
@Table(name = "t_resource")
public class Resource {
	@Id
	@Column(name = "id")
	private long resId;

	/* 资源标识 URI */
	private String uri;

	public long getResId() {
		return resId;
	}

	public void setResId(long resourceId) {
		this.resId = resourceId;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
