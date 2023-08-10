package silva.server.spring;

import java.util.List;

import silva.shared.Employee;
public interface EmployeeDAO {
	 void saveEmployee(Employee employee);
	    void updateEmployee(Employee employee);
	    void deleteEmployee(int employeeId);
	    Employee getEmployeeById(int employeeId);
	    List<Employee> getAllEmployees();
}
