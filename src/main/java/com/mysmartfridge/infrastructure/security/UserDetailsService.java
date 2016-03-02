package com.mysmartfridge.infrastructure.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mysmartfridge.domain.users.User;
import com.mysmartfridge.domain.users.UsersRepository;

/**
 * Service used by spring security to retrieve user details. This service is
 * able to retrieve a domain model User and convert it to spring security object
 * UserDetails.
 */
@Configuration
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

	@Autowired
	private UsersRepository userRepository;

	/**
	 * Finds a user by its username, and returns a spring security UserDetails
	 * object.
	 */
	@Override
	public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {

		log.debug("Authenticating {}", login);

		String lowercaseLogin = login.toLowerCase();

		Optional<User> userFromDatabase = userRepository.findByEmail(lowercaseLogin);

		return userFromDatabase.map(user -> {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities.addAll(
					user.getRole().getWeakerRoles().stream()
					.map(r -> new SimpleGrantedAuthority(r.toString()))
					.collect(Collectors.toList())
				);

			return new org.springframework.security.core.userdetails.User(lowercaseLogin, user.getPassword(), grantedAuthorities);
		})
		.orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
	}

}
