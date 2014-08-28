package com.norther.dynamic.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-10 ÉÏÎç12:18:27
 */
public class DynamicDaoProxy implements FactoryBean {

	private static final Log log = LogFactory.getLog(DynamicDaoProxy.class);

	@SuppressWarnings("unchecked")
	private Class dao;

	private SessionFactory sessionFactory;

	public Object getObject() throws Exception {
		return DynamicDaoFactory.create(dao, sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public Class getObjectType() {
		return dao;
	}

	public boolean isSingleton() {
		return true;
	}

	@SuppressWarnings("unchecked")
	public void setDao(String daoName) {
		if (log.isDebugEnabled()) {
			log.debug("dao class name [" + daoName + "]");
		}

		try {
			Class dao = Class.forName(daoName);
			if (dao == null) {
				throw new IllegalArgumentException("argument 'dao' can not be null");
			}
			if (!dao.isInterface()) {
				throw new IllegalArgumentException("dynamic dao only support interface! your class " + dao.getClass());
			}
			this.dao = dao;
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(e);
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
