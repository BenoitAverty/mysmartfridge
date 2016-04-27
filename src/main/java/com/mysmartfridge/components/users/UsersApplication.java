package com.mysmartfridge.components.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysmartfridge.components.users.domain.User;
import com.mysmartfridge.components.users.domain.UsersRepository;
import com.mysmartfridge.infrastructure.security.SecurityUtils;

/**
 * Application layer related to manipulation of users (as a domain entity or as
 * a technical device).
 */
@Service
public class UsersApplication {

	@Autowired
	UsersRepository usersRepository;

	/**
	 * Find the currently logged in user.
	 * 
	 * @return an Optional containing the user that's currently logged in. If no
	 * user is logged in, or if the logged in user doesn't exist in database,
	 * returns an empty optional.
	 */
	public Optional<UserDto> getLoggedInUser() {

		if (!SecurityUtils.isAuthenticated()) {
			return Optional.empty();
		}

		String currentLogin = SecurityUtils.getCurrentUserLogin();

		return usersRepository.findByEmail(currentLogin).map(u -> new UserDto(u));
	}
	
	public Optional<SecurityInfoDto> getSecurityInfoForUsername(String login) {
		
		Optional<User> userFromDatabase = usersRepository.findByEmail(login.toLowerCase());
		
		return userFromDatabase.map(SecurityInfoDto::new);
	}
}
