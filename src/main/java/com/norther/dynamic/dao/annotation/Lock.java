package com.norther.dynamic.dao.annotation;

import com.norther.dynamic.dao.type.LockMode;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-11 обнГ10:38:06
 */
public @interface Lock {
	
	String entityName() default "";

	LockMode lockMode() default LockMode.NULL;
}
