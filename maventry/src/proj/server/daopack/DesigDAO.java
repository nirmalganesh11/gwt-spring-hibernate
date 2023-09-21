package proj.server.daopack;

import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import proj.shared.DesigClass;
import proj.shared.Employee;

public class DesigDAO {
	
	public SessionFactory factory;
	private HibernateTemplate template;
	
	public DesigDAO(SessionFactory factory){
		this.factory = factory;
		this.template = new HibernateTemplate(factory);
		template.setCheckWriteOperations(false);
	}
	
	
	public String addDesig(DesigClass toBeAdded)
    {

		template.save(toBeAdded);
		return "added Designation to db";
    }
	
	
	public List<String> getUsernames(){

	    String hql = "SELECT designame FROM DesigClass";
		 List<String> usernames = template.execute(session -> {
	            Query<String> query = session.createQuery(hql, String.class);
	            return query.getResultList();
	        });
	     return usernames;
	}
	
	public DesigClass getDesignation(String designame) {

		  String hql = "SELECT d FROM DesigClass d WHERE d.designame = :designame";
	        @SuppressWarnings("deprecation")
			List<?> designations = template.findByNamedParam(hql, "designame", designame);

	        if (!designations.isEmpty()) {
	            return (DesigClass) designations.get(0);
	        } else {
	            return null; // Or throw an exception indicating not found
	        }
	}
	
	
	public List<DesigClass> listDesigantions(){
    	
        Session session = factory.openSession();
        List<DesigClass> listOfEmp = null;
        Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();
            List<DesigClass> products = session.createQuery("FROM proj.shared.DesigClass").list();
            listOfEmp = products;
            transaction.commit();
        }
        catch (Exception e){
            if (transaction != null)
                transaction.rollback();
            System.out.println("ERROR: " + e.getMessage());
        }
        finally{
            session.close();
            
        }
        return listOfEmp;
    }
	
	
	
}
