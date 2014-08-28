package com.norther.dynamic.dao.processor;

import com.norther.dynamic.dao.annotation.Save;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-10 ионГ10:53:17
 */
public class SaveMethodProcessor extends SingleParameterMethodProcessor<Save> {

	public Object process() {
		String entityName = annotation.entityName().trim();
		if (entityName.equals("")) {
			return hibernateDao.save(singleParameter);
		} else {
			return hibernateDao.save(entityName, singleParameter);
		}
	}

}
