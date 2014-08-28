package com.norther.dynamic.dao.type;

/**
 * 
 * @author Norther Wang
 * @since 2008-3-16 ионГ12:38:57
 */
public final class Null {

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj.getClass().equals(Null.class)) {
			return true;
		}

		return false;
	}

}
