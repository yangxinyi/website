/*
 * @(#)EmployeeService.java
 *
 * Copyright 2011 Xinhua Online, Inc. All rights reserved.
 */

package com.map.service.user;

import com.exception.AuthorityException;
import com.map.model.user.Employee;
import com.support.web.filter.authentication.AuthenticationProvider;

/**
 * 后台用户逻辑
 * 
 * @author HideHai
 * @version 1.0,2011-8-3
 */
public interface EmployeeService extends AuthenticationProvider{

	/**
	 * 通过编号获取后台用户
	 * 
	 * @param id
	 *            后台用户编号
	 * @return
	 */
	Employee get(Long id);

	/**
	 * 通过名称获取后台用户
	 * 
	 * @param name
	 *            用户名称
	 * @return
	 */
	Employee getByName(String name);
	
	/**
	 * 更新用户
	 * 
	 * @param employee
	 * @return
	 */
	void update(Employee employee);

	/**
	 * 根据用户名和密码登录
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	Employee login(String name, String password);

	void addUser(Employee employee)
			throws AuthorityException;

}
