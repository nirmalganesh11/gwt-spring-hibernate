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
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import proj.server.security.*;

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
		
		//authenticationEntryPoint.commence(request, response, null);
		String header = request.getHeader("WWW-Authenticate");
		if (header == null || !header.toLowerCase().startsWith("basic ")) {
			//change add
			filterChain.doFilter(request,response);
			return;
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
