package com.mysmartfridge.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mysmartfridge.application.dto.UserDto;
import com.mysmartfridge.domain.repositories.UsersRepository;
import com.mysmartfridge.security.SecurityUtils;

/**
 * Application layer related to manipulation of users (as a domain entity or as a technical device).
 */
@Service
public class UsersApplication {

	@Autowired
	UsersRepository usersRepository;
	
	public Optional<UserDto> getLoggedInUser() {
		
		if(!SecurityUtils.isAuthenticated()) {
			return Optional.empty();
		}
		
		String currentLogin = SecurityUtils.getCurrentUserLogin();
		
		return Optional.of(new UserDto(usersRepository.findByEmail(currentLogin).orElseThrow(() -> new UsernameNotFoundException("Logged in user does not exist"))));
	}
}
