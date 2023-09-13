package proj.client.servicesClient;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import proj.shared.Employee;

public interface EmployeeServiceClientAsync {
	void signUpEmployee(Employee iniDetials,AsyncCallback<String> callback);
	void remEmployee(String remEmp, AsyncCallback<Boolean>callback);
	void getEmployee(String username,AsyncCallback<Employee>callback);
	void getEmployeesOnDesig(String designame,AsyncCallback<List<Employee>> callback);
	void getEmployees(AsyncCallback<List<Employee>> asyncCallback);
	void updateEmployee(Employee update,AsyncCallback<String> asyncCallback);
}
