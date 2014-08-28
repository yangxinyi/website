package com.norther.dynamic.dao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-11 ÏÂÎç11:19:37
 */
public class AutoInjectDynamicDaoBeanPostProcessor implements BeanPostProcessor {

	private static final Log log = LogFactory.getLog(AutoInjectDynamicDaoBeanPostProcessor.class);

	private Map<Class<?>, Object> daoCache = new HashMap<Class<?>, Object>();

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Method[] methods = bean.getClass().getMethods();
		for (Method each : methods) {
			InjectDao annotation = each.getAnnotation(InjectDao.class);
			if (annotation == null) {
				continue;
			}
			Class<?>[] parameterTypes = each.getParameterTypes();
			if (parameterTypes == null || parameterTypes.length != 1) {
				throw new BeanInitializationException("method " + each + " not a valid method for DynamicDao injection");
			}
			Class<?> daoClass = parameterTypes[0];

			if (log.isDebugEnabled()) {
				log.debug("inject dao class " + daoClass);
			}

			Object dao = getDaoFromCache(daoClass);

			try {
				each.invoke(bean, dao);
			} catch (IllegalAccessException e) {
				throw new BeanInitializationException(e.getMessage());
			} catch (InvocationTargetException e) {
				throw new BeanInitializationException(e.getMessage());
			}

		}
		return bean;
	}

	private Object getDaoFromCache(Class<?> daoClass) {
		Object dao = daoCache.get(daoClass);
		if (dao == null) {
			if (log.isDebugEnabled()) {
				log.debug("create dao for " + daoClass + "]");
			}
			dao = DynamicDaoFactory.create(daoClass, sessionFactory);
			daoCache.put(daoClass, dao);
		}
		return dao;
	}

}
