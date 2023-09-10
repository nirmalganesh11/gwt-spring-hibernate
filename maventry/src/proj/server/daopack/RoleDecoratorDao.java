package proj.server.daopack;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;

import proj.server.security.securityClasses.RoleDecorator;
import proj.shared.security.Role;

public class RoleDecoratorDao {
	
	private HibernateTemplate template;
	
	private SessionFactory factory;
	
	public RoleDecoratorDao(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
	}
	
	public String addRoleDecorator(RoleDecorator toBeAdded) {
		template.save(toBeAdded);
		return "Role decorator added";
	}
	
	public RoleDecorator getRoleDecorator(String decName) {
		
		return null;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String[] givePermissions(String roleName) {
		
		 List<RoleDecorator> roles = (List<RoleDecorator>) template.findByNamedParam(
		            "FROM RoleDecorator R WHERE R.roleName = :roleName",
		            "roleName", roleName
		        );

		        if (roles.isEmpty()) {
		            return null;
		        }

		        return roles.get(0).getRolePermissions();
		
	}
	
	
	
}
