package com.norther.dynamic.dao.processor;

import com.norther.dynamic.dao.annotation.SaveOrUpdate;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-10 ионГ10:49:07
 */
public class SaveOrUpdateMethodProcessor extends SingleParameterMethodProcessor<SaveOrUpdate> {

	@SuppressWarnings("unchecked")
	public Object process() {
		String entityName = annotation.entityName().trim();
		if (entityName.equals("")) {
			hibernateDao.saveOrUpdate(singleParameter);
		} else {
			hibernateDao.saveOrUpdate(entityName, singleParameter);
		}
		return null;
	}
}
