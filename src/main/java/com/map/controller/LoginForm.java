/*
 * @(#)LoginForm.java
 *
 * Copyright 2011 Xinhua Online, Inc. All rights reserved.
 */

package com.map.controller;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 后台用户登录表单
 * @author  yangxinyi
 * @version 1.0,2015-7-3
 */
public class LoginForm {

	private static final int NAME_MIN = 3;
	
	private static final int NAME_MAX = 16;
	
	private static final int PWD_MIN = 6;
	
	private static final int PWD_MAX = 16;
	
	@NotEmpty
	@Size(min = NAME_MIN, max = NAME_MAX)
	private String name;

	@NotEmpty
	@Size(min = PWD_MIN, max = PWD_MAX)
	private String password;

	private String from;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}

