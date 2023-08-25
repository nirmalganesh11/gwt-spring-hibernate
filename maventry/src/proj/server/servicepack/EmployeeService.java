package proj.server.servicepack;

import java.util.List;
import proj.server.daopack.IEmployeeDao;
import proj.shared.Employee;

public class EmployeeService implements IEmployeeService {
	private IEmployeeDao empdao;
	
	public EmployeeService(IEmployeeDao empdao){
		this.empdao = empdao;
	}
	
	@Override
	public String addEmployee(Employee toBeAdded){
		return empdao.addEmployee(toBeAdded);
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
