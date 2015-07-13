/*
 * @(#)EmployeeDao.java
 *
 * Copyright 2011 Xinhua Online, Inc. All rights reserved.
 */

package com.map.dao;

import com.map.model.user.Employee;
import com.norther.dynamic.dao.annotation.Get;
import com.norther.dynamic.dao.annotation.Save;
import com.norther.dynamic.dao.annotation.Update;
import com.norther.dynamic.dao.annotation.query.Query;

/**
 * 后台用户持久
 * @author  yangxinyi
 * @version 1.0,2015-7-3
 */
public interface EmployeeDao {

	@Get
	Employee get(Long id);

	@Save
	void save(Employee employee);

	@Update
	void update(Employee employee);

	@Query("from Employee e WHERE e.name = ?")
	Employee getByName(String name);

	@Query("from Employee e where e.name=? and e.password=?")
	Employee getByNameAndPassword(String name,String password);

}

