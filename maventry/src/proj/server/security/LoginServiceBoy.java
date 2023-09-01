package proj.server.security;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import proj.server.servicepack.IDesigService;
import proj.server.servicepack.IUserService;

public class LoginServiceBoy implements UserDetailsService {
	
	
	private ApplicationContext context;
	private IUserService userserv;
	
	public LoginServiceBoy(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userserv = context.getBean(IUserService.class);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LoginServiceDelegateImpl newobj = new LoginServiceDelegateImpl(userserv);
		return newobj.getUserDataFromDb(username);
		//UserDetails man = new UserClass(username,"rechkottaku");
		//return man;
	}

}
