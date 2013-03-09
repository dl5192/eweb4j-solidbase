package org.eweb4j.solidbase.permission.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eweb4j.solidbase.code.model.Code;

@Entity
@Table(name="t_perm_http_method")
public class PermHttpIndex {

	@Id
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="perm_id")
	private Permission perm;
	
	@ManyToOne
	@JoinColumn(name="http_method")
	private Code http;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Permission getPerm() {
		return perm;
	}

	public void setPerm(Permission perm) {
		this.perm = perm;
	}

	public Code getHttp() {
		return http;
	}

	public void setHttp(Code http) {
		this.http = http;
	}
	
}
