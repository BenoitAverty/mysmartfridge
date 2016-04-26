package com.mysmartfridge.infrastructure.security;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mysmartfridge.components.users.SecurityInfoDto;
import com.mysmartfridge.components.users.UsersApplication;

/**
 * Service used by spring security to retrieve user details. This service is
 * able to retrieve a domain model User and convert it to spring security object
 * UserDetails.
 */
@Configuration
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

	@Autowired
	private UsersApplication usersApplication;

	/**
	 * Finds a user by its username, and returns a spring security UserDetails
	 * object.
	 */
	@Override
	public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {

		log.debug("Authenticating {}", login);

		SecurityInfoDto userInfo = usersApplication.getSecurityInfoForUsername(login)
				.orElseThrow(() -> new UsernameNotFoundException("The username " + login + " was not found in database."));
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		
		grantedAuthorities.addAll(
			userInfo.authorities.stream()
				.map(r -> new SimpleGrantedAuthority(r))
				.collect(Collectors.toList())
		);
		
		return new org.springframework.security.core.userdetails.User(userInfo.login, userInfo.password, grantedAuthorities);
	}

}
