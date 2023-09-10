//package proj.server.security;
//
//import proj.server.security.securityClasses.*;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import proj.server.security.securityClasses.CustomGrantedAuthority;
//import proj.server.security.securityClasses.Role;
//import proj.server.security.securityClasses.UserAccount;
//import proj.server.servicesInterfaces.UserService;
//import proj.shared.User;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//public class LoginServiceDelegateImpl {
//	
//	private UserService userServiceDao;
//	private UserService userService;
//	
//	public LoginServiceDelegateImpl() {
//		
//	}
//	
//	public void authenticate(Credential cred) {
//		String username  = cred.getUsername();
//		String password = cred.getPassword();
//		UserAccount authuser = getUserDataFromDb(username);
//		if(authuser != null  && authuser.getPassword().equals(password)) {
//			Authentication doneauth = new UsernamePasswordAuthenticationToken(authuser,password,authuser.getAuthorities());
//			System.out.println("came here bruv");
//			//SecurityContextHolder.setStrategyName("MODE_GLOBAL");
//			SecurityContextHolder.getContext().setAuthentication(doneauth);
//		}
//		
//		
//		
//	}
//	public UserService getUserServiceDao() {
//		return userServiceDao;
//	}
//
//	public void setUserServiceDao(UserService userServiceDao) {
//		this.userServiceDao = userServiceDao;
//	}
//
//	public LoginServiceDelegateImpl(UserService userserv) {
//		this.userServiceDao = userserv;
//	}
//	
//	public UserService getUserService() {
//		return userServiceDao;
//	}
//
//	public void setUserService(UserService userService) {
//		this.userServiceDao = userService;
//	}
//	
//	public UserAccount getUserDataFromDb(String username) {
//		
//		UserAccount newacc = new UserAccount();
//		User user = userServiceDao.findByUsername(username);
//		
//		newacc.setUsername(user.getUsername());
//		newacc.setPassword(user.getPassword());
//		List<CustomGrantedAuthority> cust = new ArrayList<CustomGrantedAuthority>();
//		
//		String arr[] = user.getRolesArray();
//		List<Role> userRoles = new ArrayList<Role>();
//		for(String each: arr) {
//			cust.add(new CustomGrantedAuthority(each));
//			userRoles.add(new Role(each));
//		}
//		
//		newacc.setRoles(userRoles);
//		newacc.setAuthorities(cust);
//		
//		//System.out.println(newacc.getPassword());
//		return newacc;
//		
//	}
//	
//
//}
