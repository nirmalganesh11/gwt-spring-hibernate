package proj.server.servicesInterfaces;

import java.util.List;

import proj.server.Exceptions.AccessDeniedException;
import proj.shared.Employee;

public interface EmployeeService {
	
	String addEmployee(Employee toBeAdded);

	List<Employee> listEmployees();

	void updateEmployee(int id, String name, int salary) ;

	boolean deleteEmployee(String remEmp);

	Employee getEmployee(String username);

	List<Employee> getEmployeesOnDesig(String designame);
	
	
}
