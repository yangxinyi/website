package com.norther.dynamic.dao.processor;

import com.norther.dynamic.dao.annotation.Lock;
import com.norther.dynamic.dao.type.LockMode;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-11 ÏÂÎç10:43:44
 */
public class LockMethodProcessor extends SingleParameterMethodProcessor<Lock> {

	@Override
	public Object process() {
		LockMode lockMode = annotation.lockMode();
		if (lockMode.equals(LockMode.NULL)) {
			throw new IllegalArgumentException("lock mode is required!");
		}
		String entityName = annotation.entityName().trim();
		if (entityName.equals("")) {
			hibernateDao.lock(singleParameter, lockMode.convert());
		} else {
			hibernateDao.lock(entityName, singleParameter,lockMode.convert());
		}
		return null;
	}

}
