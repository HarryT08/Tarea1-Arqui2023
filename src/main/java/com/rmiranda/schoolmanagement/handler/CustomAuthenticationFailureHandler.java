package com.rmiranda.schoolmanagement.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex)
            throws IOException, ServletException {


        if (BadCredentialsException.class == ex.getClass()) {
            res.sendRedirect("/login?error");
        }

        if (DisabledException.class == ex.getClass()) {
            res.sendRedirect("/login?disabled");
        }
    }

}
