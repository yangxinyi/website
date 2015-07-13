/*
 * @(#)EmployeeServiceImpl.java
 *
 * Copyright 2011 Xinhua Online, Inc. All rights reserved.
 */

package com.map.service.user.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.exception.AuthorityException;
import com.map.dao.EmployeeDao;
import com.map.model.user.Employee;
import com.map.service.user.EmployeeService;
import com.norther.dynamic.dao.InjectDao;
import com.support.security.MD5Custom;
import com.support.web.filter.authentication.AuthenticationException;
import com.support.web.filter.authentication.Principal;
import com.support.web.filter.authentication.Verifier;
import com.support.web.filter.authentication.impl.PasswordVerifier;

/**
 * 后台用户逻辑实现
 * @author  yangxinyi
 * @version 1.0,2015-7-3
 */
@Service("employeeService")
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService,Serializable{

	private static final long serialVersionUID = -351143041171740060L;
	
	private EmployeeDao employeeDao;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Employee get(Long id) {
		return employeeDao.get(id);
	}
    
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Employee getByName(String name) {
		return employeeDao.getByName(name);
	}

	public void update(Employee employee) {
		employeeDao.update(employee);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Employee login(String name, String password) {
		password = MD5Custom.encrypt(password);
		return employeeDao.getByNameAndPassword(name, password);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Principal get(Serializable id) {
		return get(new Long(id.toString()));
	}
	
	@Override
	public void addUser(Employee employee) throws AuthorityException {
		employeeCheck(employee);
		Date date = new Date();
		employee.setUpdateTime(date);
		employee.setRegisterTime(date);
		employee.setPassword(MD5Custom.encrypt(employee.getPassword()));
		employee.setAvailable(true);
		employeeDao.save(employee);
		// TODO 发邮件
	}

	private void employeeCheck(Employee employee) throws AuthorityException {
		if(getByName(employee.getName()) != null){
			throw new AuthorityException(employee, "该账号已经存在");
		}
	}

	public Principal authenticate(Verifier verifier)
	throws AuthenticationException {
		Principal principal=null;
		if(verifier instanceof PasswordVerifier){
			PasswordVerifier verifierPass=(PasswordVerifier)verifier;
			principal = login(verifierPass.getUserName(), verifierPass.getPassword());
			if(principal != null){
				if(!(principal instanceof Employee)){
					throw new AuthenticationException("登录方式不正确");					
				}else{
					Employee employee = (Employee) principal;
					Date now = new Date(System.currentTimeMillis());
					employee.setLastLoginTime(now);
					update(employee);
				}
			}
		}
		return principal;
	}

	@InjectDao
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

}

