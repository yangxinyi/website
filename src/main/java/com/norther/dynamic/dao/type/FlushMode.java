package com.norther.dynamic.dao.type;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-10 01:26:49
 */
public enum FlushMode {
	ALWAYS, AUTO, COMMIT, MANUAL, NEVER, NULL;

	public org.hibernate.FlushMode convert() {
		if (this.equals(NULL)) {
			return null;
		}
		return org.hibernate.FlushMode.parse(this.toString());
	}
}
