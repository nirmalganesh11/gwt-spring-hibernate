package proj.server.daopack;

import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import proj.shared.DesigClass;

public class DesigDAO implements IDesigDAO {
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	public DesigDAO(SessionFactory factory){
		this.template = new HibernateTemplate(factory);
		template.setCheckWriteOperations(false);
	}
	
	
	public String addDesig(DesigClass toBeAdded)
    {
//    		Session session = factory.openSession();
//    		Transaction transaction = null;
//    		try{
//    			transaction = session.beginTransaction();
//    			int inserted_id = (Integer) session.save(toBeAdded);
//    			System.out.println("Inserted ID :"+" ="+ inserted_id);
//    			transaction.commit();
//    		} 
//    		catch (Exception e){
//    			if (transaction != null)
//    				transaction.rollback();
//    			System.out.println("ERROR: " + e.getMessage());
//    		}
//    		finally {
//    			session.close();
//    		}
//    	//}
//        return "added Designation to the db";
		template.save(toBeAdded);
		return "added Designation to db";
    }
	
	
	public List<String> getUsernames(){
		
//	    List<String> usernames = new ArrayList<>();
//	    try{
//	        	Session session = factory.openSession();
//	            String hql = "SELECT designame FROM DesigClass";
//				Query<String> query = session.createQuery(hql, String.class);
//	            usernames = query.list();
//	        }
//	        catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	    return usernames;
	    String hql = "SELECT designame FROM DesigClass";
		 List<String> usernames = template.execute(session -> {
	            Query<String> query = session.createQuery(hql, String.class);
	            return query.getResultList();
	        });
	     return usernames;
	}
	
	public DesigClass getDesignation(String designame) {
//		Session session = factory.openSession();
//	    try {
//	        String hql = "SELECT d FROM DesigClass d WHERE d.designame = :designame";
//	        Query<DesigClass> query = session.createQuery(hql, DesigClass.class);
//	        query.setParameter("designame", designame);
//
//	        return query.uniqueResult();
//	    } finally {
//	        session.close();
//	    }
		  String hql = "SELECT d FROM DesigClass d WHERE d.designame = :designame";
	        @SuppressWarnings("deprecation")
			List<?> designations = template.findByNamedParam(hql, "designame", designame);

	        if (!designations.isEmpty()) {
	            return (DesigClass) designations.get(0);
	        } else {
	            return null; // Or throw an exception indicating not found
	        }
	}
	
	
	
}
