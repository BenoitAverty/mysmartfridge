package com.mysmartfridge.components.users.domain;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represent a role that a user can have in the application.
 */
public enum Role {
	ROLE_ANONYMOUS(10), ROLE_USER(20), ROLE_ADMIN(30);

	/**
	 * Rights level of the role. A role with a superior rights level has rights
	 * for all the inferior roles.
	 */
	private Integer rightsLevel;

	private Role(Integer rightsLevel) {
		this.rightsLevel = rightsLevel;
	}

	/**
	 * Check if the current role has equal or higher rights level than the
	 * provided role.
	 * 
	 * Example : let's imagine an action that requires an user to be a USER or
	 * higher. to check if the current user has rights, you would do:
	 * 
	 * <pre>
	 * currentUser.getRole().isSufficient(Role.USER)
	 * </pre>
	 * 
	 * @param authorizedRole
	 *            the reference role for the action that's right protected.
	 * @return true if the current role has sufficient rights level.
	 */
	public Boolean isSufficient(Role authorizedRole) {
		return this.rightsLevel >= authorizedRole.rightsLevel;
	}
	
	/**
	 * Return a set containing the current role as well as all weaker roles.
	 */
	public Set<Role> getWeakerRoles() {
		return Stream.of(this.getClass().getEnumConstants())
				.filter(r -> this.isSufficient(r))
				.collect(Collectors.toSet());
	}
}
