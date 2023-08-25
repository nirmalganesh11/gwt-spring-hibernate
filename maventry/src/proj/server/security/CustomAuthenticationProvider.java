package proj.server.security;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import proj.server.servicepack.IUserService;



public class CustomAuthenticationProvider implements AuthenticationProvider {
		private ApplicationContext context;
		private IUserService userServ;

		public CustomAuthenticationProvider(){
			context = new ClassPathXmlApplicationContext("applicationContext.xml");
			userServ = context.getBean(IUserService.class);
		}
	
	   @Override
	    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	        String username = authentication.getName();
	        String password = authentication.getCredentials().toString();
	        
	        // Replace this logic with your actual user authentication mechanism
	        UserDetails userDetails = getUserDetails(username);

	        if (userDetails != null && userDetails.getPassword().equals(password)) {
	            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
	        } else {
	            throw new UsernameNotFoundException("Authentication failed for user: " + username);
	        }
	    }	

	    @Override
	    public boolean supports(Class<?> authenticationClass) {
	        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationClass);
	    }

	    
	    private UserDetails getUserDetails(String username) {
	        
	    	proj.shared.User foundedUser = userServ.findByUsername(username);
	    	if(foundedUser != null) {
	    		return User.builder()
	    				.username(foundedUser.getUsername())
	    				.password(foundedUser.getPassword())
	    				.roles(foundedUser.getDesignation())
	    				.build();
	    		
	    	}
	    	else {
	    		return null;
	    	}
	    	
	    	
	    	
	    	
	    	
//	        if ("user".equals(username)) {
//	            return User.builder()
//	                    .username("user")
//	                    .password("c")
//	                    .roles("USER")
//	                    .build();
//	        }
//	        if ("admin".equals(username)) {
//	            return User.builder()
//	                    .username("admin")
//	                    .password("c")
//	                    .roles("USER","ADMIN")
//	                    .build();
//	        }
	        
	        
	        
//	        return null;
	    }
}
