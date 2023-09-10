package proj.server.servicesInterfaces;

import java.util.List;

import proj.shared.security.*;
public interface PermissionService {

	List<Permission> getAllPermissions();
	
	String addPermission(Permission perm);
}
