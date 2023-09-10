package proj.server.servicepack;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import proj.server.Exceptions.AccessDeniedException;
import proj.server.daopack.EmployeeDAO;
import proj.server.enums.MethodPermissions;
import proj.server.security.securityClasses.CustomGrantedAuthority;
import proj.server.servicesInterfaces.EmployeeService;
import proj.shared.Employee;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeDAO empdao;
	
	public EmployeeServiceImpl(EmployeeDAO empdao){
		this.empdao = empdao;
	}
	
	@Override
	public String addEmployee(Employee toBeAdded){	
		return empdao.addEmployee(toBeAdded);
	};

	@Override
	public List<Employee> listEmployees() {
		
		return empdao.listEmployees();
	};
   
    @Override
	public void updateEmployee(int id, String name, int salary) {
    	
    	empdao.updateEmployee(id, name, salary);
    };
    
    @Override
	public boolean deleteEmployee(String remEmp) {

    	
    	return empdao.deleteEmployee(remEmp);
    };
 
    @Override
	public Employee getEmployee(String username){
    	return empdao.getEmployee(username);
    };
   
    @Override
	public List<Employee> getEmployeesOnDesig(String designame){
    	return empdao.getEmployeesOnDesig(designame);
    };
	
}
