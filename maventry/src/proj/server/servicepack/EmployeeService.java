package proj.server.servicepack;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import proj.server.daopack.IEmployeeDao;
import proj.server.security.securityClasses.CustomGrantedAuthority;
import proj.shared.Employee;

public class EmployeeService implements IEmployeeService {
	private IEmployeeDao empdao;
	
	public EmployeeService(IEmployeeDao empdao){
		this.empdao = empdao;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String addEmployee(Employee toBeAdded){
		
		String Operation1 ="EMP_ROLE";
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.isAuthenticated()) {
	        List<CustomGrantedAuthority> authorities = (List<CustomGrantedAuthority>) authentication.getAuthorities();
	        for (GrantedAuthority authority : authorities) {
	            String role = authority.getAuthority();
	            if(role.equals(Operation1)) {
	            	return empdao.addEmployee(toBeAdded);
	            	//break;
	            }
	        }
	    }
		
		return "RoleNotAllowed";
	};

	@Override
	public List<Employee> listEmployees(){
		return empdao.listEmployees();
	};
   
    @Override
	public void updateEmployee(int id, String name, int salary){
    	empdao.updateEmployee(id, name, salary);
    };
    
    @Override
	public boolean deleteEmployee(String remEmp){
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
