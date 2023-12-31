package proj.server;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import proj.client.servicesClient.DesigServiceClient;
import proj.server.Exceptions.AccessDeniedException;
import proj.server.servicesInterfaces.DesigService;
import proj.shared.DesigClass;

public class DesigServiceImplServlet extends RemoteServiceServlet implements DesigServiceClient {
	
	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private DesigService desigserv;
	

	public DesigServiceImplServlet(){
		
		context = new ClassPathXmlApplicationContext("services.xml");
		desigserv = context.getBean(DesigService.class);
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

	@Override
	public List<DesigClass> getDesignationsList() {
		return desigserv.listDesignations();
	}

}
