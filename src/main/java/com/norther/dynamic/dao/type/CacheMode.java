package com.norther.dynamic.dao.type;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-9 12:54:40
 */
public enum CacheMode {
	GET, IGNORE, NORMAL, PUT, REFRESH, NULL;

	public org.hibernate.CacheMode convert() {
		if (this.equals(NULL)) {
			return null;
		}
		return org.hibernate.CacheMode.parse(this.toString());
	}
	
}


