package proj.client.servicesClient;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import proj.shared.security.FullUser;

public interface FullUserServiceClientAsync {
	
	void getAllFullUsers(AsyncCallback<List<FullUser>> callback);
	
	void addFullUser(FullUser user,AsyncCallback<String> callback);
	
	void updateFullUser(FullUser user,AsyncCallback<String> callback);
	
	void deleteFullUser(FullUser user,AsyncCallback<String> callback);
	
	
	
	
}