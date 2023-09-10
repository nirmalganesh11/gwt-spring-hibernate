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
import proj.server.sessionManagement.UserSession;


public class DaoProvider extends DaoAuthenticationProvider {
	
	private UserDetailsService userServiceMechanism;
	
	private UserSession userSession;
	
 

@Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        Authentication AuthUser = null;

        UserDetails userDetails = getUserDetails(username);
        
        if(userDetails != null && userDetails.getPassword().equals(password)) {
        	
        	AuthUser = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        	SecurityContextHolder.getContext().setAuthentication(AuthUser);
        	userSession.setAuthenticatedUser(AuthUser);
        	
        }
        
        return AuthUser;
    }	

    @Override
    public boolean supports(Class<?> authenticationClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationClass);
    }

    
    private UserDetails getUserDetails(String username) {
    	
    		userServiceMechanism = getUserDetailsService();
    		UserDetails fulldetails = userServiceMechanism.loadUserByUsername(username);
    		
    		return fulldetails;
    	}
    
    
    public UserDetailsService getUserServiceMechanism() {
  		return userServiceMechanism;
  	}

  	public void setUserServiceMechanism(UserDetailsService userServiceMechanism) {
  		this.userServiceMechanism = userServiceMechanism;
  	}

  	public UserSession getUserSession() {
  		return userSession;
  	}

  	public void setUserSession(UserSession userSession) {
  		this.userSession = userSession;
  	}
}
