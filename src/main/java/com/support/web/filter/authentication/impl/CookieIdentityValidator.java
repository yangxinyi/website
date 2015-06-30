package com.support.web.filter.authentication.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.support.util.CookieUtils;
import com.support.web.WebContext;
import com.support.web.filter.authentication.AuthenticationException;
import com.support.web.filter.authentication.AuthorityNotAvailableException;
import com.support.web.filter.authentication.IdentityValidator;
import com.support.web.filter.authentication.Principal;
import com.support.web.filter.authentication.Verifier;

public abstract class CookieIdentityValidator implements IdentityValidator {
	
	
	/**
	 * �����û�������һ���־�cookie
	 * 
	 * @param registerName
	 *            �û���
	 * @return ������cookie
	 */
	public abstract Cookie createVisitorCookie(String registerName);

	/**
	 * �����û����󴴽�һ���־�cookie
	 * 
	 * @param principal
	 *            �û�����
	 * @return ������cookie
	 */
	public abstract Cookie createPrincipalCookie(Principal principal);

	/**
	 * ͨ��������֤�ṩ�߽��е�¼�������¼�ɹ������Ựcookie�ͳ־�cookieд��ͻ���
	 * 
	 * @param verifier
	 *            �û���
	 * @return true ��ʾ��¼�ɹ� false ��ʾ��¼ʧ��
	 * @throws AuthenticationException  �ʺŻ��������
	 * @throws AuthorityNotAvailableException  ������
	 */
	public boolean login(Verifier verifier) throws AuthenticationException,AuthorityNotAvailableException {
		Principal principal = getAuthenticationProvider()
				.authenticate(verifier);
		if (principal != null) {
			if(!principal.isAvailable()){
				throw new AuthorityNotAvailableException();
			}
			HttpServletRequest request = WebContext.currentRequest();
			CookieUtils.writeCookie(createVisitorCookie(principal.getDisplayName()));
			CookieUtils.writeCookie(createPrincipalCookie(principal));
			request.setAttribute(VISITOR, principal.getDisplayName());
			request.setAttribute(PRINCIPAL, principal);
			return true;
		} else {
			return false;
		}
	}
}
