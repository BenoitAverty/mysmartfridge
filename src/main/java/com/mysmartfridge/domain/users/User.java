package com.mysmartfridge.domain.users;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mysmartfridge.domain.Entity;

import lombok.Getter;

/**
 * A user of the application.
 */
@Document(collection="users")
public class User extends Entity {

	/**
	 * Email address of the user.
	 */
	@Getter
	private String email;

	/**
	 * Hashed password of the user.
	 */
	@Getter
	private String password;

	/**
	 * Role of the user in the application. Is used to determine rights and do
	 * some processes differently regarding the role.
	 */
	@Getter
	private Role role;
}
