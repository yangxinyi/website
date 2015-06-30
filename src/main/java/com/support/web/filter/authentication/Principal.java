package com.support.web.filter.authentication;

import java.io.Serializable;

/**
 * 
 * @author xinyi
 *
 */
public interface Principal{

	public Serializable getIdentity();
	
	public Long getLastLoginSeconds();
	
	public String getName();
	
	public String getDisplayName();
	
	public boolean isAvailable();

}
