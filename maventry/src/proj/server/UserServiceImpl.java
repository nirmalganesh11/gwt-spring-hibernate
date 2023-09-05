package proj.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import proj.client.servicesClient.UserServiceClient;
import proj.server.security.DaoProvider;
import proj.server.security.securityClasses.CustomGrantedAuthority;
import proj.server.security.securityClasses.UserAccount;
import proj.server.servicepack.IUserService;
import proj.shared.User;


public class UserServiceImpl extends RemoteServiceServlet implements UserServiceClient {

	private static final long serialVersionUID = 1L;
	
	private ApplicationContext context;
	private IUserService userServ;

	public UserServiceImpl(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userServ = context.getBean(IUserService.class);
		
	}

	@Override
	public boolean loginUserAuth(User logDetails) {
		User user = userServ.findByUsername(logDetails.getUsername());
	    if (user != null && user.getPassword().equals(logDetails.getPassword()))
	        return true;
	    else
	        return false;
	}

	@Override
	public String signUpAuth(User newUser) {
		
		UserAccount signUp = new UserAccount();
		signUp.setUsername(newUser.getUsername());
		signUp.setPassword(signUp.getPassword());
		List<CustomGrantedAuthority> custom_auth = new ArrayList<CustomGrantedAuthority>();
		for(String a:newUser.getRolesArray()) {
			custom_auth.add(new CustomGrantedAuthority(a));
		}
		signUp.setAuthorities(custom_auth);
        Authentication ause = new UsernamePasswordAuthenticationToken(signUp,newUser.getPassword(),signUp.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(ause);
	
		return userServ.addUser(newUser);
	}

	@Override
	public String checkUsername(String username) {
		return userServ.validateUsername(username);
	}
	
}
