package proj.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import proj.client.servicesClient.UserServiceClient;
import proj.server.servicepack.IUserService;
import proj.shared.User;


public class UserServiceImpl extends RemoteServiceServlet implements UserServiceClient {

	private static final long serialVersionUID = 1L;
	
	private ApplicationContext context;
	private IUserService userServ;

	public UserServiceImpl(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		userServ = context.getBean(IUserService.class);
		
	}

	@Override
	public boolean loginUserAuth(User logDetails) {
		User user = userServ.findByUsername(logDetails.getUsername());
	    if (user != null && user.getPassword().equals(logDetails.getPassword()))
	        return true;
	    else
	        return false;
	}

	@Override
	public String signUpAuth(User newUser) {
		return userServ.addUser(newUser);
	}

	@Override
	public String checkUsername(String username) {
		return userServ.validateUsername(username);
	}
	
}
