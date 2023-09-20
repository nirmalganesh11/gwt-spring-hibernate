package proj.server;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import proj.client.servicesClient.FullUserServiceClient;
import proj.server.servicesInterfaces.FullUserService;
import proj.server.servicesInterfaces.RoleService;
import proj.shared.security.FullUser;

public class FullUserServiceImplServlet extends RemoteServiceServlet implements FullUserServiceClient{

	
	
	private static final long serialVersionUID = 1L;
	
	private FullUserService fullUserServ;
	
	private ApplicationContext context;

	
	public FullUserServiceImplServlet() {
		context = new ClassPathXmlApplicationContext("services.xml");
		fullUserServ = context.getBean(FullUserService.class);	
	}
	
	@Override
	public List<FullUser> getAllFullUsers() {
		return fullUserServ.getAllFullUsers();
	}

	@Override
	public String addFullUser(FullUser user) {
		return fullUserServ.addFullUser(user);
	}

	@Override
	public String deleteFullUser(FullUser user) {
		return fullUserServ.deleteFullUser(user);
	}

	@Override
	public String updateFullUser(FullUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean authenticateUserIdMail(String UserId, String email) {
		return fullUserServ.authenticateWithUserIdMail(UserId, email);
	}

}
