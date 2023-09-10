package proj.server.daopack;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;

import proj.shared.security.Role;
import proj.shared.security.Permission;

public class PermissionDAO {
	
	private HibernateTemplate template;
	
	public PermissionDAO(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
	}
	
	public String addPermission(Permission toBeAdded) {
		template.save(toBeAdded);
		return "Added Permission";
	}
	
	public List<Permission> getAllPermissions(){
		return template.loadAll(Permission.class);
	}

}
