/**
 * 
 */
package com.norther.dynamic.dao.annotation.query;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.norther.dynamic.dao.type.CacheMode;
import com.norther.dynamic.dao.type.FlushMode;
import com.norther.dynamic.dao.type.LockMode;
import com.norther.dynamic.dao.type.Null;

/**
 * @author Norther Wang
 * @since 2008-3-8 ����11:28:58
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Query {

	String value() default "";

	String name() default "";

	boolean readOnly() default false;

	String comment() default "";

	boolean cacheable() default false;

	String cacheRegion() default "";

	CacheMode cacheMode() default CacheMode.NULL;

	FlushMode flushMode() default FlushMode.NULL;

	LockMode lockMode() default LockMode.NULL;

	String lockModeAlias() default "";

	boolean executeUpdate() default false;

	boolean sqlQuery() default false;

	int fetchSize() default -1;

	int timeout() default -1;

	Class<?> postProcessor() default Null.class;
}
