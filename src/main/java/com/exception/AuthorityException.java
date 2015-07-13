package com.exception;

import com.map.model.user.Employee;

/**
 * 权限exception
 * @author yangxinyi
 *
 */
public class AuthorityException extends BusinessException {

	private static final long serialVersionUID = 6425506829308372551L;

	public AuthorityException(Employee employee, String message) {
		super(employee, message);
	}


}
