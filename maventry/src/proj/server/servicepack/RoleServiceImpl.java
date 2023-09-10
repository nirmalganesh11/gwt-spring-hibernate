package proj.server.servicepack;

import java.util.ArrayList;
import java.util.List;

import proj.server.daopack.RoleDAO;
import proj.server.daopack.RoleDecoratorDao;
import proj.server.security.securityClasses.RoleDecorator;
import proj.server.servicesInterfaces.RoleService;
import proj.shared.security.Permission;
import proj.shared.security.Role;

public class RoleServiceImpl implements RoleService {
	
	private RoleDAO roleDao;
	
	private RoleDecoratorDao decDao;
	
	public RoleServiceImpl(RoleDAO roleDao ,RoleDecoratorDao decDao) {
		this.roleDao = roleDao;
		this.decDao = decDao;
	}
	

	@Override
	public String addRole(Role toBeAdded) {
//		for(Permission perm:toBeAdded.getRolePermissions()) {
//			System.out.print(perm.getPermissionType());
//		}
		RoleDecorator rd = new RoleDecorator(toBeAdded);
		String str = decDao.addRoleDecorator(rd);
		
		return roleDao.addRole(toBeAdded)+str;
	}
	public Role getRole(String roleName) {
		return roleDao.getRole(roleName);
	}
	
	
	@Override
	public String deleteRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}
	
	public String [] givePermissions(String roleName) {
		return decDao.givePermissions(roleName);
		
	}
	

}
