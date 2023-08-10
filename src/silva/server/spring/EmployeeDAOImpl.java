package silva.server.spring;

import org.hibernate.Session;


import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import silva.shared.Employee;

import java.util.List;

@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {

    private final HibernateTemplate hibernateTemplate;

    public EmployeeDAOImpl(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    @Override
    public void saveEmployee(Employee employee) {
        hibernateTemplate.save(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        hibernateTemplate.update(employee);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        Employee employee = getEmployeeById(employeeId);
        if (employee != null) {
            hibernateTemplate.delete(employee);
        }
    }
    @Override
    public Employee getEmployeeById(int employeeId) {
        return hibernateTemplate.get(Employee.class, employeeId);
    }
    @Override
    public List<Employee> getAllEmployees() {
        return hibernateTemplate.loadAll(Employee.class);
    }
}
