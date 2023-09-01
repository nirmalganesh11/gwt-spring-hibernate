package proj.server.daopack;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import proj.shared.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;


public class UserDAO implements IUserDao {
	public SessionFactory factory;
	private HibernateTemplate template;
	public UserDAO(SessionFactory sessionFactory){
		this.template = new HibernateTemplate(sessionFactory);
		template.setCheckWriteOperations(false);
		this.factory = sessionFactory;
	}
	
	
	@Override
	public String addUser(User toBeAdded)
    {
		template.save(toBeAdded);
		return "added";
//        Session session = factory.openSession();
//        Transaction transaction = null;
//       
//        try{
//            transaction = session.beginTransaction();
//            int inserted_id = (Integer) session.save(toBeAdded);
//            System.out.println("Inserted ID :"+" ="+ inserted_id);
//            
//            transaction.commit();
//            
//        } 
//        catch (Exception e){
//            if (transaction != null)
//                transaction.rollback();
//            System.out.println("ERROR: " + e.getMessage());
//            return "not added";
//        }
//        finally {
//            session.close();
//            
//        }
//        return "added";
        
    }
	
	
    
    
	@Override
	public User findByUsername(String username) {
    	
//    	Session session = factory.openSession();
//    	User founded = null;
//        try{
//            founded= session.createQuery("FROM User WHERE username = :username", User.class)
//                    .setParameter("username", username)
//                    .uniqueResult();
//            return founded;
//        } catch (NoResultException e) {
//        	System.out.println("got No Result Found Exception");
//            return null;
//        }finally {
//        	session.close();
//        }
    	DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("username", username));
        
        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) template.findByCriteria(criteria);

        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    		
    }
   
    @Override
	public String validateUsername(String username) {
    
//    	Session session = factory.openSession();
//    	User founded = null;
//        try{
//            founded= session.createQuery("FROM User WHERE username = :username", User.class)
//                    .setParameter("username", username)
//                    .uniqueResult();
//            if(founded == null)
//            	return "Username is available bro";
//            return founded.getUsername()+"is already used";
//        } catch (NoResultException e) {
//            return e.toString();
//        }finally {
//        	session.close();
//        	}
//    	}
    	
    	DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("username", username));

        @SuppressWarnings("unchecked")
		List<User> users = (List<User>) template.findByCriteria(criteria);

        if (users != null && !users.isEmpty()) {
            return "username not available";
        }
        return "username Available";
    	
    }
}
