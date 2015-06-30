/*
 * @(#)AuthenticationException.java
 *
 * Copyright 2008 Xinhua Online, Inc. All rights reserved.
 */

package com.support.web.filter.authentication;

/**
 * 
 * @author xinyi
 *
 */
public class AuthenticationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3586688485169295329L;

	public AuthenticationException() {
		super();
	}

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(Throwable cause) {
		super(cause);
	}
	
}

