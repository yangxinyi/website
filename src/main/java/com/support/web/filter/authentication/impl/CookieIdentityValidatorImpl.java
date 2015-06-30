package com.support.web.filter.authentication.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.support.util.CookieUtils;
import com.support.util.DateUtils;
import com.support.util.MD5Utils;
import com.support.web.WebContext;
import com.support.web.filter.authentication.AuthenticationProvider;
import com.support.web.filter.authentication.Principal;

/**
 * 
 * 
 *
 */
public class CookieIdentityValidatorImpl extends CookieIdentityValidator {

	private static final String DEFAULT_PRINCIPALCOOKIE_NAME = "p";
	private static final String DEFAULT_VISITORCOOKIE_NAME = "v";
	private static final String DEFAULT_COOKIE_CLIENT_NAME = "c";
	private static final String DEFAULT_DOMAIN = ".map.com";
	private static final String DEFAULT_PATH = "/";
	private static final int DEFAULT_PRINCIPALCOOKIE_MAX_AGE = 3600 * 12;
	private static final int DEFAULT_VISITORCOOKIE_MAX_AGE = 3600 * 24 * 7;
	private static final String DEFAULT_ENCODING = "utf-8";
	private static final String SPLIT = "&";

	private String domain = DEFAULT_DOMAIN;

	private String path = DEFAULT_PATH;

	private String visitorTarget = DEFAULT_VISITORCOOKIE_NAME;

	private String principalTarget = DEFAULT_PRINCIPALCOOKIE_NAME;

	private String cookieClientName = DEFAULT_COOKIE_CLIENT_NAME;

	private int principalCookieMaxAge = DEFAULT_PRINCIPALCOOKIE_MAX_AGE;

	private int visitorCookieMaxAge = DEFAULT_VISITORCOOKIE_MAX_AGE;

	private String encoding = DEFAULT_ENCODING;

	private AuthenticationProvider authenticationProvider;

	private String getMD5Param(Serializable id, Long lastLoginTime) {
		return id.toString() + lastLoginTime.toString();
	}

	/**
	 * ͨ�����CookieUtils��MD5Utils����Ựcookie
	 * 
	 * @param principal
	 *            �û�����
	 * @return ������cookie
	 */
	public Cookie createPrincipalCookie(Principal principal) {
		Cookie cookie = new Cookie(principalTarget,
				createPrincipalCookieString(principal));
		cookie.setPath(path);
		cookie.setDomain(domain);
		return cookie;
	}

	private String createPrincipalCookieString(Principal principal) {
		Long lastLoginTime = principal.getLastLoginSeconds();
		String md5Param = getMD5Param(principal.getIdentity(), lastLoginTime);
		StringBuilder cookValue = new StringBuilder();
		cookValue.append(principal.getIdentity().toString());
		cookValue.append(SPLIT + lastLoginTime.toString());
		cookValue.append(SPLIT + MD5Utils.encryptWithKey(md5Param));
		try {
			return URLEncoder.encode(cookValue.toString(), encoding);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ͨ�����CookieUtils����־�cookie
	 * 
	 * @param registerName
	 *            �û���
	 * 
	 * @return ������cookie
	 */
	public Cookie createVisitorCookie(String registerName) {
		try {
			registerName = URLEncoder.encode(registerName, encoding);
			Cookie cookie = new Cookie(visitorTarget, registerName);
			cookie.setMaxAge(visitorCookieMaxAge);
			cookie.setDomain(domain);
			cookie.setPath(path);
			return cookie;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ͨ�����CookieUtils���ҳ־�cookie��
	 * 
	 * @return �ҵ�����true�����򷵻�false
	 */
	public boolean isVisited() {
		return currentVisitor() != null;
	}

	/**
	 * ����currentPrincipal�������߼�������û��ѵ�¼���� true ,���򷵻�null
	 * 
	 * @return
	 */
	public boolean isLogined() {
		return currentPrincipal() != null;
	}

	/**
	 * ͨ�����CookieUtils���һỰcookie��<br>
	 * ����ҵ���ȡ��cookie��id��������֤�ṩ�ߵ�get�������õ��û���<br>
	 * �ٸ��cookieժҪ�����ժҪ�Աȣ����һ�����ж�ʱ������뵱ǰʱ��ļ���Ƿ��ں��ʷ�Χ��<br>
	 * ����Ƿ�����֤�û������򷵻�null
	 * 
	 * @return true ��ʾ���ѵ�¼ false ��ʾû�е�¼
	 */
	public Principal currentPrincipal() {
		HttpServletRequest request = WebContext.currentRequest();
		if (request != null) {
			Object principalObj = request.getAttribute(PRINCIPAL);
			if (principalObj != null && principalObj instanceof Principal) {
				return (Principal) principalObj;
			}
			Cookie cookie = CookieUtils.getCookie(principalTarget);
			if (cookie != null) {
				String cookieValue = cookie.getValue();
				try {
					cookieValue = URLDecoder.decode(cookieValue, encoding);
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
				String[] values = cookieValue.split(SPLIT);
				Integer cookieId = new Integer(values[0]);
				String cookieMd5Value = values[2];
				Principal pricipal = authenticationProvider.get(cookieId);
				if (pricipal != null) {
					Long lastLoginTime = pricipal.getLastLoginSeconds();
					String md5Param = getMD5Param(pricipal.getIdentity(),
							lastLoginTime);
					String md5Value = MD5Utils.encryptWithKey(md5Param);
					if (md5Value.equals(cookieMd5Value)) {
						Date nowDate = new Date();
						if (DateUtils.addSecond(
								new Date(lastLoginTime.longValue()),
								principalCookieMaxAge).compareTo(nowDate) > 0) {
							request.setAttribute(PRINCIPAL, pricipal);
							return pricipal;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * ͨ�����CookieUtils���ҳ־�cookie,
	 * 
	 * @return �ҵ�����cookie���û�����򷵻�null
	 */
	public String currentVisitor() {
		Object visitorObj = WebContext.currentRequest().getAttribute(VISITOR);
		if (visitorObj != null) {
			return (String) visitorObj;
		}
		try {
			Cookie cookie = CookieUtils.getCookie(visitorTarget);
			if (cookie == null) {
				return null;
			}
			return URLDecoder.decode(cookie.getValue(), encoding);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public void logout() {
		CookieUtils.removeCookie(principalTarget, path, domain);
		CookieUtils.removeCookie(visitorTarget, path, domain);
		CookieUtils.removeCookie(cookieClientName, path, domain);
		WebContext.currentRequest().setAttribute(VISITOR, null);
		WebContext.currentRequest().setAttribute(PRINCIPAL, null);
	}

	public void setAuthenticationProvider(
			AuthenticationProvider authenticationProvider) {
		this.authenticationProvider = authenticationProvider;
	}

	public AuthenticationProvider getAuthenticationProvider() {
		return authenticationProvider;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setVisitorTarget(String visitorTarget) {
		this.visitorTarget = visitorTarget;
	}

	public void setPrincipalTarget(String principalTarget) {
		this.principalTarget = principalTarget;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPrincipalCookieMaxAge(int principalCookieMaxAge) {
		this.principalCookieMaxAge = principalCookieMaxAge;
	}

	public void setVisitorCookieMaxAge(int visitorCookieMaxAge) {
		this.visitorCookieMaxAge = visitorCookieMaxAge;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}
