package proj.server.sessionManagement;

import java.util.List;

import org.springframework.security.core.Authentication;

import proj.shared.User;
import proj.shared.security.Role;
import proj.shared.security.UserAccount;

public class UserSession {
	
	private Authentication authenticatedUser;
	
	private List<Role> rolesList;
	
	private User loginUser;
	
	private UserAccount newAccount;
	 
	private List<String> rightsAssigned;
	
	public UserSession() {
		
	}

	public Authentication getAuthenticatedUser() {
		return authenticatedUser;
	}

	public void setAuthenticatedUser(Authentication authenticatedUser) {
		this.authenticatedUser = authenticatedUser;
	}

	public List<Role> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Role> rolesList) {
		this.rolesList = rolesList;
	}

	public User getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}

	public UserAccount getNewAccount() {
		return newAccount;
	}

	public void setNewAccount(UserAccount newAccount) {
		this.newAccount = newAccount;
	}

	public List<String> getRightsAssigned() {
		return rightsAssigned;
	}

	public void setRightsAssigned(List<String> rightsAssigned) {
		this.rightsAssigned = rightsAssigned;
	}

	


}
