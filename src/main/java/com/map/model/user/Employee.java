/*
 * @(#)Employee.java
 *
 * Copyright 2011 Xinhua Online, Inc. All rights reserved.
 */

package com.map.model.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * description
 * 
 * @author yangxinyi
 * @version 1.0,2015-7-10
 */
@Entity
@Table(name = "employee")
@PrimaryKeyJoinColumn(name = "user")
public class Employee extends User {

	private static final long serialVersionUID = 4557256088325758497L;

	@Column(name = "updatetime")
	private Date updateTime;

	public Employee() {

	}

	public Employee(Long id) {
		setId(id);
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
