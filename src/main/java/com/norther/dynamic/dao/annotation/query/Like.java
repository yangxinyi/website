package com.norther.dynamic.dao.annotation.query;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.norther.dynamic.dao.type.MatchMode;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-17 обнГ09:44:01
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface Like {
	public MatchMode matchMode();
}
