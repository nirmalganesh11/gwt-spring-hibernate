package silva.server;

import silva.client.AppService;
import silva.shared.DesigClass;
import silva.shared.Employee;
import silva.shared.SalaryClass;
import silva.shared.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import silva.server.spring.EmployeeDAO;
@SuppressWarnings("serial")
public class AppServiceImpl extends RemoteServiceServlet implements AppService {
	
	private EmployeeDao empData;
	private AuthClass authData;
	private DesigServer ds;
	private SalServer sc;
//	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//	EmployeeDAO employeeDAO = context.getBean(EmployeeDAO.class);
//	try {
//}catch(Exception e) {
//	System.out.println(e.toString());
//}
	public AppServiceImpl() {
		empData = new EmployeeDao();
		authData = new AuthClass();
		ds = new DesigServer();
		sc = new SalServer();
	}
	public String greetServer(String input) throws IllegalArgumentException {
		return "updated db" + input;
	}
	@Override
	public String signUpUser(Employee toBeAdded) {
		return empData.addEmployee(toBeAdded);
	}
	@Override
	public String signUpAuth(User newUser) {
		authData.addUser(newUser);
		return "added user";
	}
	@Override
	public List<Employee> getEmployees() {
		return empData.listEmployees();
	}
	
	@Override
	public boolean loginUserAuth(User logDetails) {
		User user = authData.findByUsername(logDetails.getUsername());
	    if (user != null && user.getPassword().equals(logDetails.getPassword()))
	        return true;
	    else
	        return false;
	}

	@Override
	public String checkUsername(String username) {
		return authData.validateUsername(username);
	}

	@Override
	public boolean remEmployee(String remEmp) {
		return empData.deleteEmployee(remEmp);
	}

	@Override
	public Employee getEmployee(String username) {
		return empData.getEmployee(username);
	}
	@Override
	public String addDesignation(DesigClass desigVal) {
		return ds.addDesig(desigVal);
	}
	@Override
	public List<String> getDesignations() {
		return ds.getUsernames();
	}
	@Override
	public List<Employee> getEmployeesOnDesig(String designame) {
		return empData.getEmployeesOnDesig(designame);
	}
	@Override
	public String saveSalaryRecord(SalaryClass salcal) {
		return sc.saveSalaryRecord(salcal);
	}
	@Override
	public List<SalaryClass> getSalariesEmp(String username) {
		return sc.getSalRecord(username);
	}
	@Override
	public DesigClass getDesignation(String designame) {
		return ds.getDesignation(designame);
	}
	
}
