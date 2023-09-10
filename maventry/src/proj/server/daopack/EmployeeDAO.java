package proj.server.daopack;

//import org.hibernate.FlushMode;
//import java.util.Iterator;
//import org.hibernate.HibernateException;
//import org.hibernate.Session;
////import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.springframework.orm.hibernate5.HibernateCallback;
//import org.springframework.orm.hibernate5.HibernateTemplate;
//import org.springframework.transaction.annotation.Transactional;
//import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;


import proj.shared.Employee;



public class EmployeeDAO {
	 public SessionFactory factory;
	 
	    public EmployeeDAO(SessionFactory sessionFactory) {
	        this.factory = sessionFactory;
	    }
	  
	    
	    public String addEmployee(Employee toBeAdded)
	    {
	    	Employee alreadyPresentEmp = getEmployee(toBeAdded.getUsername());
	    	if(alreadyPresentEmp !=null)
	    		return "Employee Already Present";
	    	else {
	    		Session session = factory.openSession();
	    		Transaction transaction = null;
	       
	    		try{
	    			transaction = session.beginTransaction();
	    			int inserted_id = (Integer) session.save(toBeAdded);
	    			System.out.println("Inserted ID :"+" ="+ inserted_id);
	    			transaction.commit();
	            
	    		} 
	    		catch (Exception e){
	    			if (transaction != null) {
	    				transaction.rollback();
	    			}
	    			System.out.println("ERROR: " + e.getMessage());
	    		}
	    		finally {
	    			session.close();
	            
	    		}
	    	}
	        return "added Employee to the db";
	    }
	    
	 
	    @SuppressWarnings("unchecked")
		public List<Employee> listEmployees(){
	    	
	        Session session = factory.openSession();
	        List<Employee> listOfEmp = null;
	        Transaction transaction = null;
	        try
	        {
	            transaction = session.beginTransaction();
	            List<Employee> products = session.createQuery("FROM proj.shared.Employee").list();
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
	   
	    public void updateEmployee(int id, String name, int salary){
	        Session session = factory.openSession();
	        Transaction transaction = null;
	        try{
	            transaction = session.beginTransaction();
	            Employee product = (Employee) session.get(Employee.class, id);
	            product.setName(name);
	            product.setSalary(salary);
	            session.update(product);
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
	    }
	    
	    
	    public boolean deleteEmployee(String remEmp)
	    {
	    	Session session = factory.openSession();
	        Transaction transaction = null;
	        try {
	            transaction = session.beginTransaction();
	            @SuppressWarnings("rawtypes")
				Query query = session.createQuery("FROM Employee WHERE username = :username");
	            query.setParameter("username", remEmp);
	            Employee delemp = (Employee) query.uniqueResult();
	            if (delemp != null) {
	                session.delete(delemp);
	                transaction.commit();
	                return true;
	            } else {
	                System.out.println("Employee with username '" + remEmp + "not found.");
	                return false;
	            }
	        } catch (Exception e) {
	            if (transaction != null)
	                transaction.rollback();
	            System.out.println("ERROR: " + e.getMessage());
	        } finally {
	            session.close();
	        }
	        return false;
	    }
	    
	  
	    @SuppressWarnings("unchecked")
		
	    public Employee getEmployee(String username) {
	    	Employee employee = null;
	        Session session = factory.openSession();
	        try {
	            Query<Employee> query = session.createQuery("FROM Employee WHERE username = :username");
	            query.setParameter("username", username);
	            employee = query.uniqueResult();
	        } catch (Exception e) {
	            System.out.println("ERROR: " + e.getMessage());
	        } finally {
	            session.close();
	        }

	        return employee;
	    }
	    
	    
	    public List<Employee> getEmployeesOnDesig(String designame){
	    	  try{
	    		  Session session = factory.openSession();
	              String hql = "FROM Employee WHERE designation = :designame";
	              Query<Employee> query = session.createQuery(hql, Employee.class);
	              query.setParameter("designame", designame);

	              return query.getResultList();
	          } catch (Exception e) {
	              e.printStackTrace();
	              // Handle the exception as required.
	              return null;
	          }
	    }

   
}
