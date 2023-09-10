package proj.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import proj.client.servicesClient.EmployeeServiceClient;
import proj.server.Exceptions.AccessDeniedException;
import proj.server.enums.MethodPermissions;
import proj.server.security.securityClasses.CustomGrantedAuthority;
import proj.server.servicesInterfaces.EmployeeService;
import proj.shared.Employee;

public class EmployeeServiceImplServlet extends RemoteServiceServlet implements EmployeeServiceClient{

	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private EmployeeService empserv;
	

	public EmployeeServiceImplServlet(){
		
		
		context = new ClassPathXmlApplicationContext("services.xml");
		empserv = context.getBean(EmployeeService.class);
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
