package com.mysmartfridge.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mysmartfridge.domain.User;
import com.mysmartfridge.domain.repositories.UsersRepository;

@Configuration
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);
	
	@Autowired
	private UsersRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
		
		log.debug("Authenticating {}", login);
		
		String lowercaseLogin = login.toLowerCase();
		
		Optional<User> userFromDatabase = userRepository.findByEmail(lowercaseLogin);
		
		return userFromDatabase.map(user -> {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
			grantedAuthorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.ADMIN));
			
			return new org.springframework.security.core.userdetails.User(lowercaseLogin, user.getPassword(), grantedAuthorities);
		}).orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
	}
	
	
	
}
