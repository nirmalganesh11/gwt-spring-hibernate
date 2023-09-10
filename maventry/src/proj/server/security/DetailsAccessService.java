package proj.server.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import proj.server.security.securityClasses.CustomGrantedAuthority;
import proj.server.servicesInterfaces.RoleService;
import proj.server.servicesInterfaces.UserService;
import proj.server.sessionManagement.UserSession;
import proj.shared.User;
import proj.shared.security.Role;
import proj.shared.security.UserAccount;

public class DetailsAccessService implements UserDetailsService {
	
	private  UserService userServ;
	
	private RoleService roleServ;
	
	private UserSession userSession;
	
	


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		
		UserAccount newacc = new UserAccount();
		List<Role> userRoles = new ArrayList<Role>();
		User user =	userServ.findByUsername(username);
		
		newacc.setUsername(user.getUsername());
		newacc.setPassword(user.getPassword());
		List<CustomGrantedAuthority> cust = new ArrayList<CustomGrantedAuthority>();
		
		String arr[] = user.getRolesArray();
		List<String> permissionsAll = new ArrayList<>();
		
		for(String roleName: arr) {
			System.out.println(roleName+"----------came here");
			String [] permissionArray = roleServ.givePermissions(roleName);
			if(permissionArray != null) {
				for(String permission:permissionArray) {
					permissionsAll.add(permission);
					cust.add(new CustomGrantedAuthority(permission));
				}
				continue;
			}
			cust.add(new CustomGrantedAuthority(roleName));
			userRoles.add(new Role(roleName));
		}
		
		newacc.setRoles(userRoles);
		newacc.setAuthorities(cust);
		
		
		userSession.setRightsAssigned(permissionsAll);;
		userSession.setLoginUser(user);
		userSession.setNewAccount(newacc);
		
		return newacc;	
	}


	public UserService getUserServ() {
		return userServ;
	}


	public void setUserServ(UserService userServ) {
		this.userServ = userServ;
	}


	public RoleService getRoleServ() {
		return roleServ;
	}


	public void setRoleServ(RoleService roleServ) {
		this.roleServ = roleServ;
	}
	public UserSession getUserSession() {
		return userSession;
	}


	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	

}
