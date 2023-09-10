package proj.client.servicesClient;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import proj.shared.User;
import proj.shared.security.Role;

@RemoteServiceRelativePath("user")
public interface UserServiceClient extends RemoteService{
	boolean loginUserAuth(User logDetails);
	String signUpAuth(User newUser);
	String checkUsername(String username);
	String addRole(Role userRole);
}
