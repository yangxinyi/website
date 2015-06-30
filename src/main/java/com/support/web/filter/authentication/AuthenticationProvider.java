package com.support.web.filter.authentication;

import java.io.Serializable;


public interface AuthenticationProvider {
	/**
	* �����û���ʶ������֤�û�
	* @param id �û���ʶ
	* @return ��֤�û� û�з���null
	*/
	public Principal get(Serializable id);

	/**
	 * ��֤�û�
	 * @param verifier
	 * @return
	 * @throws AuthenticationException
	 */
	public Principal authenticate(Verifier verifier) throws AuthenticationException;
}
