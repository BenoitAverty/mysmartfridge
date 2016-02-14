package com.mysmartfridge.rest;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysmartfridge.application.dto.UserDto;

@RestController
@RequestMapping("/api")
public class SecurityController {

	private final Logger log = LoggerFactory.getLogger(SecurityController.class);
	
	@RequestMapping(value="/authenticate", method=RequestMethod.GET)
	public ResponseEntity<UserDto> isAuthenticated(HttpServletRequest request) {
		
		log.debug("REST request to check if the user is authenticated.");
		
		UserDto loggedInUser = new UserDto(0L, request.getRemoteUser());
		
		return new ResponseEntity<UserDto>(loggedInUser, HttpStatus.OK);		
	}
}
