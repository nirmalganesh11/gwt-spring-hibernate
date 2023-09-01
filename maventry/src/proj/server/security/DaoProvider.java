package proj.server.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import proj.server.security.securityClasses.*;

public class DaoProvider extends DaoAuthenticationProvider {
	
	
   @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Authentication AuthUser = null;
        // Replace this logic with   your actual user authentication mechanism
        UserDetails userDetails = getUserDetails(username,password);
        if(userDetails != null && userDetails.getPassword().equals(password)) {
        	AuthUser = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        	SecurityContextHolder.getContext().setAuthentication(AuthUser);
        	//System.out.println(AuthUser.isAuthenticated());
        }
        return AuthUser;
    }	

    @Override
    public boolean supports(Class<?> authenticationClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationClass);
    }

    
    private UserDetails getUserDetails(String username,String password) {
    		LoginServiceBoy obj = new LoginServiceBoy();
    		UserDetails mustreturn = obj.loadUserByUsername(username);
    		
    		return mustreturn;
    		
    	}
}
