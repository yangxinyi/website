package com.map.controller.authority;

import java.io.Serializable;

/**
 * 用户创建form
 * @author yangxinyi
 *
 */
public class CreateUserForm implements Serializable{

	private static final long serialVersionUID = -5361244198198734867L;

	private String name;
	private String password;
	private String email;
	private String mobile;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
