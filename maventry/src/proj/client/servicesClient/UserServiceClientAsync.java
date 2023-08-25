package proj.client.servicesClient;

import com.google.gwt.user.client.rpc.AsyncCallback;

import proj.shared.User;

public interface UserServiceClientAsync {
	void loginUserAuth(User logDetails,AsyncCallback<Boolean> callback);
	void signUpAuth(User userDetails,AsyncCallback<String> callback);
	void checkUsername(String username,AsyncCallback<String> callback);
}
