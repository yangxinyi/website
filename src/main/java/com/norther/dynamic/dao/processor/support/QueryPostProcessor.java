package com.norther.dynamic.dao.processor.support;

import org.hibernate.Query;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-10 обнГ11:16:01
 */
public interface QueryPostProcessor {

	Object process(Query query);
}
