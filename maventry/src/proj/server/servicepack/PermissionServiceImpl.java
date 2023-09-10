package proj.server.servicepack;

import java.util.List;

import proj.server.daopack.PermissionDAO;
import proj.server.servicesInterfaces.PermissionService;
import proj.shared.security.Permission;

public class PermissionServiceImpl implements PermissionService{

	private PermissionDAO permdao;
	
	public PermissionServiceImpl(PermissionDAO permdao) {
		this.permdao = permdao;
	}
	
	@Override
	public List<Permission> getAllPermissions() {
		return permdao.getAllPermissions();
	}

	@Override
	public String addPermission(Permission perm) {
		return permdao.addPermission(perm);
	}
	
}
