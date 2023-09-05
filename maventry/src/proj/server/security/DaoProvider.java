package proj.server.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import proj.server.security.securityClasses.*;
import proj.server.servicepack.IUserService;

public class DaoProvider extends DaoAuthenticationProvider {
	
	
	
   @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("came into the dao provider authenticate -------------------");

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Authentication AuthUser = null;
        // Replace this logic with   your actual user authentication mechanism
        UserDetails userDetails = getUserDetails(username);
        if(userDetails != null && userDetails.getPassword().equals(password)) {
        	AuthUser = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        	SecurityContextHolder.getContext().setAuthentication(AuthUser);
        	//System.out.println(AuthUser.isAuthenticated());
        }
		System.out.println("exiting the dao provider authenticate -------------------");

        return AuthUser;
    }	

    @Override
    public boolean supports(Class<?> authenticationClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationClass);
    }

    
    private UserDetails getUserDetails(String username) {
    	
    		UserDetailsService ob = getUserDetailsService();
    		UserDetails fulldetails = ob.loadUserByUsername(username);
    		
    		return fulldetails;
    	}
}
