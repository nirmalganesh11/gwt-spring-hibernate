package silva.server;

import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import silva.shared.DesigClass;

public class DesigServer {
	public SessionFactory factory = HibernateUtil.getSessionFactory();
	public String addDesig(DesigClass toBeAdded)
    {
//    	Employee alreadyPresentEmp = getEmployee(toBeAdded.getUsername());
//    	if(alreadyPresentEmp !=null)
//    		return "Employee Already Present";
//    	else {
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
    	//}
        return "added Designation to the db";
    }
	@SuppressWarnings("deprecation")
	public List<String> getUsernames(){
	    List<String> usernames = new ArrayList<>();
	    try{
	        	Session session = factory.openSession();
	            String hql = "SELECT designame FROM DesigClass";
				Query<String> query = session.createQuery(hql, String.class);
	            usernames = query.list();
	        }
	        catch (Exception e) {
	        e.printStackTrace();
	    }
	    return usernames;
	}
	public DesigClass getDesignation(String designame) {
		Session session = factory.openSession();
	    try {
	        String hql = "SELECT d FROM DesigClass d WHERE d.designame = :designame";
	        Query<DesigClass> query = session.createQuery(hql, DesigClass.class);
	        query.setParameter("designame", designame);

	        return query.uniqueResult();
	    } finally {
	        session.close();
	    }
	}
	
	
	
	
}
