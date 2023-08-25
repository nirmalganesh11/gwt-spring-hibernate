package proj.server.daopack;

import java.util.List;

import proj.shared.Employee;

public interface IEmployeeDao {

	String addEmployee(Employee toBeAdded);

	List<Employee> listEmployees();

	//instead of sending individual employee detials send all the employee object 
	void updateEmployee(int id, String name, int salary);

	boolean deleteEmployee(String remEmp);

	Employee getEmployee(String username);

	List<Employee> getEmployeesOnDesig(String designame);

}