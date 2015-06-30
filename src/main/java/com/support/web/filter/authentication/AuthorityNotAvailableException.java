/*
 * @(#)AuthorityNotAvailableException
 *
 * Copyright 2011 Xinhua Online, Inc. All rights reserved.
 */

package com.support.web.filter.authentication;

/**
 * 
 * @author xinyi
 *
 */
public class AuthorityNotAvailableException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2398583435820358885L;

	public AuthorityNotAvailableException() {
		super();
	}

	public AuthorityNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorityNotAvailableException(String message) {
		super(message);
	}

	public AuthorityNotAvailableException(Throwable cause) {
		super(cause);
	}
	
}
