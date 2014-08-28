package com.norther.dynamic.dao.processor.support;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-19 ÏÂÎç08:41:05
 */
public class QueryStringUtils {

	private static final Log log = LogFactory.getLog(QueryStringUtils.class);

	public static final Pattern SUBSELECT_PATTERN = Pattern.compile("[(]+.*[.^]*from\\s+.*[)]+");

	public static final Pattern ORDER_BY_PATTERN = Pattern.compile("\\s+order\\s+by\\s+[^'\"]", Pattern.CASE_INSENSITIVE);

	public static final Pattern GROUP_BY_PATTERN = Pattern.compile("\\s+group\\s+by\\s+[^'\"]", Pattern.CASE_INSENSITIVE);

	private QueryStringUtils() {
	}

	public static String insertCondition(String queryString, List<String> conditionList) {
		if (hasSubselect(queryString)) {
			throw new IllegalArgumentException("the query string [" + queryString
					+ "] has subselect ,unassured position for conditions insert");
		}
		if (conditionList == null || conditionList.size() == 0) {
			return queryString;
		}
		if (log.isDebugEnabled()) {
			for (String each : conditionList) {
				log.debug("condition -> " + each);
			}
		}
		int insertIndex = findConditionsInsertIndex(queryString);
		String insert = getInsert(queryString, conditionList).trim();
		String left = queryString.substring(0, insertIndex).trim();
		String right = queryString.substring(insertIndex).trim();

		String insertedQueryString = new StringBuilder(left).append(" ").append(insert).append(" ").append(right).toString().trim();
		if (log.isInfoEnabled()) {
			log.info("inserted query string [" + insertedQueryString + "]");
		}

		return insertedQueryString;
	}

	private static boolean hasSubselect(String queryString) {
		Matcher matcher = SUBSELECT_PATTERN.matcher(queryString);
		return matcher.find();
	}

	private static String getInsert(String queryString, List<String> conditionList) {
		String condition = convertConditionList(conditionList);
		String insertString;
		if (hasWhere(queryString)) {
			insertString = "and " + condition;
		} else {
			insertString = "where " + condition;
		}
		return insertString;
	}

	private static String convertConditionList(List<String> conditionList) {
		StringBuilder conditions = new StringBuilder();
		for (int i = 0; i < conditionList.size(); i++) {
			String condition = conditionList.get(i);
			conditions.append(condition);
			if (i != (conditionList.size() - 1)) {
				conditions.append(" and ");
			}
		}
		return conditions.toString();
	}

	private static int findConditionsInsertIndex(String queryString) {

		int groupIndex = getIndexOfGroupBy(queryString);
		int orderIndex = getIndexOfOrderBy(queryString);

		if (groupIndex != -1) {
			return groupIndex;
		} else if (orderIndex != -1) {
			return orderIndex;
		} else {
			return queryString.length();
		}
	}

	private static int getIndexOfOrderBy(String string) {
		Matcher matcher = ORDER_BY_PATTERN.matcher(string);
		return matcher.find() ? matcher.start() : -1;
	}

	private static int getIndexOfGroupBy(String string) {
		Matcher matcher = GROUP_BY_PATTERN.matcher(string);
		return matcher.find() ? matcher.start() : -1;
	}

	private static boolean hasWhere(String string) {
		return string.toLowerCase().contains("where");
	}
}
