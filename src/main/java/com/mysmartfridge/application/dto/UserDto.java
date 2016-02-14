package com.mysmartfridge.application.dto;

import lombok.AllArgsConstructor;

/**
 * A user as exposed by the application layer.
 * 
 */
@AllArgsConstructor
public class UserDto {
	
	/** The tid of the user in the system. Represents its identity. */
	public Long tid;
	
	/** Email address of the user. */
	public String email;
}
