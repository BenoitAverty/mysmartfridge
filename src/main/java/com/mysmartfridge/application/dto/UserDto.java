package com.mysmartfridge.application.dto;

import com.mysmartfridge.domain.User;

/**
 * A user as exposed by the application layer.
 * 
 * A user has : 
 * <ul>
 * 	<li>An email address;</li>
 * 	<li>A role.</li>
 * </ul>
 */
public class UserDto {
	
	public UserDto(User user) {
		
		if(user != null) {
			this.email = user.getEmail();
			this.role = user.getRole().toString();
		}
	}
	
	/** Email address of the user. */
	public String email;
	
	/** Role of the user : represents the access level of the user. */
	public String role;
}