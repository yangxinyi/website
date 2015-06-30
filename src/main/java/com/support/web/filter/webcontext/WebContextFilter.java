/*
 * @(#)WebContextFilter.java
 *
 * Copyright 2011 Xinhua Online, Inc. All rights reserved.
 */

package com.support.web.filter.webcontext;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.support.web.WebContext;

/**
 * 将request和response注册到WebContext中，结束时清除
 * @author  yangxinyi
 * @version 1.0,2015-7-27
 */
public class WebContextFilter implements Filter{

	Logger log = LoggerFactory.getLogger(getClass());


	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException{ 
		try {
			HttpServletRequest heq = (HttpServletRequest)request;
			WebContext.registry(heq, (HttpServletResponse)response);
			chain.doFilter(request, response);
		}
		finally{
			WebContext.release();
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
