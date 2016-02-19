package com.mysmartfridge.domain.users;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represent a role that a user can have in the application.
 */
public enum Role {
	ANONYMOUS("ROLE_ANONYMOUS", 10), USER("ROLE_USER", 20), ADMIN("ROLE_ADMIN", 30);

	/** label of the role, used for serialization. */
	private String label;

	/**
	 * Rights level of the role. A role with a superior rights level has rights
	 * for all the inferior roles.
	 */
	private Integer rightsLevel;

	private Role(String role_label, Integer rightsLevel) {
		this.label = role_label;
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
	
	/**
	 * Serialize the role as its role label.
	 */
	public String toString() {
		return this.label;
	}
}
