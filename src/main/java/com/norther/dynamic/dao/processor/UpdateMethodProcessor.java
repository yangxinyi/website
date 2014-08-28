package com.norther.dynamic.dao.processor;

import com.norther.dynamic.dao.annotation.Update;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-10 ионГ11:19:14
 */
public class UpdateMethodProcessor extends SingleParameterMethodProcessor<Update> {

	public Object process() {
		String entityName = annotation.entityName().trim();
		if (entityName.equals("")) {
			hibernateDao.update(singleParameter);
		} else {
			hibernateDao.update(entityName, singleParameter);
		}
		return null;
	}

}
