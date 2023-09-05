package proj.server.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import proj.server.security.securityClasses.CustomGrantedAuthority;
import proj.server.security.securityClasses.Role;
import proj.server.security.securityClasses.UserAccount;
import proj.server.servicepack.IDesigService;
import proj.server.servicepack.IUserService;
import proj.shared.User;

public class LoginServiceBoy implements UserDetailsService {
	
	private  IUserService userserv;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		
		UserAccount newacc = new UserAccount();
		User user =	userserv.findByUsername(username);
		
		newacc.setUsername(user.getUsername());
		newacc.setPassword(user.getPassword());
		List<CustomGrantedAuthority> cust = new ArrayList<CustomGrantedAuthority>();
		
		String arr[] = user.getRolesArray();
		List<Role> userRoles = new ArrayList<Role>();
		for(String each: arr) {
			cust.add(new CustomGrantedAuthority(each));
			userRoles.add(new Role(each));
		}
		
		newacc.setRoles(userRoles);
		newacc.setAuthorities(cust);
		
		return newacc;	
	}
	
	public IUserService getUserserv() {
		return userserv;
	}


	public void setUserserv(IUserService userserv) {
		this.userserv = userserv;
	}

}
