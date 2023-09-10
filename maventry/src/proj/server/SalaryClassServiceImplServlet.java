package proj.server;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import proj.client.servicesClient.SalaryClassServiceClient;
import proj.server.servicesInterfaces.SalaryService;
import proj.shared.SalaryClass;

public class SalaryClassServiceImplServlet extends RemoteServiceServlet implements SalaryClassServiceClient{

	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private SalaryService salaryServ;
	

	public SalaryClassServiceImplServlet(){
		context = new ClassPathXmlApplicationContext("services.xml");
		salaryServ = context.getBean(SalaryService.class);
		
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
