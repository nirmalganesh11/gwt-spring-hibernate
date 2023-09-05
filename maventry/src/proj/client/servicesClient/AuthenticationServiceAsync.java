package proj.client.servicesClient;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AuthenticationServiceAsync {
	void authenticate(String encodedString, AsyncCallback<Boolean> callback);
	void getLoggedInUser(AsyncCallback<String> callback);
	void LogoutUser(AsyncCallback<Void>callback);
}
