package com.mysmartfridge.infrastructure.security;

import com.mysmartfridge.domain.users.Role;

/** 
 * Constants for different authorities known to spring security.
 * They match the roles of the domain model.
 */
public final class AuthoritiesConstants {
	public static final String USER = Role.USER.toString();
	public static final String ADMIN = Role.ADMIN.toString();
	public static final String ANONYMOUS = Role.ANONYMOUS.toString();
	
	private AuthoritiesConstants() {}
}
