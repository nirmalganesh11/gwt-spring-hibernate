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
	
	private LoginServiceDelegateImpl loginServiceDelegate;
	private UserService userService;
	private LoginServiceBoy loginService;
	private AuthenticatorStandAlone authenticationService;
	
	
	public BasicAuthStandAloneFilter(AuthenticationManager authenticationManager) {
		if (authenticationManager == null) {
			System.out.println("auth manager null ra bro");
		}
		this.authenticationManager = authenticationManager;
	}

	public BasicAuthStandAloneFilter(AuthenticationManager authenticationManager,
			AuthenticationEntryPoint authenticationEntryPoint) {
		if (authenticationManager == null) {
			System.out.println("Auth manager cannot be null bruv");
		}
		if (authenticationEntryPoint == null) {
			System.out.println("Entry point cannot be nulll bruv");
		}
		this.authenticationManager = authenticationManager;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("---------------------"+"came into basic auth filter"+ "-------------------------------------");
		String requestedUrl = request.getRequestURL().toString();
		System.out.println("here its asking this servlet"+requestedUrl);
		//authenticationEntryPoint.commence(request, response, null);
		String header = request.getHeader("Authorization");
		if (header == null || !header.toLowerCase().startsWith("basic ")) {
			//change add
			System.out.println("---------------------"+"exiting basic auth filter"+ "-------------------------------------");
			filterChain.doFilter(request,response);
			return;
		}
		String base64Credentials = header.substring("Basic ".length());
		String username ="";
    	String password ="";
    	String decodedCreds = decode(base64Credentials);
    	  String[] credentials = decodedCreds.split(":");
          if (credentials.length == 2) {
              username = credentials[0];
              password = credentials[1];
          } else {
              System.out.println("Invalid credentials format");
          }
//          System.out.println(username +"------------"+password);
//		Credential object = new Credential(username,password);
//		//authenticationService.authenticate(object);
//		System.out.println("that came here bro for the first time");
		
        UsernamePasswordAuthenticationToken newToken = new UsernamePasswordAuthenticationToken(username,password);
		authenticationManager.authenticate(newToken);
		System.out.println("---------------------"+"exiting auth filter after authentication"+ "-------------------------------------");

		
	}
	private String decode(String encodedCredentials) {
	    try {
	        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
	        return new String(decodedBytes, StandardCharsets.UTF_8);
	    } catch (IllegalArgumentException e) {
	        // Handle decoding errors
	        return null;
	    }
	}

	
	public LoginServiceDelegateImpl getLoginServiceDelegate() {
		return loginServiceDelegate;
	}

	public void setLoginServiceDelegate(LoginServiceDelegateImpl loginServiceDelegate) {
		this.loginServiceDelegate = loginServiceDelegate;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public LoginServiceBoy getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginServiceBoy loginService) {
		this.loginService = loginService;
	}

	public AuthenticatorStandAlone getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(AuthenticatorStandAlone authenticationService) {
		this.authenticationService = authenticationService;
	}
}
