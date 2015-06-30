package com.map.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.map.service.user.UserService;

/**
 * 
* @Title: LoginController.java
* @Package com.map.controller
* @Description: TODO(website)
* @author yangxinyi
* @date 2015年6月16日 上午10:41:08
* @version V1.0
 */
@Controller
public class LoginController {
	
	@Autowired
	private UserService signinService;

	@RequestMapping(value="/signin",method = RequestMethod.GET)
	public ModelAndView page(){
		return new ModelAndView("/login/index");
	}

	
	@RequestMapping(value="/signin",method = RequestMethod.POST)
	public ModelAndView login(HttpServletResponse response,HttpServletRequest request){
		ModelAndView view = new ModelAndView("/login/index");
		if(signinService.login(response, request)){
			view.setViewName("/index");
		}
		return view;
	}
		
		
		
}
