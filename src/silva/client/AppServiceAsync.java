package silva.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import silva.shared.DesigClass;
import silva.shared.Employee;
import silva.shared.SalaryClass;
import silva.shared.User;


/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface AppServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
	void signUpUser(Employee iniDetials,AsyncCallback<String> callback);
	void loginUserAuth(User logDetails,AsyncCallback<Boolean> callback);
	void getEmployees(AsyncCallback<List<Employee>> callback);
	void signUpAuth(User userDetails,AsyncCallback<String> callback);
	void checkUsername(String username,AsyncCallback<String> callback);
	void remEmployee(String remEmp, AsyncCallback<Boolean>callback);
	void getEmployee(String username,AsyncCallback<Employee>callback);
	void addDesignation(DesigClass desigValue, AsyncCallback<String> callback);
	void getDesignations(AsyncCallback<List<String>> callback);
	void getEmployeesOnDesig(String designame,AsyncCallback<List<Employee>> callback);
	void saveSalaryRecord(SalaryClass salcal,AsyncCallback<String> callback);
	void getSalariesEmp(String username, AsyncCallback<List<SalaryClass>> callback);
	void getDesignation(String designame,AsyncCallback<DesigClass> callback);
}
