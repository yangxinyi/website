package com.norther.dynamic.dao.type;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-9 12:55:58
 */
public enum LockMode {
	FORCE, NONE, READ, UPGRADE, UPGRADE_NOWAIT, WRITE, NULL;

	public org.hibernate.LockMode convert() {
		if (this.equals(NULL)) {
			return null;
		}
		return org.hibernate.LockMode.parse(this.toString());
	}
}
