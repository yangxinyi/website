package com.norther.dynamic.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.norther.dynamic.dao.annotation.Delete;
import com.norther.dynamic.dao.annotation.Get;
import com.norther.dynamic.dao.annotation.Load;
import com.norther.dynamic.dao.annotation.Lock;
import com.norther.dynamic.dao.annotation.Save;
import com.norther.dynamic.dao.annotation.SaveOrUpdate;
import com.norther.dynamic.dao.annotation.Update;
import com.norther.dynamic.dao.annotation.query.Query;
import com.norther.dynamic.dao.processor.DeleteMethodProcessor;
import com.norther.dynamic.dao.processor.GetMethodProcessor;
import com.norther.dynamic.dao.processor.LoadMethodProcessor;
import com.norther.dynamic.dao.processor.LockMethodProcessor;
import com.norther.dynamic.dao.processor.QueryMethodProcessor;
import com.norther.dynamic.dao.processor.SaveMethodProcessor;
import com.norther.dynamic.dao.processor.SaveOrUpdateMethodProcessor;
import com.norther.dynamic.dao.processor.UpdateMethodProcessor;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-10 ����10:52:37
 */
public class DynamicDaoInvocationHandler implements InvocationHandler {

	private HibernateDao hibernateDao;

	@SuppressWarnings("unchecked")
	public Object invoke(Object proxy, Method method, Object[] parameters) throws Throwable {
		Annotation[] annotations = method.getAnnotations();
		for (Annotation each : annotations) {
			DaoMethodProcessor daoMethodProcessor = null;
			Class<? extends Annotation> annotationClass = each.annotationType();
			if (annotationClass.equals(Query.class)) {
				daoMethodProcessor = new QueryMethodProcessor();
			} else if (annotationClass.equals(Save.class)) {
				daoMethodProcessor = new SaveMethodProcessor();
			} else if (annotationClass.equals(Update.class)) {
				daoMethodProcessor = new UpdateMethodProcessor();
			} else if (annotationClass.equals(SaveOrUpdate.class)) {
				daoMethodProcessor = new SaveOrUpdateMethodProcessor();
			} else if (annotationClass.equals(Delete.class)) {
				daoMethodProcessor = new DeleteMethodProcessor();
			} else if (annotationClass.equals(Get.class)) {
				daoMethodProcessor = new GetMethodProcessor();
			} else if (annotationClass.equals(Load.class)) {
				daoMethodProcessor = new LoadMethodProcessor();
			} else if (annotationClass.equals(Lock.class)) {
				daoMethodProcessor = new LockMethodProcessor();
			}
			if (daoMethodProcessor != null) {
				if (parameters == null || parameters.length == 0) {
					throw new IllegalStateException("a valid dao method should have 1 parameter at least");
				}
				daoMethodProcessor.setAnnotation(each);
				daoMethodProcessor.setParameters(parameters);
				daoMethodProcessor.setParameterAnnotations(method.getParameterAnnotations());
				daoMethodProcessor.setHibernateDao(hibernateDao);
				daoMethodProcessor.setMethod(method);

				return daoMethodProcessor.process();
			}
		}

		throw new IllegalStateException("the method of invocation is not a valid dao method");
	}

	public void setHibernateDao(HibernateDao hibernateDao) {
		this.hibernateDao = hibernateDao;
	}

}
