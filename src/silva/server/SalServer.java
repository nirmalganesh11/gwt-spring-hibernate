package silva.server;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

import org.hibernate.Session;
import silva.shared.SalaryClass;
import org.hibernate.Transaction;

public class SalServer {
	public SessionFactory factory = HibernateUtil.getSessionFactory();
	public SalServer() {}
	public String saveSalaryRecord (SalaryClass salcal) {
		Session session = factory.openSession();
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			int inserted_id = (Integer) session.save(salcal);
			trans.commit();
			return "saved to db";
		}catch(Exception e){
			if(trans != null) {
				trans.rollback();
			}
			System.out.println(e.toString());
			return "problem with saving";
		}
		finally {
			session.close();
		}
		
	}
	public List<SalaryClass> getSalRecord(String username){
		Session session = factory.openSession();
		try {
			String hql = "FROM SalaryClass where username = :username";
			Query<SalaryClass> query = session.createQuery(hql,SalaryClass.class);
			query.setParameter("username",username);
			List<SalaryClass> salaryRecords  = query.list();
			return salaryRecords;
		}catch(Exception e) {
			System.out.println(e.toString());
			return null;
		}
		finally {
			session.close();
		}	
	}
	
	
	
	
}
