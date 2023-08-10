package silva.server;

import javax.persistence.NoResultException;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import silva.shared.User;

public class AuthClass {
	public SessionFactory factory = HibernateUtil.getSessionFactory();
	
	public void addUser(User toBeAdded)
    {
        Session session = factory.openSession();
        Transaction transaction = null;
       
        try{
            transaction = session.beginTransaction();
            int inserted_id = (Integer) session.save(toBeAdded);
            System.out.println("Inserted ID :"+" ="+ inserted_id);
           
            transaction.commit();
            
        } 
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            System.out.println("ERROR: " + e.getMessage());
        }
        finally {
            session.close();
            
        }
        
    }
	
	
    public User findByUsername(String username) {
    	Session session = factory.openSession();
    	User founded = null;
        try{
            founded= session.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
            return founded;
        } catch (NoResultException e) {
        	System.out.println("got No Result Found Exception");
            return null;
        }finally {
        	session.close();
        }
    }
    public String validateUsername(String username) {
    	Session session = factory.openSession();
    	User founded = null;
        try{
            founded= session.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
            if(founded == null)
            	return "Username is available bro";
            return founded.getUsername()+"is already used";
        } catch (NoResultException e) {
            return e.toString();
        }finally {
        	session.close();
        	}
    	}
    	
    }


