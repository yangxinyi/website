package com.norther.dynamic.dao;

import java.lang.reflect.Proxy;

import org.hibernate.SessionFactory;

import com.norther.dynamic.dao.support.HibernateDaoDelegator;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-11 ÏÂÎç11:34:32
 */
public class DynamicDaoFactory {

	private DynamicDaoFactory() {

	}

	public static Object create(Class<?> clazz, SessionFactory sessionFactory) {
		HibernateDaoDelegator hibernateDaoDelegator = new HibernateDaoDelegator();
		hibernateDaoDelegator.setSessionFactory(sessionFactory);

		DynamicDaoInvocationHandler dynamicDaoInvocationHandler = new DynamicDaoInvocationHandler();
		dynamicDaoInvocationHandler.setHibernateDao(hibernateDaoDelegator);
		return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, dynamicDaoInvocationHandler);
	}
}
