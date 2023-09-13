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
	public String addEmployee(Employee toBeAdded) {
		
		if(checkPermission(MethodPermissions.WRITE_EMPLOYEE.name())) {
			return "RoleNotAllowed";
		}
		
		
		return empdao.addEmployee(toBeAdded);
	}

	@Override
	public List<Employee> listEmployees() {
		
		if(checkPermission(MethodPermissions.READ_EMPLOYEE.name())) {
			return null;
		}
		
		
		return empdao.listEmployees();
	}
   
    @Override
	public String updateEmployee(int id, String name, int salary,String designation,String password) {
    	
    	if(checkPermission(MethodPermissions.UPDATE_EMPLOYEE.name())) {
			return "not updated due to not permissions";
		}
    	
    	empdao.updateEmployee(id, name, salary,designation,password);
    	return "Updated Employee";
    }
    
    @Override
	public boolean deleteEmployee(String remEmp) {
    	
    	if(checkPermission(MethodPermissions.WRITE_EMPLOYEE.name())) {
			return false;
		}
    	
    	
    	return empdao.deleteEmployee(remEmp);
    }
 
    @Override
	public Employee getEmployee(String username){
    	
    	if(checkPermission(MethodPermissions.READ_EMPLOYEE.name())) {
			return null;
		}
    	
    	return empdao.getEmployee(username);
    }
   
    @Override
	public List<Employee> getEmployeesOnDesig(String designame){
    	
    	if(checkPermission(MethodPermissions.READ_EMPLOYEE.name())) {
			return null;
		}
    	
    	
    	return empdao.getEmployeesOnDesig(designame);
    };
    
    public Boolean checkPermission(String authority) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for(GrantedAuthority ga: auth.getAuthorities()) {
			if(ga.getAuthority().equals(authority)) {
				return false;
			}
		}
		return true;
	}
	
}
