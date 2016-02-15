package com.mysmartfridge.application.dto;

import com.mysmartfridge.domain.User;

/**
 * A user as exposed by the application layer.
 * 
 */
public class UserDto {
	
	public UserDto(User user) {
		
		if(user != null) {
			this.email = user.getEmail();
		}
	}
	
	/** Email address of the user. */
	public String email;
}
