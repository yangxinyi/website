package com.norther.dynamic.dao.processor;

import com.norther.dynamic.dao.DaoMethodProcessor;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-10 ÉÏÎç11:10:20
 */
public abstract class SingleParameterMethodProcessor<T> extends DaoMethodProcessor<T> {

	protected Object singleParameter;

	@Override
	public void setParameters(Object[] parameters) {
		if (parameters.length != 1) {
			throw new IllegalArgumentException("unassured argument, there are " + parameters.length + " arguments for "
					+ getParameterizedType().getSimpleName());
		}
		if (parameters[0] == null) {
			throw new IllegalArgumentException("the argument can not be null");
		}
		this.singleParameter = parameters[0];
		super.setParameters(parameters);
	}
}
