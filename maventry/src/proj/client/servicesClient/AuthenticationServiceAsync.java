package proj.client.servicesClient;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AuthenticationServiceAsync {
	void authenticate(String username,String password, AsyncCallback<Boolean> callback);
	void getLoggedInUser(AsyncCallback<String> callback);
}
