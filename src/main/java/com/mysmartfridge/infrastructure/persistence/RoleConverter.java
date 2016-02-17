package com.mysmartfridge.infrastructure.persistence;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.mysmartfridge.domain.Role;

/**
 * Converter between a Role domain object and a String stored in databse.
 * 
 * This class is used by JPA when storing and retrieving a user in the database.
 */
@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

	/**
	 * Convert a role to a string (uses simply the tostring method).
	 */
	@Override
	public String convertToDatabaseColumn(Role r) {
		return r.toString();
	}

	/**
	 * Convert a String to a Role, by testing the toString() method of each role
	 * until finding one that corresponds to the value in database. Otherwise,
	 * returns the anonymous role.
	 */
	@Override
	public Role convertToEntityAttribute(String dbData) {
		for (Role r : Role.class.getEnumConstants()) {
			if (r.toString().equals(dbData))
				return r;
		}

		return Role.ANONYMOUS;
	}

}
