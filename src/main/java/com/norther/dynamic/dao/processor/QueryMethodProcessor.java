package com.norther.dynamic.dao.processor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.norther.dynamic.dao.DaoMethodProcessor;
import com.norther.dynamic.dao.annotation.query.Condition;
import com.norther.dynamic.dao.annotation.query.Conditions;
import com.norther.dynamic.dao.annotation.query.Query;
import com.norther.dynamic.dao.processor.support.QueryArguments;
import com.norther.dynamic.dao.processor.support.QueryPostProcessor;
import com.norther.dynamic.dao.processor.support.QueryStringUtils;
import com.norther.dynamic.dao.type.CacheMode;
import com.norther.dynamic.dao.type.FlushMode;
import com.norther.dynamic.dao.type.LockMode;
import com.norther.dynamic.dao.type.Null;

public class QueryMethodProcessor extends DaoMethodProcessor<Query> {

	@Override
	public Object process() {
		QueryArguments queryArguments = new QueryArguments(parameters, parameterAnnotations);
		org.hibernate.Query query = createQuery(queryArguments);
		if (log.isDebugEnabled()) {
			log.debug("query string -> " + query.getQueryString());
		}

		initializeQuery(queryArguments, query);
		return executeQuery(query);
	}

	private Object executeQuery(org.hibernate.Query query) {
		Class<?> processor = annotation.postProcessor();
		if (!(processor.equals(Null.class))) {
			return queryPostProcess(query, processor);

		}
		if (annotation.executeUpdate()) {
			return query.executeUpdate();
		}

		Class<?> returnType = method.getReturnType();
		if (returnType.equals(List.class)) {
			return query.list();
		} else if (returnType.equals(Iterator.class)) {
			return query.iterate();
		} else {
			return query.uniqueResult();
		}

	}

	private Object queryPostProcess(org.hibernate.Query query, Class<?> processor) {
		try {
			Object newInstance = processor.newInstance();
			if (newInstance instanceof QueryPostProcessor) {
				QueryPostProcessor queryProcessor = (QueryPostProcessor) newInstance;
				return queryProcessor.process(query);
			} else {
				throw new IllegalArgumentException("the argument " + newInstance.getClass() + "] is invalid for QueryProcessor");
			}
		} catch (InstantiationException e) {
			throw new IllegalArgumentException("cannot instance QueryProcessor", e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException("cannot instance QueryProcessor", e);
		}
	}

	private org.hibernate.Query createQuery(QueryArguments queryArguments) {

		org.hibernate.Query query;
		String name = annotation.name().trim();
		if (!name.equals("")) {
			query = hibernateDao.getNamedQuery(name);
		} else {
			String queryString = annotation.value();
			Conditions conditions = method.getAnnotation(Conditions.class);
			if (conditions != null) {
				queryString = processDynamicSegment(queryArguments, queryString, conditions.value());
			}
			if (annotation.sqlQuery()) {
				query = hibernateDao.createSqlQuery(queryString);
			} else {
				query = hibernateDao.createQuery(queryString);
			}
		}

		return query;
	}

	private void initializeQuery(QueryArguments queryArguments, org.hibernate.Query query) {
		Map<String, Object> namedArguments = queryArguments.getNamedArguments();
		if (!namedArguments.isEmpty()) {
			for (Entry<String, Object> each : namedArguments.entrySet()) {
				query.setParameter(each.getKey(), each.getValue());
			}
		}

		Map<Integer, Object> positionArguments = queryArguments.getPositionArguments();
		if (!positionArguments.isEmpty()) {
			List<Object> arguments = new ArrayList<Object>(positionArguments.values());

			for (int i = 0; i < arguments.size(); i++) {
				Object each = arguments.get(i);
				query.setParameter(i, each);
			}
		}

		Integer firstResult = queryArguments.getFirstResult();
		if (firstResult != null) {
			query.setFirstResult(firstResult);
		}
		Integer maxResults = queryArguments.getMaxResults();
		if (maxResults != null) {
			query.setMaxResults(maxResults);
		}

		if (annotation.readOnly()) {
			query.setReadOnly(true);
		}
		int timeout = annotation.timeout();
		if (timeout != -1) {
			query.setTimeout(timeout);
		}
		int fetchSize = annotation.fetchSize();
		if (fetchSize != -1) {
			query.setFetchSize(fetchSize);
		}
		FlushMode flushMode = annotation.flushMode();
		if (!flushMode.equals(FlushMode.NULL)) {
			query.setFlushMode(flushMode.convert());
		}
		CacheMode cacheMode = annotation.cacheMode();
		if (!cacheMode.equals(CacheMode.NULL)) {
			query.setCacheMode(cacheMode.convert());
		}
		if (annotation.cacheable()) {
			query.setCacheable(true);
		}
		String cacheRegion = annotation.cacheRegion().trim();
		if (!cacheRegion.equals("")) {
			query.setCacheRegion(cacheRegion);
		}
		LockMode lockMode = annotation.lockMode();
		if (!lockMode.equals(LockMode.NULL)) {
			query.setLockMode(annotation.lockModeAlias().trim(), lockMode.convert());
		}
		String comment = annotation.comment().trim();
		if (!comment.equals("")) {
			query.setComment(comment);
		}
	}

	private String processDynamicSegment(QueryArguments queryArguments, String queryString, Condition[] conditions) {
		List<String> validConditions = new ArrayList<String>();
		for (int i = 0; i < conditions.length; i++) {
			String querySegment = conditions[i].value();
			int indexOfColon = querySegment.indexOf(":");
			if (indexOfColon != -1) {
				String name = querySegment.substring(indexOfColon + 1).trim();
				int indexOfFirstBlank = name.indexOf(" ");
				if (indexOfFirstBlank != -1) {
					name = name.substring(0, indexOfFirstBlank);
				}
				if (queryArguments.hasValue(name)) {
					validConditions.add(querySegment);
				} else {
					queryArguments.removeParameter(name);
				}
			} else {
				if (queryArguments.hasValue(i)) {
					validConditions.add(querySegment);
				} else {
					queryArguments.removeParameter(i);
				}
			}
		}

		return QueryStringUtils.insertCondition(queryString, validConditions);
	}

}
