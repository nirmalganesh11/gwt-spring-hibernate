package proj.client.servicesClient;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import proj.shared.security.Permission;
import proj.shared.security.Role;

public interface RoleServiceClientAsync {
	
	void getAllRoles(AsyncCallback<List<Role>> callback);
	void addRole(Role role ,AsyncCallback<String> callback);
	void deleteRole(Role role, AsyncCallback<String>callback);
	void updateRole(Role role,AsyncCallback<String> callback);
	
	
	void getAllPermissions(AsyncCallback<List<Permission>> callback);
	
}
