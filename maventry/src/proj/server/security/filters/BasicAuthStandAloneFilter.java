package proj.server.security.filters;

import java.util.Base64;
import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import proj.server.security.*;
import proj.server.security.*;
import proj.server.security.securityClasses.*;

public class BasicAuthStandAloneFilter extends OncePerRequestFilter{
	
	
	private AuthenticationEntryPoint authenticationEntryPoint;
	private AuthenticationManager authenticationManager;
	private String username;
	private String password;
	
	public BasicAuthStandAloneFilter(AuthenticationManager authenticationManager) {
		if (authenticationManager == null) {
			System.out.println("Authentication Manager cannot be null");
		}
		this.authenticationManager = authenticationManager;
	}

	public BasicAuthStandAloneFilter(AuthenticationManager authenticationManager,
			AuthenticationEntryPoint authenticationEntryPoint) {
		if (authenticationManager == null) {
			System.out.println("Authentication manager cannot be null");
		}
		if (authenticationEntryPoint == null) {
			System.out.println("Entry point cannot be nulll");
		}
		this.authenticationManager = authenticationManager;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		if (header == null || !header.toLowerCase().startsWith("basic ")) {
			filterChain.doFilter(request,response);
			return;
		}
		
		String base64Credentials = header.substring("Basic ".length());
    	decode(base64Credentials);
    	         
        UsernamePasswordAuthenticationToken newSignInToken = new UsernamePasswordAuthenticationToken(username,password);
		authenticationManager.authenticate(newSignInToken);
		
	}
	
	
	private void decode(String encodedCredentials) {
	    try {
	        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
	        String decodeCreds = new String(decodedBytes, StandardCharsets.UTF_8);
	        setCredentials(decodeCreds);
	        
	    } catch (IllegalArgumentException e) {
	       
	        return;
	    }
	}
	
	private void setCredentials(String wholeString) {
		
		String[] credentials = wholeString.split(":");
        if (credentials.length == 2) {
            username = credentials[0];
            password = credentials[1];
        } else {
            System.out.println("Invalid credentials format");
        }
		
	}
	
}
