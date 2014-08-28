package com.norther.dynamic.dao.processor;

import com.norther.dynamic.dao.annotation.Delete;
import com.norther.dynamic.dao.type.LockMode;

/**
 * @author Norther Wang
 * @since 2008-3-10 ����11:13:15
 */
public class DeleteMethodProcessor extends SingleParameterMethodProcessor<Delete> {

	@SuppressWarnings("unchecked")
	public Object process() {
		LockMode lockMode = annotation.lockMode();
		if (lockMode.equals(LockMode.NULL)) {
			hibernateDao.delete(singleParameter);
		} else {
			hibernateDao.delete(singleParameter, lockMode.convert());
		}
		return null;
	}

}
