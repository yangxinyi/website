package com.map.service.user;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.exception.AuthorityException;
import com.map.model.user.Employee;
import com.map.service.BaseTest;

/**
 * 
* @Title: EmployeeServiceTest.java
* @Description: TODO(website)
* @author yangxinyi
* 2015年7月9日 上午11:01:45
* @version V1.0
 */
public class EmployeeServiceTest extends BaseTest {
	
	@Autowired
	private EmployeeService employeeService;
	
	private String username;
	
	@Before
	public void setUp() {
		username = "xin";
	}
	
	@Test
//	@Rollback(false)
	public void testCreate() throws AuthorityException {
		Employee employee = new Employee();
		employee.setEmail("xin@winshare.com");
		employee.setRegisterTime(new Date());
		employee.setUpdateTime(new Date());
		employee.setMobile("18202020202");
		employee.setName(username);
		employee.setPassword("123456");
		employeeService.addUser(employee);
	}
	
	@Test
	public void testGet() {
		Employee employee = employeeService.getByName(username);
		assertEquals(username, employee.getName());
	}
	
}