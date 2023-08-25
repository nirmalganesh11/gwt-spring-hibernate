package proj.server;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import proj.client.servicesClient.DesigServiceClient;
import proj.server.servicepack.IDesigService;

import proj.shared.DesigClass;

public class DesigServiceImpl extends RemoteServiceServlet implements DesigServiceClient {
	
	private static final long serialVersionUID = 1L;
	private ApplicationContext context;
	private IDesigService desigserv;
	
	public DesigServiceImpl(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		desigserv = context.getBean(IDesigService.class);
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
