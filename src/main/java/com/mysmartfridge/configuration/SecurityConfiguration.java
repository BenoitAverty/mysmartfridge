package com.mysmartfridge.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mysmartfridge.domain.User;
import com.mysmartfridge.domain.repositories.UserRepository;
import com.mysmartfridge.security.AuthoritiesConstants;

@Configuration
public class SecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

	@Autowired
	UserRepository userRepository;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
	}

	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				User user = userRepository.findByEmail(email);
				if(user != null) {
					return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true, AuthorityUtils.createAuthorityList(AuthoritiesConstants.USER));
				}
				else {
					throw new UsernameNotFoundException("Could not find user " + email);
				}
			}
		};
	}

}
