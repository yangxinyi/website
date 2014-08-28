package com.norther.dynamic.dao.processor;

import java.io.Serializable;

import com.norther.dynamic.dao.annotation.Get;
import com.norther.dynamic.dao.type.LockMode;
import com.norther.dynamic.dao.type.Null;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-10 ÏÂÎç10:20:31
 */
public class GetMethodProcessor extends SingleParameterMethodProcessor<Get> {

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
				return hibernateDao.get(entityName, id);
			} else {
				return hibernateDao.get(entityName, id, lockMode.convert());
			}
		}
		if (entityClass.equals(Null.class)) {
			entityClass = method.getReturnType();
		}
		if (lockMode.equals(LockMode.NULL)) {
			return hibernateDao.get(entityClass, id);
		} else {
			return hibernateDao.get(entityClass, id, lockMode.convert());
		}

	}

}
