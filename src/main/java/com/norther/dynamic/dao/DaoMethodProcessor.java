package com.norther.dynamic.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-10 ÉÏÎç10:44:24
 */
public abstract class DaoMethodProcessor<T> {

	protected final Log log = LogFactory.getLog(getClass());

	protected Annotation[][] parameterAnnotations;

	protected Object[] parameters;

	protected Method method;

	protected T annotation;

	protected HibernateDao hibernateDao;

	public abstract Object process();

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	public T getAnnotation() {
		return annotation;
	}

	public void setAnnotation(T annotation) {
		this.annotation = annotation;
	}

	@SuppressWarnings("unchecked")
	public Class<T> getParameterizedType() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public HibernateDao getHibernateDao() {
		return hibernateDao;
	}

	public void setHibernateDao(HibernateDao hibernateDao) {
		this.hibernateDao = hibernateDao;
	}

	public Annotation[][] getParameterAnnotations() {
		return parameterAnnotations;
	}

	public void setParameterAnnotations(Annotation[][] parameterAnnotations) {
		this.parameterAnnotations = parameterAnnotations;
	}
}
