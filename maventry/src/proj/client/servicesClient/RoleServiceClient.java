package proj.client.servicesClient;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import proj.shared.security.Permission;
import proj.shared.security.Role;

@RemoteServiceRelativePath("role")
public interface RoleServiceClient extends RemoteService{
	
	List<Role> getAllRoles ();
	
	String addRole(Role role);
	
	String deleteRole(Role role);
	
	String updateRole(Role role);
	
	List<Permission> getAllPermissions();
	
}
