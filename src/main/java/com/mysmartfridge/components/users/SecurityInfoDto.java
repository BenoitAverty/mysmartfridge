package com.mysmartfridge.components.users;

import java.util.List;
import java.util.stream.Collectors;

import com.mysmartfridge.components.users.domain.User;

public class SecurityInfoDto {
	public List<String> authorities;
	public String password;
	public String login;
	
	public SecurityInfoDto(User u) {
		this.password = u.getPassword();
		this.login = u.getEmail();
		this.authorities = u.getRole().getWeakerRoles().stream().map(r -> r.toString()).collect(Collectors.toList());
	}
}
