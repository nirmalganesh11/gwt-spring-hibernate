package proj.server;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import proj.client.servicesClient.AuthenticationService;
import proj.server.security.DaoProvider;
import proj.server.security.securityClasses.CustomGrantedAuthority;

public class AuthenticationServiceImpl extends RemoteServiceServlet implements AuthenticationService {

	private static final long serialVersionUID = 1L;

	private AuthenticationProvider authenticationProvider;
	String username;

	public AuthenticationServiceImpl() {
	   //this.authenticationProvider = new CustomAuthenticationProvider();
	}
	
    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Override
    public boolean authenticate(String encodedString) {
    	
    	if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated() == true) {
    		System.out.println("came into authentication service impl -------------------");
    		// Get the current Authentication object from SecurityContextHolder
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            
            // Print out the basic information from the Authentication object
            if (authentication != null) {
                System.out.println("Principal (Username): " + authentication.getName());
                System.out.println("Credentials (Password or Token): " + authentication.getCredentials());
                System.out.println("Authorities/Roles: " + authentication.getAuthorities());
                System.out.println("Authenticated: " + authentication.isAuthenticated());
                System.out.println("Details: " + authentication.getDetails());
            } else {
                System.out.println("No authentication information available.");
            }
    		
    		return true;
    	}
    	return false;
//    	String username ="";
//    	String password ="";
//    	String decodedCreds = decode(encodedString);
//    	  String[] credentials = decodedCreds.split(":");
//          if (credentials.length == 2) {
//              username = credentials[0];
//              password = credentials[1];
//          } else {
//              System.out.println("Invalid credentials format");
//          }
//          System.out.println(username +"------------"+password);
//          
//        Authentication auth = new UsernamePasswordAuthenticationToken(username,password);
//        DaoProvider doap = new DaoProvider();
//        Authentication authbro = doap.authenticate(auth);
//        //System.out.println(authbro.isAuthenticated());
//  		//Authentication doneUser = authenticationProvider.authenticate(auth);
//  		//SecurityContextHolder.getContext().setAuthentication(doneUser);
//        if(authbro !=null)
//        	return authbro.isAuthenticated();
//        else
//        	return false;
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
	

	@Override
	public String getLoggedInUser() {
		 
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	        if (authentication != null && authentication.isAuthenticated()) {
	            Object principal = authentication.getPrincipal();
	            
	            if (principal instanceof UserDetails) {
	                UserDetails userDetails = (UserDetails) principal;
	                String str="";
	                for(GrantedAuthority authority : userDetails.getAuthorities())
	                {
	                	str = str + " "+authority.getAuthority();
	                }
	                return username = userDetails.getUsername()+str+"--"+authentication.isAuthenticated();
	               
	            } else {
	                return username = principal.toString()+authentication.isAuthenticated();
	                
	            }
	        } else {
	        	return "user is not authenticated";
	        }
	}

	@Override
	public void LogoutUser() {
		SecurityContextLogoutHandler ob = new SecurityContextLogoutHandler();
		ob.logout(getThreadLocalRequest(), getThreadLocalResponse(), SecurityContextHolder.getContext().getAuthentication());
		
	}
}
