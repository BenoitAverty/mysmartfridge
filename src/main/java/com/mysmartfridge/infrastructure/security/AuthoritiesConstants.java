package com.mysmartfridge.infrastructure.security;

/** 
 * Constants for different authorities known to spring security.
 * They match the roles of the domain model.
 */
public final class AuthoritiesConstants {
	public static final String USER = "ROLE_USER";
	public static final String ADMIN = "ROLE_ADMIN";
	public static final String ANONYMOUS = "ROLE_ANONYMOUS";
	
	private AuthoritiesConstants() {}
}
