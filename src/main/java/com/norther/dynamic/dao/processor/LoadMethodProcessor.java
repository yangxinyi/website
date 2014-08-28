package com.norther.dynamic.dao.processor;

import java.io.Serializable;

import com.norther.dynamic.dao.annotation.Load;
import com.norther.dynamic.dao.type.LockMode;
import com.norther.dynamic.dao.type.Null;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-10 ÏÂÎç10:21:17
 */
public class LoadMethodProcessor extends SingleParameterMethodProcessor<Load> {

	@Override
	public Object process() {
		if (!(singleParameter instanceof Serializable)) {
			throw new IllegalArgumentException("");
		}
		Serializable id = (Serializable) singleParameter;
		Class<? extends Object> entityClass = annotation.entityClass();
		String entityName = annotation.entityName().trim();
		LockMode lockMode = annotation.lockMode();

		if (!entityName.equals("")) {
			if (lockMode.equals(LockMode.NULL)) {
				return hibernateDao.load(entityName, id);
			} else {
				return hibernateDao.load(entityName, id, lockMode.convert());
			}
		}

		if (entityClass.equals(Null.class)) {
			entityClass = method.getReturnType();
		}

		if (lockMode.equals(LockMode.NULL)) {
			return hibernateDao.load(entityClass, id);
		} else {
			return hibernateDao.load(entityClass, id, lockMode.convert());
		}
	}

}
