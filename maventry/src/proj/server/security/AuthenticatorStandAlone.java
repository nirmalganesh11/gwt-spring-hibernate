package proj.server.security;

import proj.server.*;

public class AuthenticatorStandAlone {

	private UserService userService;
	private LoginServiceDelegateImpl loginServiceDelegate;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public LoginServiceDelegateImpl getLoginServiceDelegate() {
		return loginServiceDelegate;
	}

	public void setLoginServiceDelegate(LoginServiceDelegateImpl loginServiceDelegate) {
		this.loginServiceDelegate = loginServiceDelegate;
	}

	
	
}
