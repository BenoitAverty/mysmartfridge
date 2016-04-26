package com.mysmartfridge.infrastructure.security;

import com.mysmartfridge.domain.users.Role;

/** 
 * Constants for different authorities known to spring security.
 * They match the roles of the domain model.
 */
public final class AuthoritiesConstants {
	public static final String USER = Role.ROLE_USER.name();
	public static final String ADMIN = Role.ROLE_ADMIN.name();
	public static final String ANONYMOUS = Role.ROLE_ANONYMOUS.name();
	
	private AuthoritiesConstants() {}
}
