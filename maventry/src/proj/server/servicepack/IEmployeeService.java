package proj.server.servicepack;

import java.util.List;

import proj.shared.Employee;

public interface IEmployeeService {

	String addEmployee(Employee toBeAdded);

	List<Employee> listEmployees();

	void updateEmployee(int id, String name, int salary);

	boolean deleteEmployee(String remEmp);

	Employee getEmployee(String username);

	List<Employee> getEmployeesOnDesig(String designame);

}