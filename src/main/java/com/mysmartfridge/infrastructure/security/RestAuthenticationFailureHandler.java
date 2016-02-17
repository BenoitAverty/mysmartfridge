package com.mysmartfridge.infrastructure.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * Returns a 401 error code (Unauthorized) to the client, when rest authentication fails.
 */
@Component
public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private static final Logger log = LoggerFactory.getLogger(RestAuthenticationFailureHandler.class);
	
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception) throws IOException, ServletException {

    	log.debug("Auth failure : " + exception);
    	
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
    }
}
