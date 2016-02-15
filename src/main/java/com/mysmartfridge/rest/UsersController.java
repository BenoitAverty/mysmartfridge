package com.mysmartfridge.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysmartfridge.application.UsersApplication;
import com.mysmartfridge.application.dto.UserDto;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	private final Logger log = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private UsersApplication usersApplication;
	
	@RequestMapping(value="/current", method=RequestMethod.GET)
	public ResponseEntity<UserDto> getUsersCurrent() {
		
		log.debug("REST request to check if the user is authenticated.");
		
		Optional<UserDto> loggedInUser = usersApplication.getLoggedInUser();
		
		return loggedInUser.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));	
	}
}
