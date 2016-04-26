package com.mysmartfridge.components.users;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;

/**
 * A user of the application.
 */
@Document(collection="users")
class User {

	@Id
	private UUID id;
	
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
