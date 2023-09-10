package proj.client.servicesClient;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import proj.shared.security.FullUser;


@RemoteServiceRelativePath("fulluser")
public interface FullUserServiceClient extends RemoteService{
	
	List<FullUser> getAllFullUsers ();
	
	String addFullUser(FullUser user);
	
	String deleteFullUser(FullUser user);
	
	String updateFullUser(FullUser user);
	
}