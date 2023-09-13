package proj.client.servicesClient;

import java.util.List;

import proj.shared.Employee;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("emp")
public interface EmployeeServiceClient extends RemoteService{
	String signUpEmployee(Employee encoded);
	List<Employee> getEmployees();
	boolean remEmployee(String remEmp);
	Employee getEmployee(String username);
	List<Employee> getEmployeesOnDesig(String designame);
	String updateEmployee(Employee update);
	
}
