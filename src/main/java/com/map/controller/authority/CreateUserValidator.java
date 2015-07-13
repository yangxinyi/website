package com.map.controller.authority;

import org.springframework.validation.Errors;

import com.support.util.ControllerConstant;

/**
 * 
* @Title: CreateUserValidator.java
* @Description: 创建用户表单的验证器
* @author yangxinyi
* 2015年7月10日 上午10:06:42
* @version V1.0
 */

public class CreateUserValidator {

	private static final String VALIDATE_MESSAGE_CREATE = "create";
	
	private static final int PSW_MIN = 6;
	
    public static void validate(CreateUserForm createUserForm, Errors errors) {  
        // TODO Auto-generated method stub  
        if (createUserForm.getName().isEmpty()) {
			errors.rejectValue("name", VALIDATE_MESSAGE_CREATE);
		} else if (createUserForm.getPassword().isEmpty()) {
			errors.rejectValue("password", VALIDATE_MESSAGE_CREATE);
		} else if (createUserForm.getEmail().isEmpty()) {
			errors.rejectValue("email", VALIDATE_MESSAGE_CREATE);
		} else if (createUserForm.getPassword().length() < PSW_MIN) {
			errors.rejectValue("password", null, "密码的长度必须大于等于" + PSW_MIN);
		} else if (!createUserForm.getEmail().matches(ControllerConstant.EMAIL_EXP)) {
			errors.rejectValue("email", null, "邮箱格式不合法");
		} else if (createUserForm.getMobile().isEmpty()) {
			errors.rejectValue("mobile", VALIDATE_MESSAGE_CREATE);
		}  
    }  

}