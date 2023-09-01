package proj.server;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import proj.client.servicesClient.SalaryClassServiceClient;
import proj.server.security.securityClasses.CustomGrantedAuthority;
import proj.server.servicepack.IDesigService;
import proj.server.servicepack.ISalaryService;

import proj.shared.SalaryClass;

public class SalaryClassServiceImpl extends RemoteServiceServlet implements SalaryClassServiceClient{

	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private ISalaryService salaryServ;
	
	public SalaryClassServiceImpl(){
		
		String Operation1 ="SALARY_ROLE";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
		    List<CustomGrantedAuthority> authorities = (List<CustomGrantedAuthority>) authentication.getAuthorities();
		    for (GrantedAuthority authority : authorities) {
		        String role = authority.getAuthority();
		        if(role.equals(Operation1)) {
		        	context = new ClassPathXmlApplicationContext("applicationContext.xml");
		    		salaryServ = context.getBean(ISalaryService.class);
		    		break;
		        }
		    }
		}
		
	}
	@Override
	public String saveSalaryRecord(SalaryClass salcal) {
		return salaryServ.saveSalaryRecord(salcal);
	}

	@Override
	public List<SalaryClass> getSalariesEmp(String username) {
		return salaryServ.getSalRecord(username);
	}
	
}
