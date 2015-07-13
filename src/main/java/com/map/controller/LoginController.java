package com.map.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.support.web.filter.authentication.AuthenticationException;
import com.support.web.filter.authentication.AuthorityNotAvailableException;
import com.support.web.filter.authentication.IdentityValidator;
import com.support.web.filter.authentication.impl.PasswordVerifier;

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
	
	private static final String ERRORMESSAGE="errorMessage";
	
	@Resource(name="identityValidator")
	private IdentityValidator identityValidator;

	@RequestMapping(value="/login",method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value="from",required=false)String from){
		ModelAndView modelAndView = new ModelAndView("/user/login");
		if(identityValidator.isLogined()){
			modelAndView.setView(new RedirectView("/user/index"));
			return modelAndView;
		}
		LoginForm loginForm = new LoginForm();
		loginForm.setName("Root");
		loginForm.setFrom(from);
		modelAndView.addObject(loginForm);
		return modelAndView;
	}

	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView login(@Valid LoginForm loginForm,BindingResult result){
		ModelAndView modelAndView = new ModelAndView("redirect:/test");
		boolean success;
		try {
			success = identityValidator.login(new PasswordVerifier(loginForm.getName(), loginForm.getPassword()));
			if(success){
				modelAndView.setViewName("/user/index");
			}
		} catch (AuthenticationException e) {
			modelAndView.addObject(ERRORMESSAGE, "账户名或密码不正确！");
		} catch(AuthorityNotAvailableException e){
			modelAndView.addObject(ERRORMESSAGE, "账户被禁用！");
		}
		return modelAndView;
	}
		
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logout(){
		ModelAndView mav = new ModelAndView("/user/login");
		identityValidator.logout();
		return mav;
	}	
		
}
