package proj.server;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import proj.client.servicesClient.DesigServiceClient;
import proj.server.security.securityClasses.CustomGrantedAuthority;
import proj.server.servicepack.IDesigService;
import proj.server.servicepack.IEmployeeService;
import proj.shared.DesigClass;

public class DesigServiceImpl extends RemoteServiceServlet implements DesigServiceClient {
	
	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private IDesigService desigserv;
	
	public DesigServiceImpl(){
		String Operation1 ="DESIG_ROLE";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
		    List<CustomGrantedAuthority> authorities = (List<CustomGrantedAuthority>) authentication.getAuthorities();
		    for (GrantedAuthority authority : authorities) {
		        String role = authority.getAuthority();
		        if(role.equals(Operation1)) {
		        	context = new ClassPathXmlApplicationContext("applicationContext.xml");
		    		desigserv = context.getBean(IDesigService.class);
		    		break;
		        }
		    }
		}	
		
	}

	@Override
	public String addDesignation(DesigClass desigVal) {
		return desigserv.addDesig(desigVal);
	}

	@Override
	public List<String> getDesignations() {
		return desigserv.getUsernames();
	}

	@Override
	public DesigClass getDesignation(String designame) {
		return desigserv.getDesignation(designame);
	}

}
