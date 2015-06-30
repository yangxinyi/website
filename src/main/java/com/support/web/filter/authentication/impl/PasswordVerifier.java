/*
 * @(#)PasswordVerifier.java
 *
 * Copyright 2008 Xinhua Online, Inc. All rights reserved.
 */

package com.support.web.filter.authentication.impl;

import com.support.web.filter.authentication.Verifier;

/**
 * description
 * @author  yangxinyi
 * @version 1.0,2015-6-25
 */
public class PasswordVerifier implements Verifier{
	
	private String userName;
	private String password;
	private Long type;

	public PasswordVerifier(String userName,String password){
		this.userName=userName;
		this.password=password;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	
	
}

