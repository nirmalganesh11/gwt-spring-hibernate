package proj.server.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import proj.server.security.securityClasses.CustomGrantedAuthority;
import proj.server.security.securityClasses.Role;
import proj.server.security.securityClasses.UserAccount;
import proj.server.servicepack.IUserService;
import proj.shared.User;

public class LoginServiceDelegateImpl {
	
	private IUserService userServiceDao;
	private UserService userService;
	
	public LoginServiceDelegateImpl() {
		
	}
	
	public IUserService getUserServiceDao() {
		return userServiceDao;
	}

	public void setUserServiceDao(IUserService userServiceDao) {
		this.userServiceDao = userServiceDao;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public LoginServiceDelegateImpl(IUserService userserv) {
		this.userServiceDao = userserv;
	}
	
	public IUserService getUserService() {
		return userServiceDao;
	}

	public void setUserService(IUserService userService) {
		this.userServiceDao = userService;
	}
	public UserAccount getUserDataFromDb(String username) {
		
		UserAccount newacc = new UserAccount();
		User user = userServiceDao.findByUsername(username);
		
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
		
		//System.out.println(newacc.getPassword());
		return newacc;
		
	}
	

}
