package com.norther.dynamic.dao.processor.support;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.criterion.MatchMode;

import com.norther.dynamic.dao.annotation.query.FirstResult;
import com.norther.dynamic.dao.annotation.query.Like;
import com.norther.dynamic.dao.annotation.query.MaxResults;
import com.norther.dynamic.dao.annotation.query.Parameter;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-18 ÏÂÎç10:51:50
 */
public class QueryArguments {

	private Annotation[][] parameterAnnotations;

	private Object[] parameters;

	private Integer firstResult;

	private Integer maxResults;

	private Map<String, Object> namedArguments;

	private Map<Integer, Object> positionArguments;

	public boolean hasValue(Integer key) {
		return positionArguments.get(key) == null ? false : true;
	}

	public boolean hasValue(String key) {
		return namedArguments.get(key) == null ? false : true;
	}

	public void removeParameter(Integer key) {
		positionArguments.remove(key);
	}

	public void removeParameter(String key) {
		namedArguments.remove(key);
	}

	private void setParameter(Integer key, Object value) {
		positionArguments.put(key, value);
	}

	private void setParameter(String key, Object value) {
		namedArguments.put(key, value);
	}

	public QueryArguments(Object[] parameters, Annotation[][] parameterAnnotations) {
		this.parameterAnnotations = parameterAnnotations;
		this.parameters = parameters;

		namedArguments = new HashMap<String, Object>();
		positionArguments = new LinkedHashMap<Integer, Object>();

		init();
	}

	private void init() {
		for (int i = 0; i < parameters.length; i++) {
			Object argument = parameters[i];
			processAnnotationOfArgument(argument, i);

		}
	}

	private void processAnnotationOfArgument(Object argument, int index) {
		if (parameterAnnotations == null) {
			setParameter(index, argument);
			return;
		}
		Annotation[] annotations = parameterAnnotations[index];
		if (annotations == null || annotations.length == 0) {
			setParameter(index, argument);
			return;
		}
		if (getAnnotation(annotations, FirstResult.class) != null) {
			setFirstResult((Integer) argument);
			return;
		}
		if (getAnnotation(annotations, MaxResults.class) != null) {
			setMaxResults((Integer) argument);
			return;
		}

		Like like = getAnnotation(annotations, Like.class);
		if (like != null) {
			MatchMode matchMode = like.matchMode().convert();
			argument = matchMode.toMatchString(argument.toString());
		}

		Parameter parameter = getAnnotation(annotations, Parameter.class);
		if (parameter != null) {
			setParameter(parameter.value(), argument);
		} else {
			setParameter(index, argument);
		}
	}

	@SuppressWarnings("unchecked")
	private <T extends Annotation> T getAnnotation(Annotation[] annotations, Class<T> annotationClass) {
		for (Annotation each : annotations) {
			if (each.annotationType().equals(annotationClass)) {
				return (T) each;
			}
		}
		return null;
	}

	public void setFirstResult(Integer firstResult) {
		this.firstResult = firstResult;
	}

	public void setMaxResults(Integer maxResults) {
		this.maxResults = maxResults;
	}

	public Integer getFirstResult() {
		return firstResult;
	}

	public Integer getMaxResults() {
		return maxResults;
	}

	public Map<String, Object> getNamedArguments() {
		return namedArguments;
	}

	public Map<Integer, Object> getPositionArguments() {
		return positionArguments;
	}

}
