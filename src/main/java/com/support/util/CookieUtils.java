/*
 * @(#)CookieUtils.java
 *
 * Copyright 2011 Xinhua Online, Inc. All rights reserved.
 */

package com.support.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.support.web.WebContext;




public class CookieUtils {

	private static final Log log = LogFactory.getLog(CookieUtils.class);

	/**
	 * 得到当前request请求的所有cookie
	 * @param request cookie数组
	 * @return
	 */
	public static Cookie[] getCookies(HttpServletRequest request) {
		return request == null ? null : request.getCookies();
	}

	/**
	 * 根据cookie名字取得cookie
	 * @param request
	 * @param name
	 * @return cookie对象
	 */
	public static Cookie getCookie(HttpServletRequest request,String name) {
		Cookie[] cookies = getCookies(request);
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				String cookName = cookie.getName();
				if (cookName != null && cookName.equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	/**
	 * 根据cookie名字取得cookie的值
	 * @param request
	 * @param name
	 * @return cookie的值
	 */
	public static String getCookieValue(HttpServletRequest request,String name){
		Cookie cookie = getCookie(request, name);
		if(cookie != null)
			return cookie.getValue();
		return null;
	}

	/**
	 * 将cookie写入客户端
	 * @param response
	 * @param cookie 要写入的cookie
	 */
	public  static void writeCookie(HttpServletResponse response,Cookie cookie) {
		if (response != null)
			response.addCookie(cookie);
		else{
			log.warn("cookie is null");
		}
	}

	/**
	 * 删除客户端cookie
	 * @param request
	 * @param response
	 * @param name cookie名称
	 */
	public static void removeCookie(HttpServletRequest request,HttpServletResponse response, String name) {
		Cookie cookie = getCookie(request,name);
		removeCookie(response,cookie);
	}

	/**
	 * 删除客户端cookie
	 * @param response
	 * @param cookie cookie对象
	 */
	public static void removeCookie(HttpServletResponse response, Cookie cookie) {
		if (cookie != null) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		else{
			log.warn("cookie is null");
		}
	}

	/**
	 * 根据cookie名字取得cookie
	 * 
	 * @param name
	 *            cookie名字
	 * @return cookie
	 */
	public static Cookie getCookie(String name) {
		HttpServletRequest request = WebContext.currentRequest();
		Cookie[] cookies = getCookies(request);
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				String cookName = cookie.getName();
				if (cookName != null && cookName.equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}
	/**
	 * 将cookie写入客户端
	 * 
	 * @param cookie
	 *            要写入的cookie
	 */
	public static void writeCookie(Cookie cookie) {
		writeCookie(WebContext.currentResponse(), cookie);
	}

	public static void removeCookie(String cookieName, String path,
			String domain) {
		Cookie cookie = getCookie(cookieName);
		if (cookie != null) {
			cookie.setPath(path);
			cookie.setDomain(domain);
			cookie.setMaxAge(0);
			removeCookie(WebContext.currentResponse(), cookie);
		}
	}

}
