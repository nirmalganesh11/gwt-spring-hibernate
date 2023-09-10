package proj.server.daopack;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import proj.server.servicesInterfaces.FullUserService;
import proj.shared.security.FullUser;
import proj.shared.security.Permission;
import proj.shared.security.Role;

public class FullUserDAO {
	
	private HibernateTemplate template;
	
	public FullUserDAO(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
	}


	public String addFullUser(FullUser toBeAdded) {
		
//		String hql = "FROM FullUser WHERE userid = :userid";
//
//		String userid = toBeAdded.getUserId();
//        FullUser user = (FullUser) template.execute(new HibernateCallback<Object>() {
//            @Override
//            public Object doInHibernate(Session session) throws HibernateException {
//            	 Query<FullUser> query = session.createQuery(hql, FullUser.class);
//                 query.setParameter("userid", userid);
//                 return query.uniqueResult();
//            }
//        });
//		if(user !=null) {
//			template.update(user);
//		}
//		template.saveOrUpdate(toBeAdded);
		template.save(toBeAdded);
		return "full user Saved";
	}


	public List<FullUser> getAllFullUsers() {
		return template.loadAll(FullUser.class);
	}


	public String deleteFullUser(FullUser user) {
		System.out.println(user.getFuid());
		FullUser daomania = template.get( FullUser.class,user.getFuid());
		template.delete(daomania);
		return "user deleted successfully";
	}


	public String updateFullUser(FullUser role) {
		// TODO Auto-generated method stub
		return null;
	}



}
