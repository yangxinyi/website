package com.norther.dynamic.dao.annotation;

import com.norther.dynamic.dao.type.LockMode;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-9 ����12:20:03
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Delete {
	LockMode lockMode() default LockMode.NULL;
}
