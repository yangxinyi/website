package com.norther.dynamic.dao.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.norther.dynamic.dao.type.LockMode;
import com.norther.dynamic.dao.type.Null;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-9 ионГ12:48:12
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Load {

	Class<? extends Object> entityClass() default Null.class;

	String entityName() default "";

	LockMode lockMode() default LockMode.NULL;

}
