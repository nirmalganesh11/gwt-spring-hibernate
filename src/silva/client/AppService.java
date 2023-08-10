package silva.client;

import java.util.List;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import silva.shared.DesigClass;
import silva.shared.Employee;
import silva.shared.SalaryClass;
import silva.shared.User;


@RemoteServiceRelativePath("greet")
public interface AppService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	String signUpUser(Employee encoded);
	boolean loginUserAuth(User logDetails);
	String signUpAuth(User newUser);
	List<Employee> getEmployees();
	String checkUsername(String username);
	boolean remEmployee(String remEmp);
	Employee getEmployee(String username);
	String addDesignation(DesigClass desigVal);
	List<String> getDesignations();
	List<Employee> getEmployeesOnDesig(String designame);
	String saveSalaryRecord(SalaryClass salcal);
	List<SalaryClass> getSalariesEmp(String username);
	DesigClass getDesignation(String designame);
	
}
