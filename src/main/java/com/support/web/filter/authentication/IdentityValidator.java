package com.support.web.filter.authentication;

/**
 * 
 * @author xinyi
 *
 */
public interface IdentityValidator {
	
	public static final String VISITOR="com.website.validate.visitor";
	
	public static final String PRINCIPAL="com.website.validate.principal";
	
	public boolean isVisited();
	
	public String currentVisitor();
	
	public boolean isLogined();
	
	public Principal currentPrincipal();
	
	public boolean login(Verifier verifier) throws AuthenticationException,AuthorityNotAvailableException;
	
	public void logout();
	
	public void setAuthenticationProvider(
			AuthenticationProvider authenticationProvider);	
	
	public AuthenticationProvider getAuthenticationProvider();
}
