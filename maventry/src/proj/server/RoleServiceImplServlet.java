package proj.server;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import proj.client.servicesClient.RoleServiceClient;
import proj.server.servicesInterfaces.PermissionService;
import proj.server.servicesInterfaces.RoleService;
import proj.shared.security.Permission;
import proj.shared.security.Role;

public class RoleServiceImplServlet extends RemoteServiceServlet implements RoleServiceClient{


	private static final long serialVersionUID = 1L;
	
	private RoleService roleServ;
	private PermissionService permServ;
	private ApplicationContext context;

	
	public RoleServiceImplServlet() {
		context = new ClassPathXmlApplicationContext("services.xml");
		roleServ = context.getBean(RoleService.class);	
		permServ = context.getBean(PermissionService.class);
	}
	
	@Override
	public List<Role> getAllRoles() {
		return roleServ.getAllRoles();
	}

	@Override
	public String addRole(Role role) {
		return roleServ.addRole(role);
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
	
	public List<Permission> getAllPermissions(){
		return permServ.getAllPermissions();
	}

}
