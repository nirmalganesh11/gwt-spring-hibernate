package proj.server.daopack;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import proj.shared.security.Permission;
import proj.shared.security.Role;

public class RoleDAO {

	private HibernateTemplate template;
	
	public RoleDAO(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
	}
	
	public String addRole(Role toBeAdded) {
		template.save(toBeAdded);
		return "Role added done bro";
	}
	
	public List<Role> getAllRoles(){
		return template.loadAll(Role.class);
		
	}
	public Role getRole(String roleName) {
		
		return null;
	}

}
