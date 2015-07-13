/*
 * @(#)BusinessException.java
 *
 * Copyright 2011 Xinhua Online, Inc. All rights reserved.
 */

package com.exception;

import java.io.Serializable;

/**
 * 业务异常基类
 * 
 * @author yangxinyi
 */
public abstract class BusinessException extends Exception {

	private static final long serialVersionUID = 945754014762198036L;
	
	private Serializable businessObject;

	public BusinessException(Serializable businessObject, String message) {
		super(businessObject + " " + message);
		this.businessObject = businessObject;
	}

	public Serializable getBusinessObject() {
		return businessObject;
	}
}
