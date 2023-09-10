package proj.server.servicesInterfaces;

import java.util.List;

import proj.shared.security.Role;

public interface RoleService {
	public String addRole(Role toBeAdded);
	public String deleteRole(Role role);
	public String updateRole(Role role);
	public List<Role> getAllRoles();
	public String [] givePermissions(String roleName);
	
}
