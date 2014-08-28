package com.norther.dynamic.dao.type;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-17 ÏÂÎç09:44:26
 */
public enum MatchMode {
	ANYWHERE, END, EXACT, START;

	public org.hibernate.criterion.MatchMode convert() {
		if (this.equals(ANYWHERE)) {
			return org.hibernate.criterion.MatchMode.ANYWHERE;
		} else if (this.equals(END)) {
			return org.hibernate.criterion.MatchMode.END;
		} else if (this.equals(EXACT)) {
			return org.hibernate.criterion.MatchMode.EXACT;
		} else if (this.equals(START)) {
			return org.hibernate.criterion.MatchMode.START;
		} else {
			throw new IllegalStateException("illegal state for MatchMode[" + this + "]");
		}
	}
}
