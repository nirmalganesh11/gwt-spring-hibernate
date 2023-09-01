package proj.client.servicesClient;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("authenticationService")
public interface AuthenticationService  extends RemoteService{
	 boolean authenticate(String encodedString);
	 String getLoggedInUser();
}
