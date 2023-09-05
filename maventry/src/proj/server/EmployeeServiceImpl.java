package proj.server;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import proj.client.servicesClient.EmployeeServiceClient;
import proj.server.security.securityClasses.CustomGrantedAuthority;
import proj.server.servicepack.IEmployeeService;
import proj.shared.Employee;
public class EmployeeServiceImpl extends RemoteServiceServlet implements EmployeeServiceClient{

	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private IEmployeeService empserv;
	
	@SuppressWarnings("unchecked")
	public EmployeeServiceImpl(){
		
		
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		empserv = context.getBean(IEmployeeService.class);
	}
	
	@Override
	public String signUpEmployee(Employee encoded) {
		return empserv.addEmployee(encoded);
	}

	@Override
	public List<Employee> getEmployees() {
		return empserv.listEmployees();
	}

	@Override
	public boolean remEmployee(String remEmp) {
		return empserv.deleteEmployee(remEmp);
	}

	@Override
	public Employee getEmployee(String username) {
		return empserv.getEmployee(username);
	}

	@Override
	public List<Employee> getEmployeesOnDesig(String designame) {
		return empserv.getEmployeesOnDesig(designame);
	}
	
}
