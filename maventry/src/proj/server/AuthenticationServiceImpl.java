package proj.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import proj.client.servicesClient.AuthenticationService;
import proj.server.security.CustomAuthenticationProvider;

public class AuthenticationServiceImpl extends RemoteServiceServlet implements AuthenticationService {

	private static final long serialVersionUID = 1L;

	private AuthenticationProvider authenticationProvider;

	public AuthenticationServiceImpl() {
	   this.authenticationProvider = new CustomAuthenticationProvider();
	}
	
    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public boolean authenticate(String username, String password) {
        
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            
            Authentication result = authenticationProvider.authenticate(authentication);
           
            SecurityContextHolder.getContext().setAuthentication(result);

            return result.isAuthenticated();
            
        } catch (AuthenticationException e) {
            
            return false;
        }
    }
	
    
	

	@Override
	public String getLoggedInUser() {
		 String username;
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	        if (authentication != null && authentication.isAuthenticated()) {
	            Object principal = authentication.getPrincipal();

	            if (principal instanceof UserDetails) {
	                UserDetails userDetails = (UserDetails) principal;
	                return username = userDetails.getUsername();
	               
	            } else {
	                return username = principal.toString();
	                
	            }
	        } else {
	        	return "user is not authenticated";
	        }
	}
}
