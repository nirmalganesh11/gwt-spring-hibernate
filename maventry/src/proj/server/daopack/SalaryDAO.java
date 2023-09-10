package proj.server.daopack;

import org.hibernate.SessionFactory;

import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;


import proj.shared.SalaryClass;


public class SalaryDAO {
//	public SessionFactory factory;
//	public SalaryDAO(SessionFactory factory) {
//		this.factory = factory;
//	}
	private HibernateTemplate template;
	
	public SalaryDAO(SessionFactory factory){
		this.template = new HibernateTemplate(factory);
		template.setCheckWriteOperations(false);
	}
	public String saveSalaryRecord (SalaryClass salcal) {
//		Session session = factory.openSession();
//		Transaction trans = null;
//		try {
//			trans = session.beginTransaction();
//			session.save(salcal);
//			trans.commit();
//			return "saved to db";
//		}catch(Exception e){
//			if(trans != null) {
//				trans.rollback();
//			}
//			System.out.println(e.toString());
//			return "problem with saving";
//		}
//		finally {
//			session.close();
//		}
		template.save(salcal);
		return "Saved To DB";
		
	}
	
	@SuppressWarnings("unchecked")
	public List<SalaryClass> getSalRecord(String username){
//		Session session = factory.openSession();
//		try {
//			String hql = "FROM SalaryClass where username = :username";
//			Query<SalaryClass> query = session.createQuery(hql,SalaryClass.class);
//			query.setParameter("username",username);
//			List<SalaryClass> salaryRecords  = query.list();
//			return salaryRecords;
//		}catch(Exception e) {
//			System.out.println(e.toString());
//			return null;
//		}
//		finally {
//			session.close();
//		}
		String hql = "SELECT d FROM SalaryClass d WHERE d.username = :username";
        @SuppressWarnings("deprecation")
		List<?> listSalaries = template.findByNamedParam(hql, "username", username);

        if (!listSalaries.isEmpty()) {
            return (List<SalaryClass>) listSalaries;
        } else {
            return null; // Or throw an exception indicating not found
        }
		
	}
	
	
	
	
	
}
