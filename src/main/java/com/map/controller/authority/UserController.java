package com.map.controller.authority;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.exception.AuthorityException;
import com.map.model.user.Employee;
import com.map.service.user.EmployeeService;
import com.support.util.ControllerConstant;

/**
 * 
 * @author yangxinyi
 * 娴嬭瘯鐢�
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	private static final String USER_CREATE_VIEW = "/user/useradd";
	
	private static final String USER_CREATE = "/user/create";
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("/user/index");
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView addUser() {
		ModelAndView modelAndView = new ModelAndView(USER_CREATE_VIEW);
		CreateUserForm createUserForm = new CreateUserForm();
		modelAndView.addObject("createUserForm", createUserForm);
		return modelAndView;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView userCreate(
			@Valid CreateUserForm createUserForm, BindingResult result) {
		
		ModelAndView modelAndView = new ModelAndView();
		CreateUserValidator.validate(createUserForm, result);
		if (result.hasErrors()) {
			modelAndView.setViewName(USER_CREATE_VIEW);
		} else {
			Employee employee = new Employee();
			employee.setName(createUserForm.getName());
			employee.setPassword(createUserForm.getPassword());
			employee.setEmail(createUserForm.getEmail());
			employee.setMobile(createUserForm.getMobile());
			modelAndView.setViewName(USER_CREATE);
			modelAndView.addObject(ControllerConstant.MESSAGE_KEY, "用户创建成功");
			try {
				employeeService.addUser(employee);
			} catch (AuthorityException e) {
				modelAndView.addObject(ControllerConstant.MESSAGE_KEY, e.getMessage());
			}
			
		}
		modelAndView.addObject("createUserForm", createUserForm);
		return modelAndView;
	}
	
}